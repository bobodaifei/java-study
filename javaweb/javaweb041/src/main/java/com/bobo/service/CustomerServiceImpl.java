package com.bobo.service;

import com.bobo.dao.CustomerDao;
import com.bobo.entity.Customer;
import com.bobo.utils.JdbcUtil;

public class CustomerServiceImpl implements CustomerService{
  private CustomerDao customerDao = new CustomerDao();

  @Override
  public Customer login(Customer customer) {
    Customer res = customerDao.login(customer);
    JdbcUtil.close();
    return res; 
  }

} 
