package com.bobo.study.demo.work01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StaffDao {
  public static void main(String[] args) {
    StaffDao dao = new StaffDao();
    // 1.封装一个方法，根据员工编号查询员工全部信息。接收员工编号。
    // dao.getById(1);
    // 2.封装一个方法，根据部门编号查询全部员工。接收部门编号。分别用集合和数组返回数据。
    // System.out.println(dao.getByDepartIdList(1));
    // 2.封装一个方法，根据部门编号查询全部员工。接收部门编号。分别用集合和数组返回数据。
    // for (Staff01 staff01 : dao.getByDepartIdArr(1)) {
    // if (staff01==null) {
    // break;
    // }
    // System.out.println(staff01);
    // }
    System.out.println(dao.delete(1));
    System.out.println(dao.add(new Staff01(1, "bobo1", 10, new Date())));

  }

  // 1.封装一个方法，根据员工编号查询员工全部信息。接收员工编号。
  public void getById(int Id) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      // 查询query，返回一个数据集
      resultSet = statement.executeQuery("SELECT * FROM STAFF WHERE staff_id = " + Id);
      while (resultSet.next()) {
        int staffId = resultSet.getInt("STAFF_ID");
        String staffName = resultSet.getString("STAFF_NAME");
        int staffDepartId = resultSet.getInt("STAFF_DEPART_ID");
        Date staffInTime = resultSet.getDate("STAFF_INTIME");
        // 新建一个对象
        System.out.println(new Staff01(staffId, staffName, staffDepartId, staffInTime));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close(connection, statement, resultSet);
    }
  }

  // 2.封装一个方法，根据部门编号查询全部员工。接收部门编号。分别用集合和数组返回数据。
  public List getByDepartIdList(int Id) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    ArrayList<Staff01> list = new ArrayList<Staff01>();
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      // 查询query，返回一个数据集
      resultSet = statement.executeQuery("SELECT * FROM STAFF WHERE STAFF_DEPART_ID = " + Id);

      while (resultSet.next()) {
        int staffId = resultSet.getInt("STAFF_ID");
        String staffName = resultSet.getString("STAFF_NAME");
        int staffDepartId = resultSet.getInt("STAFF_DEPART_ID");
        Date staffInTime = resultSet.getDate("STAFF_INTIME");
        list.add(new Staff01(staffId, staffName, staffDepartId, staffInTime));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close(connection, statement, resultSet);
    }
    return list;
  }

  // 2.封装一个方法，根据部门编号查询全部员工。接收部门编号。分别用集合和数组返回数据。
  public Staff01[] getByDepartIdArr(int Id) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    Staff01[] arr = {};
    int index = 0;

    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      // 查询query，返回一个数据集
      resultSet = statement.executeQuery("SELECT * FROM STAFF WHERE STAFF_DEPART_ID = " + Id);

      while (resultSet.next()) {
        int staffId = resultSet.getInt("STAFF_ID");
        String staffName = resultSet.getString("STAFF_NAME");
        int staffDepartId = resultSet.getInt("STAFF_DEPART_ID");
        Date staffInTime = resultSet.getDate("STAFF_INTIME");
        if (index >= arr.length - 1) {
          arr = Arrays.copyOf(arr, arr.length + 5);
        }

        arr[index++] = new Staff01(staffId, staffName, staffDepartId, staffInTime);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close(connection, statement, resultSet);
    }
    return arr;
  }

  //3. 封装一个方法，根据员工编号删除员工，接收员工编号，删除成功返回true,删除失败返回false
  public boolean delete(int Id) {

    Connection connection = null;
    Statement statement = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      int flag = statement.executeUpdate("delete from STAFF where STAFF_ID = " + Id);
      if (flag>0) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      JdbcUtil.close(connection, statement, null);
    }
  }

  //4. 封装一个方法，添加一个员工，添加成功返回true，添加失败返回false
  public boolean add(Staff01 staff) {

    Connection connection = null;
    Statement statement = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      int flag = statement.executeUpdate("INSERT INTO STAFF  VALUES ("+staff.getStaffId()+", " + staff.getStaffName()
          + ", " + staff.getStaffDepartId() + ", " + staff.getStaffInTime() + ")");
      if (flag > 0) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      JdbcUtil.close(connection, statement, null);
    }
  }

  // /**
  // * 添加一个
  // *
  // * @param student
  // */
  // public void save(Staff staff) {
  // String name = staff.getName();
  // int num = student.getNum();
  // Connection connection = null;
  // Statement statement = null;
  // try {
  // connection = JdbcUtil.getConnection();

  // statement = connection.createStatement();

  // // 拼接SQL语句，把数据插入到数据库中
  // statement.execute("insert into STUDENT value(" + num + "," + name + ") ");
  // } catch (SQLException e) {
  // e.printStackTrace();
  // } finally {
  // JdbcUtil.close(connection, statement, null);
  // }
  // }

  // public void update(Student student) {

  // }

  // /**
  // * 删除指定编号的学生
  // *
  // * @param num
  // */
  // public void delete(Integer num) {

  // Connection connection = null;
  // Statement statement = null;
  // try {
  // connection = JdbcUtil.getConnection();

  // statement = connection.createStatement();

  // // 拼接SQL语句，把数据插入到数据库中
  // boolean flag = statement.execute("delete from STUDENT where num = " + num);
  // if (flag) {
  // System.out.println("删除成功！");
  // } else {
  // System.out.println("删除失败！");
  // }
  // } catch (SQLException e) {
  // e.printStackTrace();
  // } finally {
  // JdbcUtil.close(connection, statement, null);
  // }
  // }

  // public void findByPK(Integer number) {

  // Connection connection = null;
  // Statement statement = null;
  // ResultSet resultSet = null;
  // try {
  // connection = JdbcUtil.getConnection();

  // statement = connection.createStatement();

  // // 查询query，返回一个数据集
  // resultSet = statement.executeQuery("select * from STUDENT where num =" +
  // number);
  // while (resultSet.next()) {
  // int num = resultSet.getInt("num");
  // String name = resultSet.getString("name");
  // String sex = resultSet.getString("sex");
  // int age = resultSet.getInt("age");
  // Date birthdate = resultSet.getDate("birthdate");
  // double grade = resultSet.getDouble("grade");
  // // 新建一个对象
  // // new Student(num, age, grade, name, sex, birthdate);

  // }
  // } catch (SQLException e) {
  // e.printStackTrace();
  // } finally {
  // JdbcUtil.close(connection, statement, resultSet);
  // }
  // }

  // /**
  // * 查询全部
  // *
  // * @return
  // */
  // public List<Student> findAll() {
  // List<Student> list = new ArrayList<>();
  // Connection connection = null;
  // Statement statement = null;
  // ResultSet resultSet = null;
  // try {
  // connection = JdbcUtil.getConnection();

  // statement = connection.createStatement();

  // // 查询query，返回一个数据集
  // resultSet = statement.executeQuery("select * from STUDENT");
  // while (resultSet.next()) {
  // int num = resultSet.getInt("num");
  // String name = resultSet.getString("name");
  // String sex = resultSet.getString("sex");
  // int age = resultSet.getInt("age");
  // Date birthdate = resultSet.getDate("birthdate");
  // double grade = resultSet.getDouble("grade");
  // // 新建一个对象
  // // list.add(new Student(num, age, grade, name, sex, birthdate));
  // list.add(new Student());
  // }
  // } catch (SQLException e) {
  // e.printStackTrace();
  // } finally {
  // JdbcUtil.close(connection, statement, resultSet);
  // }
  // return list;
  // }

}