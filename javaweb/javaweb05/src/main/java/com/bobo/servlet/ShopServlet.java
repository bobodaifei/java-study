package com.bobo.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bobo.service.ShopService;
import com.bobo.service.ShopServiceImpl;


@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

  private ShopService shopService = new ShopServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
  }

}
