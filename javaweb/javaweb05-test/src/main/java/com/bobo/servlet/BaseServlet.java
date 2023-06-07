package com.bobo.servlet;

import com.auth0.jwt.JWT;
import com.bobo.entity.Shop;
import com.bobo.entity.User;
import com.bobo.service.ShopService;
import com.bobo.service.UserService;
import com.bobo.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {

    private UserService userService = BeanFactory.getBean(UserService.class);
    ShopService shopService = BeanFactory.getBean(ShopService.class);

    /**
     * 根据token获取用户信息
     * @return user
     */
    public User getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Access-Token");
        String user_no = JWT.decode(token).getAudience().get(0);
        User user = userService.selectById(user_no);
        Shop shop = shopService.selectById(user.getShop_no());
        user.setShop(shop);
        return user;
    }
}
