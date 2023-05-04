package com.bobo.dao;

import com.bobo.entity.Log_;

public class LogDao extends BasicDao<Log_>{
  public int add(Log_ log) {
    String sql = "insert into log(empno,ope_type,create_time,code,message) values(?,?,?,?,?)";
    return update(sql, log.getEmpno(), log.getOpe_type(), log.getCreate_time(), log.getCode(), log.getMessage());
  }
}
