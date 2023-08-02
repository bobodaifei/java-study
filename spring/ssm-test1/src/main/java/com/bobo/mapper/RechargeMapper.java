package com.bobo.mapper;

import com.bobo.entity.Recharge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RechargeMapper {
  public List<Recharge> selectList(@Param("userId") Integer userId);

  public int save(Recharge recharge);

  public Recharge selectByNo(@Param("rechargeNo") String rechargeNo);

  public void updateSuccessByNo(Recharge recharge);

  public void updateAfterMoneyByNo(Recharge recharge);
}
