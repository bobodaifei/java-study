package com.example.dubbointerface.service;

import com.example.entity.User;

/**
 * @Author: chenpeng
 * @Datetime: 2020/7/12 15:33
 * @Version: V1.0
 */

public interface StudentService {

    Integer queryAllStudentCount();

    //查询用户

    //查询用户
    public User findUserById(int id) throws InterruptedException;
}