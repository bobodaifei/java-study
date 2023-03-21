package com.bobo.demo.qq_.qqCommon;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
  private static final long SerializableUID = 1L;
  private String sender;
  private String getter;
  private String content;// 内容
  private Date sendTime;
  private String messageType;// 消息类型

  // 文件扩展
  private byte[] fileBytes; //文件byte
  private int fileLen;// 文件长度
  private String fileType;// 文件类型
  private String fileName;// 文件名

  public Message() {
  }

  public Message(String sender, String getter, String content, Date sendTime, String messageType) {
    this.sender = sender;
    this.getter = getter;
    this.content = content;
    this.sendTime = sendTime;
    this.messageType = messageType;
  }

  public static long getSerializableuid() {
    return SerializableUID;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getGetter() {
    return getter;
  }

  public void setGetter(String getter) {
    this.getter = getter;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public byte[] getFileBytes() {
    return fileBytes;
  }

  public void setFileBytes(byte[] fileBytes) {
    this.fileBytes = fileBytes;
  }

  public int getFileLen() {
    return fileLen;
  }

  public void setFileLen(int fileLen) {
    this.fileLen = fileLen;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

}
