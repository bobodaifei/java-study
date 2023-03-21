package com.example.tomcat.entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class AbstractHttpServletResponse implements HttpServletResponse{

  @Override
  public void flushBuffer() throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'flushBuffer'");
  }

  @Override
  public int getBufferSize() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getBufferSize'");
  }

  @Override
  public String getCharacterEncoding() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCharacterEncoding'");
  }

  @Override
  public String getContentType() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getContentType'");
  }

  @Override
  public Locale getLocale() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLocale'");
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOutputStream'");
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getWriter'");
  }

  @Override
  public boolean isCommitted() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isCommitted'");
  }

  @Override
  public void reset() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'reset'");
  }

  @Override
  public void resetBuffer() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'resetBuffer'");
  }

  @Override
  public void setBufferSize(int arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setBufferSize'");
  }

  @Override
  public void setCharacterEncoding(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setCharacterEncoding'");
  }

  @Override
  public void setContentLength(int arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setContentLength'");
  }

  @Override
  public void setContentLengthLong(long arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setContentLengthLong'");
  }

  @Override
  public void setContentType(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setContentType'");
  }

  @Override
  public void setLocale(Locale arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setLocale'");
  }

  @Override
  public void addCookie(Cookie arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addCookie'");
  }

  @Override
  public void addDateHeader(String arg0, long arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addDateHeader'");
  }

  @Override
  public void addHeader(String arg0, String arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addHeader'");
  }

  @Override
  public void addIntHeader(String arg0, int arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addIntHeader'");
  }

  @Override
  public boolean containsHeader(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'containsHeader'");
  }

  @Override
  public String encodeRedirectURL(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'encodeRedirectURL'");
  }

  @Override
  public String encodeRedirectUrl(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'encodeRedirectUrl'");
  }

  @Override
  public String encodeURL(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'encodeURL'");
  }

  @Override
  public String encodeUrl(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'encodeUrl'");
  }

  @Override
  public String getHeader(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHeader'");
  }

  @Override
  public Collection<String> getHeaderNames() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHeaderNames'");
  }

  @Override
  public Collection<String> getHeaders(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHeaders'");
  }

  @Override
  public int getStatus() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
  }

  @Override
  public void sendError(int arg0) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendError'");
  }

  @Override
  public void sendError(int arg0, String arg1) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendError'");
  }

  @Override
  public void sendRedirect(String arg0) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendRedirect'");
  }

  @Override
  public void setDateHeader(String arg0, long arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setDateHeader'");
  }

  @Override
  public void setHeader(String arg0, String arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setHeader'");
  }

  @Override
  public void setIntHeader(String arg0, int arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setIntHeader'");
  }

  @Override
  public void setStatus(int arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
  }

  @Override
  public void setStatus(int arg0, String arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
  }
  
}
