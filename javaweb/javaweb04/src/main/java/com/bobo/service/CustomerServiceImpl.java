package com.bobo.service;

import com.bobo.dao.CustomerDao;
import com.bobo.entity.Customer;

public class CustomerServiceImpl implements CustomerService{
  private CustomerDao customerDao = new CustomerDao();

  @Override
  public Customer login(Customer customer) {
    String sql = "select * from customer where customer_no = ? and password = ?";
    return customerDao.querySingle(sql, Customer.class, customer.getCustomer_no(), customer.getPassword());
  }

  // @Override
  // public Customer selectById(String customer_no) {
  //   String sql = "select * from customer where customer_no = ? ";
  //   return customerDao.querySingle(sql, Customer.class, customer_no);
  // }
} 
