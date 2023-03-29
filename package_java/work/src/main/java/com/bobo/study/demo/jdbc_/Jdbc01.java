package com.bobo.study.demo.jdbc_;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class Jdbc01 {
  // 方式1 属于静态加载，灵活性差，依赖性强。
  

  public void connect01() throws SQLException {
    Driver driver = new Driver(); // 创建driver对象
    String url = "jdbc:mysql://localhost:3306/hsp_db02";
    // 将 用户名和密码放入到Properties 对象
    Properties properties = new Properties();
    // 说明 user 和 password 是规定好，后面的值根据实际情况写
    properties.setProperty("user", "root");// 用户
    properties.setProperty("password", "qq2194296863"); // 密码
    Connection connect = driver.connect(url, properties);
    System.out.println(connect);
  }

  // 方式2
  public void connect02() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
    // 使用反射加载Driver类 , 动态加载，更加的灵活，减少依赖性
    Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
    Driver driver;
    try {
      driver = (Driver) aClass.getConstructor().newInstance();
      String url = "jdbc:mysql://localhost:3306/hsp_db02";
      // 将 用户名和密码放入到Properties 对象
      Properties properties = new Properties();
      // 说明 user 和 password 是规定好，后面的值根据实际情况写
      properties.setProperty("user", "root");// 用户
      properties.setProperty("password", "qq2194296863"); // 密码

      Connection connect = driver.connect(url, properties);
      System.out.println("方式2=" + connect);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 方式3 使用DriverManager 替代 driver 进行统一管理
  public void connect03() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

    // 使用反射加载Driver
    Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
    Driver driver;
    try {
      driver = (Driver) aClass.getConstructor().newInstance();
      // 创建url 和 user 和 password
      String url = "jdbc:mysql://localhost:3306/hsp_db02";
      String user = "root";
      String password = "qq2194296863";

      DriverManager.registerDriver(driver);// 注册Driver驱动

      Connection connection = DriverManager.getConnection(url, user, password);
      System.out.println("第三种方式=" + connection);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    
  }

  // 方式4: 使用Class.forName 自动完成注册驱动，简化代码
  // 这种方式获取连接是使用的最多，推荐使用
  public void connect04() throws ClassNotFoundException, SQLException {
    // 使用反射加载了 Driver类
    // 在加载 Driver类时，完成注册
    /*
     * 源码: 1. 静态代码块，在类加载时，会执行一次.
     * 2. DriverManager.registerDriver(new Driver());
     * 3. 因此注册driver的工作已经完成
     * static {
     * try {
     * DriverManager.registerDriver(new Driver());
     * } catch (SQLException var1) {
     * throw new RuntimeException("Can't register driver!");
     * }
     * }
     */
    Class.forName("com.mysql.jdbc.Driver");

    // 创建url 和 user 和 password
    String url = "jdbc:mysql://localhost:3306/hsp_db02";
    String user = "root";
    String password = "hsp";
    Connection connection = DriverManager.getConnection(url, user, password);

    System.out.println("第4种方式~ " + connection);

  }

  // 方式5 , 在方式4的基础上改进，增加配置文件，让连接mysql更加灵活
  public void connect05() throws IOException, ClassNotFoundException, SQLException {

    // 通过Properties对象获取配置文件的信息
    Properties properties = new Properties();
    properties.load(new FileInputStream("src\\db.properties"));
    // 获取相关的值
    String user = properties.getProperty("user");
    String password = properties.getProperty("password");
    String driver = properties.getProperty("driver");
    String url = properties.getProperty("url");

    Class.forName(driver);// 建议写上 mysql5.1.6可以无需Class.forName

    Connection connection = DriverManager.getConnection(url, user, password);

    System.out.println("方式5 " + connection);

  }

}
