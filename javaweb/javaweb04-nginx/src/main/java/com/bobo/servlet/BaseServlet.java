package com.bobo.servlet;

import com.auth0.jwt.JWT;
import com.bobo.entity.Customer;
import com.bobo.service.CustomerService;
import com.bobo.service.CustomerServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    /**
     * 根据token获取用户信息
     * 
     * @return user
     */
    public Customer getCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Access-Token");
        String customer_no = JWT.decode(token).getAudience().get(0);
        Customer customer = customerService.selectById(customer_no);
        return customer;
    }
}
