package com.bobo.utils.http;

public interface Constants {
  public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;

  public static final String CRLF = "\r\n";

  public static final byte CR = (byte) '\r';

  public static final byte LF = (byte) '\n';

  public static final byte SP = (byte) ' ';

  public static final byte HT = (byte) '\t';

  public static final byte COLON = (byte) ':';

  public static final byte SEMI_COLON = (byte) ';';

  public static final byte QUESTION = (byte) '?';

  public static final String HTTP_10 = "HTTP/1.0";

  public static final String HTTP_11 = "HTTP/1.1";
}
