package com.bobo.study.work.qq_.qqClient.views;

import java.io.IOException;
import java.net.UnknownHostException;

import com.bobo.study.work.qq_.qqClient.userClient.FileClientService;
import com.bobo.study.work.qq_.qqClient.userClient.MessageClientService;
import com.bobo.study.work.qq_.qqClient.userClient.UserClientService;
import com.bobo.study.work.qq_.qqClient.utils.Utility;

public class QQView {

  public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException {
    new QQView().mainMenu();
  }

  private boolean loop = true;
  private String key = "";
  // 用户登录连接服务
  private UserClientService userClientService = new UserClientService();
  // 用户发送信息服务
  private MessageClientService messageClientService = new MessageClientService();
  // 用户发送文件服务
  private FileClientService fileClientService = new FileClientService();

  private void mainMenu() throws UnknownHostException, ClassNotFoundException, IOException {
    while (loop) {
      System.out.println("=================一级菜单");
      System.out.println("1 登录系统");
      System.out.println("9 退出系统");
      System.out.print("您的选择");
      key = Utility.readString(1);
      switch (key) {
        case "1":
          System.out.print("用户名：");
          String userId = Utility.readString(50);
          System.out.print("密码：");
          String pwd = Utility.readString(50);
          // 需要到服务端验证是否正确
          if (userClientService.checkUser(userId, pwd)) {
            System.out.println("========欢迎" + userId);
            while (loop) {
              System.out.println("============二级菜单");
              System.out.println("1 在线用户列表");
              System.out.println("2 群发消息");
              System.out.println("3 私聊消息");
              System.out.println("4 发送文件");
              System.out.println("9 退出系统");
              System.out.print("您的选择");
              key = Utility.readString(1);
              switch (key) {
                case "1":
                  userClientService.onlineFriendList();
                  break;
                case "2":
                  System.out.println("请输入要发送的信息：");
                  String content1 = Utility.readString(50);
                  messageClientService.sendMessageToAll(content1, userId);
                  break;
                case "3":
                  System.out.println("请输入一个在线用户号：");
                  String getterId = Utility.readString(50);
                  System.out.println("请输入要发送的信息：");
                  String content = Utility.readString(50);
                  messageClientService.sendMessageToOne(content, userId, getterId);
                  break;
                case "4":
                  System.out.println("发送文件的路径：");
                  String src = Utility.readString(50);
                  System.out.println("发送文件的名称：");
                  String fileName = Utility.readString(50);
                  System.out.println("发送给谁：");
                  getterId = Utility.readString(50);
                  System.out.println("发送文件的类型：");
                  String fileType = Utility.readString(50);
                  fileClientService.sendFileToOne(src, fileName, userId, getterId, fileType);
                  break;
                case "9":
                  userClientService.clientExit();
                  loop = false;
                  break;

                default:
                  break;
              }
            }

          } else {
            System.out.println("登陆失败");
          }
          break;
        case "9":
          loop = false;
          break;

        default:
          break;
      }

    }
  }
}
