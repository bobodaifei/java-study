package com.bobo.servlet;

import com.auth0.jwt.JWT;
import com.bobo.entity.Emp;
import com.bobo.service.EmpService;
import com.bobo.service.EmpServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

    private EmpService empService = new EmpServiceImpl();

    /**
     * 根据token获取用户信息
     * @return user
     */
    public Emp getEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Access-Token");
        int empno = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        Emp emp = empService.selectById(empno);
        return emp;
    }
}
