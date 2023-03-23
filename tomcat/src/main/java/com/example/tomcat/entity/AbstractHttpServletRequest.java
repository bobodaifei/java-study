package com.example.tomcat.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

public class AbstractHttpServletRequest implements HttpServletRequest{

  @Override
  public AsyncContext getAsyncContext() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAsyncContext'");
  }

  @Override
  public Object getAttribute(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAttribute'");
  }

  @Override
  public Enumeration<String> getAttributeNames() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAttributeNames'");
  }

  @Override
  public String getCharacterEncoding() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCharacterEncoding'");
  }

  @Override
  public int getContentLength() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getContentLength'");
  }

  @Override
  public long getContentLengthLong() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getContentLengthLong'");
  }

  @Override
  public String getContentType() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getContentType'");
  }

  @Override
  public DispatcherType getDispatcherType() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getDispatcherType'");
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getInputStream'");
  }

  @Override
  public String getLocalAddr() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLocalAddr'");
  }

  @Override
  public String getLocalName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLocalName'");
  }

  @Override
  public int getLocalPort() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLocalPort'");
  }

  @Override
  public Locale getLocale() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLocale'");
  }

  @Override
  public Enumeration<Locale> getLocales() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLocales'");
  }

  @Override
  public String getParameter(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getParameter'");
  }

  @Override
  public Map<String, String[]> getParameterMap() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getParameterMap'");
  }

  @Override
  public Enumeration<String> getParameterNames() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getParameterNames'");
  }

  @Override
  public String[] getParameterValues(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getParameterValues'");
  }

  @Override
  public String getProtocol() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getProtocol'");
  }

  @Override
  public BufferedReader getReader() throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getReader'");
  }

  @Override
  public String getRealPath(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRealPath'");
  }

  @Override
  public String getRemoteAddr() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRemoteAddr'");
  }

  @Override
  public String getRemoteHost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRemoteHost'");
  }

  @Override
  public int getRemotePort() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRemotePort'");
  }

  @Override
  public RequestDispatcher getRequestDispatcher(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRequestDispatcher'");
  }

  @Override
  public String getScheme() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getScheme'");
  }

  @Override
  public String getServerName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getServerName'");
  }

  @Override
  public int getServerPort() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getServerPort'");
  }

  @Override
  public ServletContext getServletContext() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getServletContext'");
  }

  @Override
  public boolean isAsyncStarted() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isAsyncStarted'");
  }

  @Override
  public boolean isAsyncSupported() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isAsyncSupported'");
  }

  @Override
  public boolean isSecure() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isSecure'");
  }

  @Override
  public void removeAttribute(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'removeAttribute'");
  }

  @Override
  public void setAttribute(String arg0, Object arg1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setAttribute'");
  }

  @Override
  public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setCharacterEncoding'");
  }

  @Override
  public AsyncContext startAsync() throws IllegalStateException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'startAsync'");
  }

  @Override
  public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) throws IllegalStateException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'startAsync'");
  }

  @Override
  public boolean authenticate(HttpServletResponse arg0) throws IOException, ServletException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
  }

  @Override
  public String changeSessionId() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'changeSessionId'");
  }

  @Override
  public String getAuthType() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAuthType'");
  }

  @Override
  public String getContextPath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getContextPath'");
  }

  @Override
  public Cookie[] getCookies() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCookies'");
  }

  @Override
  public long getDateHeader(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getDateHeader'");
  }

  @Override
  public String getHeader(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHeader'");
  }

  @Override
  public Enumeration<String> getHeaderNames() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHeaderNames'");
  }

  @Override
  public Enumeration<String> getHeaders(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHeaders'");
  }

  @Override
  public int getIntHeader(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getIntHeader'");
  }

  @Override
  public String getMethod() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMethod'");
  }

  @Override
  public Part getPart(String arg0) throws IOException, ServletException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPart'");
  }

  @Override
  public Collection<Part> getParts() throws IOException, ServletException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getParts'");
  }

  @Override
  public String getPathInfo() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPathInfo'");
  }

  @Override
  public String getPathTranslated() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPathTranslated'");
  }

  @Override
  public String getQueryString() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getQueryString'");
  }

  @Override
  public String getRemoteUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRemoteUser'");
  }

  @Override
  public String getRequestURI() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRequestURI'");
  }

  @Override
  public StringBuffer getRequestURL() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRequestURL'");
  }

  @Override
  public String getRequestedSessionId() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRequestedSessionId'");
  }

  @Override
  public String getServletPath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getServletPath'");
  }

  @Override
  public HttpSession getSession() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSession'");
  }

  @Override
  public HttpSession getSession(boolean arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSession'");
  }

  @Override
  public Principal getUserPrincipal() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUserPrincipal'");
  }

  @Override
  public boolean isRequestedSessionIdFromCookie() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isRequestedSessionIdFromCookie'");
  }

  @Override
  public boolean isRequestedSessionIdFromURL() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isRequestedSessionIdFromURL'");
  }

  @Override
  public boolean isRequestedSessionIdFromUrl() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isRequestedSessionIdFromUrl'");
  }

  @Override
  public boolean isRequestedSessionIdValid() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isRequestedSessionIdValid'");
  }

  @Override
  public boolean isUserInRole(String arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isUserInRole'");
  }

  @Override
  public void login(String arg0, String arg1) throws ServletException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }

  @Override
  public void logout() throws ServletException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'logout'");
  }

  @Override
  public <T extends HttpUpgradeHandler> T upgrade(Class<T> arg0) throws IOException, ServletException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'upgrade'");
  }
  
}
