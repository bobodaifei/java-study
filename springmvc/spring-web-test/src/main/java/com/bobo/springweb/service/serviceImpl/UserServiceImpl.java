package com.bobo.springweb.service.serviceImpl;

import com.bobo.springweb.dao.UserDao;
import com.bobo.springweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;
    @Override
    public void selectAll() {
        userdao.selectAll();
    }
}
