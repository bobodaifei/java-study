package com.bobo.study.work.work01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StaffDao {
  public static void main(String[] args) throws ParseException {
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
    // System.out.println(dao.delete(1));
    // System.out.println(dao.add(new Staff01(6, "bobo1", 10, new Date())));
    // System.out.println(dao.updateRow(new Staff01(7, "bobo1", 10, new Date())));
    // dao.getByDepartId(15);
    // dao.getByInTimeId(new SimpleDateFormat("yyyy-MM-dd
    // HH:mm:ss").parse("2023-03-09 13:48:57"));
    // System.out.println(dao.getByDepartNameTotal("部门2"));
    dao.getByStaffNameToOther("bobo2");

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
        Date staffInTime = resultSet.getTimestamp("STAFF_INTIME");
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
        Date staffInTime = resultSet.getTimestamp("STAFF_INTIME");
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
        Date staffInTime = resultSet.getTimestamp("STAFF_INTIME");
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

  // 3. 封装一个方法，根据员工编号删除员工，接收员工编号，删除成功返回true,删除失败返回false
  public boolean delete(int Id) {

    Connection connection = null;
    Statement statement = null;
    try {
      connection = JdbcUtil.getConnection();
      statement = connection.createStatement();
      int flag = statement.executeUpdate("delete from STAFF where STAFF_ID = " + Id);
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

  // 4. 封装一个方法，添加一个员工，添加成功返回true，添加失败返回false
  public boolean add(Staff01 staff) {

    Connection connection = null;
    Statement statement = null;
    PreparedStatement ps = null;
    try {
      connection = JdbcUtil.getConnection();

      String sql = "INSERT INTO STAFF  VALUES (?,?,?,?)";
      ps = connection.prepareStatement(sql);
      ps.setInt(1, staff.getStaffId());
      ps.setString(2, staff.getStaffName());
      ps.setInt(3, staff.getStaffDepartId());
      ps.setTimestamp(4, new Timestamp(staff.getStaffInTime().getTime()));
      int flag = ps.executeUpdate();
      if (flag > 0) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      JdbcUtil.close(connection, ps, null);
    }
  }

  // 5.封装一个方法，修改一个员工的信息。返回受影响的行数。
  public int updateRow(Staff01 staff) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement ps = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      String sql = "update STAFF set STAFF_NAME = ? ,STAFF_DEPART_ID=?, STAFF_INTIME=?  where STAFF_ID = ?";
      ps = connection.prepareStatement(sql);

      ps.setString(1, staff.getStaffName());
      ps.setInt(2, staff.getStaffDepartId());
      ps.setTimestamp(3, new Timestamp(staff.getStaffInTime().getTime()));
      ps.setInt(4, staff.getStaffId());
      int flag = ps.executeUpdate();
      return flag;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    } finally {
      JdbcUtil.close(connection, ps, resultSet);
    }
  }

  // 6.封装一个方法，根据部门编号查询员工信息，接收部门编号。
  public void getByDepartId(int Id) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      // 查询query，返回一个数据集
      resultSet = statement.executeQuery("SELECT * FROM STAFF WHERE STAFF_DEPART_ID = " + Id);
      while (resultSet.next()) {
        int staffId = resultSet.getInt("STAFF_ID");
        String staffName = resultSet.getString("STAFF_NAME");
        int staffDepartId = resultSet.getInt("STAFF_DEPART_ID");
        Date staffInTime = resultSet.getTimestamp("STAFF_INTIME");
        // 输出对象
        System.out.println(new Staff01(staffId, staffName, staffDepartId, staffInTime));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close(connection, statement, resultSet);
    }
  }

  // 7.封装一个方法，查询入职时间早于输入的入职时间的员工信息，接收入职时间。
  public void getByInTimeId(Date date) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement ps = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      String sql = "SELECT * FROM STAFF WHERE STAFF_INTIME < ?";
      ps = connection.prepareStatement(sql);

      ps.setTimestamp(1, new Timestamp(date.getTime()));
      resultSet = ps.executeQuery();

      while (resultSet.next()) {
        int staffId = resultSet.getInt("STAFF_ID");
        String staffName = resultSet.getString("STAFF_NAME");
        int staffDepartId = resultSet.getInt("STAFF_DEPART_ID");
        Date staffInTime = resultSet.getTimestamp("STAFF_INTIME");
        // 输出对象
        System.out.println(new Staff01(staffId, staffName, staffDepartId, staffInTime));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close(connection, statement, resultSet);
    }
  }

  // 8.统计部门人数
  public int getByDepartNameTotal(String name) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement ps = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      String sql = "select count(*) as count_ from STAFF group by STAFF_DEPART_ID having STAFF_DEPART_ID=(select DEPART_ID from DEPART where DEPART_NAME = ?)";
      ps = connection.prepareStatement(sql);

      ps.setString(1, name);
      resultSet = ps.executeQuery();

      int count = 0;
      if (resultSet == null) {
        return count;
      }

      while (resultSet.next()) {
        count = resultSet.getInt("count_");
      }
      return count;
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      JdbcUtil.close(connection, statement, resultSet);
    }
  }

  // 9.封装一个方法，根据员工姓名，查询从事同一个工作的员工，接收员工姓名。
  public void getByStaffNameToOther(String name) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement ps = null;
    try {
      connection = JdbcUtil.getConnection();

      statement = connection.createStatement();

      String sql = "select * from STAFF where STAFF_DEPART_ID in (select STAFF_DEPART_ID from STAFF where STAFF_NAME =?)";
      ps = connection.prepareStatement(sql);

      ps.setString(1, name);
      resultSet = ps.executeQuery();

      while (resultSet.next()) {
        int staffId = resultSet.getInt("STAFF_ID");
        String staffName = resultSet.getString("STAFF_NAME");
        int staffDepartId = resultSet.getInt("STAFF_DEPART_ID");
        Date staffInTime = resultSet.getTimestamp("STAFF_INTIME");
        // 新建一个对象
        System.out.println(new Staff01(staffId, staffName, staffDepartId, staffInTime));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close(connection, statement, resultSet);
    }
  }

}