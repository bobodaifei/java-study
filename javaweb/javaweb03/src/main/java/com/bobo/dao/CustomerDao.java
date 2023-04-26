package com.bobo.dao;

import com.bobo.entity.Customer;

public class CustomerDao extends BasicDao<Customer> {
  public Customer login(Customer customer) {
    String sql = "select * from customer where customer_no = ? and password = ?";
    return querySingle(sql, Customer.class, customer.getCustomer_no(), customer.getPassword());
  }

  public Customer selectById(String customer_no) {
    String sql = "select * from customer where customer_no = ? ";
    return querySingle(sql, Customer.class, customer_no);
  }
}
