package com.bobo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.bobo.base.Result;
import com.bobo.component.convert.RechargeConvert;
import com.bobo.config.AliPayConfig;
import com.bobo.entity.Recharge;
import com.bobo.mapper.RechargeMapper;
import com.bobo.pojo.dto.RechargeAlipayNotifyDTO;
import com.bobo.pojo.dto.RechargeDTO;
import com.bobo.pojo.dto.RechargeWXpayNotifyDTO;
import com.bobo.pojo.dto.WXPayDTO;
import com.bobo.pojo.query.RechargeQuery;
import com.bobo.pojo.vo.RechargeVO;
import com.bobo.service.RechargeService;
import com.bobo.service.UserService;
import com.bobo.service.WXService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@PropertySource({"classpath:alipay.properties"})
@PropertySource({"classpath:wxpay.properties"})
public class RechargeServiceImpl implements RechargeService {

  private final RechargeConvert INSTANCE = RechargeConvert.INSTANCE;

  @Autowired
  RechargeMapper rechargeMapper;


  @Override
  public PageInfo<RechargeVO> pageList(RechargeQuery query) {
    PageHelper.startPage(query.getPageNum(), query.getPageSize());
    return new PageInfo<>(INSTANCE.toVOS(rechargeMapper.selectList(query.getUserId())));
  }

  @Override
  public String placeOrder(RechargeDTO dto) {
    Recharge recharge = INSTANCE.toEntity(dto);
    String rechargeNo = IdUtil.getSnowflake().nextIdStr();
    recharge.setRechargeNo(rechargeNo);
    recharge.setStatus(1);
    int i = rechargeMapper.save(recharge);
    return i == 1 ? rechargeNo : null;
  }

  @Autowired
  AliPayConfig aliPayConfig;

  @Autowired
  WXService wxService;

  @Value("${alipay.recharge.notifyUrl}")
  private String alipayNotifyUrl;

  @Value("${alipay.recharge.returnUrl}")
  private String alipayReturnUrl;

  @Value("${wxpay.recharge.notifyUrl}")
  private String wxpayNotifyUrl;

  @Value("${wxpay.recharge.returnUrl}")
  private String wxpayReturnUrl;

  @Override
  public String toPay(RechargeDTO dto) {
    Recharge recharge = INSTANCE.toEntity(dto);
    recharge = rechargeMapper.selectByNo(recharge.getRechargeNo());
    String payUrl = null;
    if ("支付宝".equals(dto.getPayMethod())) {
      payUrl = aliPayConfig.getPayUrl() + "?subject=" + "111" + "&outTradeNo=" + recharge.getRechargeNo() + "&totalAmount=" + recharge.getPayPrice()
              + "&notifyUrl=" + alipayNotifyUrl + "&returnUrl=" + alipayReturnUrl;
    }
    if ("微信".equals(dto.getPayMethod())) {
      WXPayDTO wxPayDTO = new WXPayDTO();
      wxPayDTO.setBody("bobopay");
      wxPayDTO.setOut_trade_no(recharge.getRechargeNo());
      wxPayDTO.setTotal_fee(recharge.getPayPrice());
      wxPayDTO.setNotifyUrl(wxpayNotifyUrl);
      wxPayDTO.setReturnUrl(wxpayReturnUrl);
      payUrl = wxService.pay(wxPayDTO);
    }

    return payUrl;
  }

  @Override
  public RechargeVO selectByNo(String rechargeNo) {
    Recharge recharge = rechargeMapper.selectByNo(rechargeNo);
    return INSTANCE.toVO(recharge);
  }

  @Override
  public void payNotify1(Result<RechargeAlipayNotifyDTO> result) throws InterruptedException {
    if ("200".equals(result.getCode())) {
      RechargeDTO recharge = new RechargeDTO();
      RechargeAlipayNotifyDTO data = result.getData();
      recharge.setRechargeNo(data.getOut_trade_no());
      recharge.setPayTime(data.getGmt_payment());
      recharge.setStatus(4);
      recharge.setPayMethod("支付宝");
      recharge.setPayPrice(data.getReceipt_amount().intValue());
      modifyRecharge(recharge);
    }
  }

  @Override
  public void payNotify(RechargeWXpayNotifyDTO dto) throws InterruptedException {
    RechargeDTO recharge = new RechargeDTO();
    recharge.setRechargeNo(dto.getOut_trade_no());
    recharge.setStatus(4);
    recharge.setPayTime(new Date());
    recharge.setPayMethod("微信");
    recharge.setPayPrice(dto.getTotal_fee());
    modifyRecharge(recharge);
  }

  @Autowired
  private UserService userService;

  @Transactional
  public void modifyRecharge(RechargeDTO dto) {
    Recharge recharge = INSTANCE.toEntity(dto);
    rechargeMapper.updateSuccessByNo(recharge);
    Integer userId = rechargeMapper.selectByNo(recharge.getRechargeNo()).getUserId();
    //忘记返回修改后的余额了
    userService.modifyBlance(userId, new BigDecimal(recharge.getPayPrice()));
    rechargeMapper.updateAfterMoneyByNo(recharge);
  }

}
