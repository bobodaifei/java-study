package com.example.entity;

import java.io.Serializable;

//注意：将来所有的pojo都需要实现Serializable接口
public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //+set()与get()方法，和AllArgsConstructor
}
