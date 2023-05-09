package com.bobo.service;

import com.bobo.dao.CustomerDao;
import com.bobo.entity.Customer;

public class CustomerServiceImpl implements CustomerService{
  private CustomerDao customerDao = new CustomerDao();

  @Override
  public Customer login(Customer customer) {
    return customerDao.login(customer);
  }

  @Override
  public Customer selectById(String customer_no) {
    return customerDao.selectById(customer_no);
  }

} 
