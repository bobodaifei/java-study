package com.bobo.springweb.web;

import com.bobo.springweb.service.UserService;
import com.bobo.springweb.utils.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(req.getServletContext());
        UserService userService = applicationContext.getBean(UserService.class);
        userService.selectAll();
        resp.getWriter().write("aoligei");
    }
}
