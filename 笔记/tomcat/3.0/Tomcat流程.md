# Tomcat

## 1.tomcat启动前的准备

### 1.1启动main方法

```java
public static void main(String[] args) {
    Tomcat tomcat = new Tomcat();
    // 以xml形式扫描加载Servlet
    tomcat.initApps();
    tomcat.start();
  }
```

### 1.2扫描webapps目录

1. 扫描所有的web应用，存储到一个 `Map<String, Context>`的集合中
2. 为每个web应用创建一个 `Context`对象
3. 解析xml
4. 找到与 `url-pattern`对应的 `servlet-class`，存入到 `Context`对象的**声明集合**中
5. 判断该servlet是否存在初始化参数，同时创建 `ServletConfig`。如果初始化参数存在，则将其存入 `ServletConfig`中，并存入到 `Context`对象的**配置集合**中
6. 判断该servlet是否是懒加载，如果不是，则通过反射实例化存入到 `Context`对象实例化**集合**中

```java
public void initApps() {
    // 通过dom4j的方式读取xml
    try {
      File file = new File(System.getProperty("user.dir"), "webapps");
      // 扫描全部web应用
      for (String app : file.list()) {
        initApp(file, app);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void initApp(File webapps, String appName) throws DocumentException {
    // 获取web应用的文件夹
    File appDirectory = new File(webapps, appName);
    // 获取xml所在目录
    File webInfDirectory = new File(appDirectory, "WEB-INF");
    // 获取classes目录
    File classesDirectory = new File(webInfDirectory, "classes");
    // 每一个web应用的context对象
    Context context = new Context(appName, classesDirectory);
    // 获取读取器
    SAXReader saxReader = new SAXReader();

    // xml文档总模型
    Document document = saxReader.read(webInfDirectory.getAbsolutePath() + "/web.xml");
    // 获取全部<servlet-mapping>
    List<Node> idNoList = document.selectNodes("//servlet-mapping");
    for (Node node : idNoList) {
      Element idElement = (Element) node;
      // 获取<servlet-name>
      Element idElementName = (Element) idElement.selectSingleNode("./servlet-name");
      // 获取全部<url-pattern>
      Element idElementUrlPattern = (Element) idElement.selectSingleNode("./url-pattern");
      // 获取全部<servlet> 用来映射类
      List<Node> idNoList2 = document.selectNodes("//servlet");
      for (Node node2 : idNoList2) {
        Element idElement2 = (Element) node2;
        // 获取<servlet-name>
        Element idElement2Name = (Element) idElement2.selectSingleNode("./servlet-name");
        // 获取<servlet-class>
        Element idElement2Class = (Element) idElement2.selectSingleNode("./servlet-class");
        // 如果对应则加入声明map
        if (idElementName.getText().equals(idElement2Name.getText())) {
          context.addDefinitionMap(idElementUrlPattern.getText(), idElement2Class.getText());

          // 加载参数
          List<Node> idNoList3 = idElement2.selectNodes("./init-param");
          ServletConfig servletConfig = new ServletConfig();
          if (idNoList3 != null) {
            for (Node node3 : idNoList3) {
              Element idElement3 = (Element) node3;
              Element idElement3ParamName = (Element) idElement3.selectSingleNode("./param-name");
              Element idElement3ParamValue = (Element) idElement3.selectSingleNode("./param-value");
              servletConfig.addInitParam(idElement3ParamName.getText(), idElement3ParamValue.getText());
            }
          }

          // 是否为快加载
          Element idElementLoad = (Element) idElement2.selectSingleNode("./load-on-startup");
          String regEx = "[1-5]";
          if (!(idElementLoad == null || !PatternUtils.contains(idElementLoad.getText(), regEx))) {
            try {
              // 自定义路径的类加载器
              context.addBean(idElementUrlPattern.getText(),idElement2Class.getText());
            } catch (Exception e) {
              e.printStackTrace();
            }
          }

        }
      }
    }
    System.out.println("app:" + appName + "准备就绪");
    // 维护全部的web应用
    contextMap.put(appName, context);
  
  }
```

### 1.3使用线程池等待连接

1. 创建大小为20的线程池
2. accept()拿到连接后，利用多线程执行当前操作，并紧接着等待下一个连接

```java
public void start() {
    try {
      // 创建20个连接的线程池
      ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(20);
      ServerSocket serverSocket = new ServerSocket(8888);
      while (true) {
        System.out.println("等待连接");
        Socket socket = serverSocket.accept();
        newFixedThreadPool.execute(new SocketThread(socket, this));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
```

## 2.获取连接后进行处理

```java
public class SocketThread implements Runnable {
  private Socket socket;
  private Tomcat tomcat;

  public SocketThread(Socket socket, Tomcat tomcat) {
    this.socket = socket;
    this.tomcat = tomcat;
  }

  @Override
  public void run() {
    try {
      // 解析请求
      HttpServletRequest request = new HttpServletRequest(socket.getInputStream());
      // 此处需要使用request的socket的输出流、request的http协议版本
      HttpServletResponse response = new HttpServletResponse(socket.getOutputStream());
      String[] parts;
      String uri = request.getRequestURI();
      if (uri.contains("/favicon.ico")) {
        parts = new String[1];
        parts[0] = "/favicon.ico";
      } else {
        parts = uri.substring(1).split("/");
      }
      // 对url进行分块：web应用名、serlvet名、参数
      // 只有web应用名，没有对应servlet，不做操作
      if (parts.length > 1) {
        // 获取要访问的应用名
        String appName = parts[0];
        // 返回应用下的全部servlet的Context的map集合
        Context context = tomcat.getContextMap().get(appName);
        String urlPattern = "/" + parts[1];
        Servlet servlet = context.getUrlPatternMapping(urlPattern);
        // 找不到servlet的时候，去DefinitionMap查看是否已经注册
        if (servlet == null) {
          String fullyName = context.getDefinitionMap(urlPattern);
          //如果没有则使用默认Servlet
          if (StrUtil.isBlank(fullyName)) {
            servlet = new DefaultServlet();
          } else {//如果有则实例化并调用service
            servlet = context.addBean(urlPattern, fullyName);
          }
        }
        servlet.service(request, response);
        // 真正的发送响应
        response.complete();

        /*
         * 1.自定义配置文件格式 properties xml
         * 2.服务器启动的时候就加载配置文件，然后创建容器将对象都创建出来。放到容器中
         * 3.根据请求协议的请求路径通过反射区调用，对应的对象的service方法。
         * //
         */
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally{
      try {
        socket.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
```

### 2.1解析请求协议

1. 传入socket的inputStream，对输入流进行解析
2. 利用BufferedReader逐行读取请求行和请求头
3. 解析请求行获得请求方式、uri、协议版本
4. 尝试解析请求体：GET请求无请求体，POST请求的请求体不一定有内容，最终通过 `content-length`来获取固定长度的请求体
5. 将请求体加入ServletInputStream的缓存中
6. 在解析请求体时如果是FORM表单提交的POST请求，则尝试解析里面的参数，加入到 `parameters`集合中

```java
public HttpServletRequest(InputStream inputStream) throws IOException {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    //获取请求行和请求头
    StringBuffer stringBuffer = new StringBuffer();
    String str = null;
    while (StrUtil.isNotEmpty(str = bufferedReader.readLine())) {
      stringBuffer.append(str + "\r\n");
    }

    //解析请求行和请求头
    parseRequest(stringBuffer.toString());

    //尝试解析请求体
    String conLength = getHeader("content-length");
    int length = 0;
    byte[] bytes;
    //如果content-length不为为0和空，表示将请求体缓存
    if (StrUtil.isNotBlank(conLength) && ((length = Integer.valueOf(conLength)) > 0)) {
      bytes = new byte[length];
      for (int i = 0; i < length; i++) {
        bytes[i] = (byte) bufferedReader.read();
      }
      //如果是form表单提交的post请求，则尝试解析请求体的参数
      String conType = getHeader("Content-type");
      if (StrUtil.isNotBlank(conType)&& conType.equals("application/x-www-form-urlencoded")) {
        parseParameter(new String(bytes));
      }
    } else {//缓存赋空
      bytes = new byte[0];
    }
    servletInputStream.setBytes(bytes);
  }

 private void parseLine(String line) {
    // 分割请求行
    String[] lineArr = line.split(" ");
    // 获取请求方法
    this.method = lineArr[0];
    // 协议版本
    this.protocol = lineArr[2];
    // 获取请求路径部分
    String uri_query = lineArr[1];
    // 判断是否存在参数
    if (uri_query.contains("?")) {
      // 分割uri和参数部分
      String[] uriQuerysArr = uri_query.split("\\?");
      // 获取uri
      this.uri = uriQuerysArr[0];
      // 分割出参数部分
      String querys = uriQuerysArr[1];
      //处理k=v参数
      parseParameter(querys);
    } else {// 不存在参数
      this.uri = uri_query;
    }
  }

  // 解析请求头
  private void parseHeader(String headerStr) {
    String[] headerArr = headerStr.split("\r\n");
    for (String header : headerArr) {
      String[] key_value = header.split(":");
      String key = key_value[0].trim();
      String value = key_value[1].trim();
      if ("Cookie".equals(key)) {
        String[] cookiesArr = value.split(";");
        for (String cookie : cookiesArr) {
          String[] cookieArr = cookie.split("=");
          String cKey = cookieArr[0].trim();
          String cValue = cookieArr[1].trim();
          this.cookies.add(new Cookie(cKey, cValue));
        }
      } else {
        this.headers.put(key, value);
      }
    }
  }

  //解析请求参数
  public void parseParameter(String querys) {
    String[] querysArr = querys.split("\\&");
    // 将参数存入parameters
    for (String query : querysArr) {
      String[] key_value = query.split("\\=");
      String key = key_value[0];
      String value = key_value[1];
      String[] strings = parameters.get(key);
      List<String> list = null;
      // 判断将参数在parameters是否存在（多选）
      if (!(strings == null || strings.length == 0)) {
        list = new ArrayList<>(Arrays.asList(strings));
      } else {
        list = new ArrayList<>();
      }
      list.add(value);
      // 存入
      parameters.put(key, list.toArray(new String[list.size()]));
    }
  }
```

### 2.2准备响应协议

1. 传入socket的outputStream，为真正的响应做准备

```java
public HttpServletResponse(OutputStream outputStream) throws IOException {
    this.socketOutPutStream = outputStream;
  }
```

### 2.3调用Servlet

1. 通过请求协议的uri来寻找对应的Servlet
2. 首先查看实例化**集合**中是否存在Servlet。如果实例化**集合**中存在，则直接调用service方法，
3. 如果不存在，再判断声明集合中是否存在，如果升声明集合中不存在，则说明无此Servlet，此时调用默认的Servlet进行处理
4. 如果存在，说明该Servlet为懒加载，通过反射实例化后加入到实例化**集合**中，并调用其service方法

```java
// 返回应用下的全部servlet的Context的map集合
        Context context = tomcat.getContextMap().get(appName);
        String urlPattern = "/" + parts[1];
        Servlet servlet = context.getUrlPatternMapping(urlPattern);
        // 找不到servlet的时候，去DefinitionMap查看是否已经注册
        if (servlet == null) {
          String fullyName = context.getDefinitionMap(urlPattern);
          //如果没有则使用默认Servlet
          if (StrUtil.isBlank(fullyName)) {
            servlet = new DefaultServlet();
          } else {//如果有则实例化并调用service
            servlet = context.addBean(urlPattern, fullyName);
          }
        }
        servlet.service(request, response);
```

### 2.4响应结果

1. 此处我们已经调用完servlet了，通过StringBuffer拼接响应体
2. 最终传入的socket的outputStream返回响应协议

```java
public void complete() throws IOException {
    if (writer != null) {
      writer.flush();
    }
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(sendResponseLine());
    stringBuffer.append(sendResponseHeader());
    stringBuffer.append(sendResponseBody());
    System.out.println(stringBuffer.toString());
    socketOutPutStream.write(stringBuffer.toString().getBytes());
    try {
      if (writer != null) {
        writer.close();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  //拼接响应行
  private String sendResponseLine() {
    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append("HTTP/1.1");
    stringBuffer.append(" ");
    stringBuffer.append(status + "");
    stringBuffer.append(" ");
    stringBuffer.append(message);
    stringBuffer.append("\r\n");
    return stringBuffer.toString();
  }

  //拼接响应头
  private String sendResponseHeader() throws IOException {
    StringBuffer stringBuffer = new StringBuffer();

    if (!headers.containsKey("Content-Length")) {
      setHeader("Content-Length", servletOutputStream.getPos() + "");
    }
    if (!headers.containsKey("Content-Type")) {
      setHeader("Content-Type", "text/plain;charset=utf-8");
    }

    Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String, String> next = iterator.next();
      String key = next.getKey();
      String value = next.getValue();
      stringBuffer.append(key);
      stringBuffer.append(":");
      stringBuffer.append(value);
      stringBuffer.append("\r\n");
    }
    if (cookies.size() > 0) {
      Iterator<Cookie> iterator2 = cookies.iterator();
      while (iterator2.hasNext()) {
        Cookie cookie = iterator2.next();

        stringBuffer.append("set-cookie");
        stringBuffer.append(":");
        stringBuffer.append(cookie.getName());
        stringBuffer.append("=");
        stringBuffer.append(cookie.getValue());
        int maxAge = cookie.getMaxAge();
        if (maxAge != -1) {
          stringBuffer.append(";");
          stringBuffer.append(" ");
          stringBuffer.append("expires");
          stringBuffer.append("=");
          stringBuffer.append(new Date(new Date().getTime() + maxAge).toString());
        }
      }
      stringBuffer.append("\r\n");
    }
    stringBuffer.append("\r\n");
   

    return stringBuffer.toString();
  }

  //拼接响应体
  private String sendResponseBody() {
    return new String(servletOutputStream.getBytes(), 0, servletOutputStream.getPos());
  }
```

## 3.流程外的重点

### 3.1HttpServlet的抽象类

1. service重载：
2. `service(ServletRequest req, ServletResponse res)`判断传入的请求和响应类型是否正确
3. `service(HttpServletRequest req, HttpServletResponse resp)`判断需要调用哪个方法

```java
public abstract class HttpServlet extends GenericServlet {

  private static final String METHOD_DELETE = "DELETE";
  private static final String METHOD_GET = "GET";
  private static final String METHOD_POST = "POST";
  private static final String METHOD_PUT = "PUT";

  public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //
  }

  public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //
  }

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //
  }

  public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getMethod();
    if (method.equals(METHOD_GET)) {
      doGet(req, resp);
    } else if (method.equals(METHOD_POST)) {
      doPost(req, resp);
    } else if (method.equals(METHOD_PUT)) {
      doPut(req, resp);
    } else if (method.equals(METHOD_DELETE)) {
      doDelete(req, resp);
    } else {
      String errMsg = new String("找不到" + method + "请求方式");
      resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg);
    }

  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    HttpServletRequest request;
    HttpServletResponse response;
    if (!(req instanceof HttpServletRequest && res instanceof HttpServletResponse)) {
      throw new ServletException("不是对应的HttpServletRequest和HttpServletResponse");
    }
    request = (HttpServletRequest) req;
    response = (HttpServletResponse) res;
    service(request, response);
  }

}
```

### 3.2请求体缓存

1. 我们将请求体加入到缓存中，可以解决部分无法重用的问题
2. 注意：ServletInputStream继承了InputStream，不论是如何读取请求体缓存，都会回归到无参的read方法

```java
public class ServletInputStream extends InputStream {

  private byte[] bytes;
  private int pos = 0;

  @Override
  public int read() throws IOException {
    if (pos > bytes.length - 1) {
      return -1;
    }
    return bytes[pos++];
  }

  // 从输入流的指定的偏移量开始将指定长度的字节读入到指定的数组中。
  // 如果该行所有请求的内容都已被读取，这个读取的过程将结束。如果是遇到了新的一行，新的一行的首个字符也将被读入到数组中。
  public int readLine(byte[] b, int off, int len) throws IOException {
    if (len <= 0) {
      return 0;
    }
    int count = 0, c;

    while ((c = read()) != -1) {
      b[off++] = (byte) c;
      count++;
      if (c == '\n' || count == len) {
        break;
      }
    }
    return count > 0 ? count : -1;
  }

  public void reset() {
    pos = 0;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

}
```

### 3.3Context

1. 里面存储了声明集合、配置集合、实例化集合

```java
//表示为一个web应用
public class Context {
  private String appName;
  private File classesDirectory;

  // 只声明已有的Servlet v为全限定名
  private Map<String, String> definitionMap = new HashMap<>();
  // ServletConfig
  private Map<String, ServletConfig> servletConfigMap = new HashMap<>();
  // Servlet映射名以及其Servlet对象
  private Map<String, Servlet> urlPatternMapping = new HashMap<>();

  public Context(String appName, File classesDirectory2) {
    this.appName = appName;
    this.classesDirectory = classesDirectory2;
  }

  public void addDefinitionMap(String urlPattern, String fullyQualifiedClassName) {
    if (!urlPattern.startsWith("/")) {
      urlPattern = "/" + urlPattern;
    }
    definitionMap.put(urlPattern, fullyQualifiedClassName);
  }

  public String getDefinitionMap(String urlPattern) {
    return definitionMap.get(urlPattern);
  }

  public void addServletConfig(String urlPattern, ServletConfig servletConfig) {
    if (!urlPattern.startsWith("/")) {
      urlPattern = "/" + urlPattern;
    }
    this.servletConfigMap.put(urlPattern, servletConfig);
  }

  public ServletConfig getServletConfig(String urlPattern) {
    return this.servletConfigMap.get(urlPattern);
  }

  public void addUrlPatternMapping(String urlPattern, Servlet servlet) {
    if (!urlPattern.startsWith("/")) {
      urlPattern = "/" + urlPattern;
    }
    urlPatternMapping.put(urlPattern, servlet);
  }

  public Servlet getUrlPatternMapping(String urlPattern) {
    return urlPatternMapping.get(urlPattern);
  }

  private Object loadClass(String clazz) throws Exception {
    // 自定义路径的类加载器
    WebappClassLoader webappClassLoader = new WebappClassLoader(new URL[] { classesDirectory.toURL() });
    Class<?> servletClass = webappClassLoader.loadClass(clazz);
    return servletClass.getConstructor().newInstance();
  }

  public Servlet addBean(String urlPattern, String clazz) throws Exception {
    Servlet servlet = (Servlet) loadClass(clazz);
    addUrlPatternMapping(urlPattern, servlet);
    ServletConfig servletConfig = getServletConfig(urlPattern);
    servlet.init(servletConfig);
    System.out.println(urlPattern + "第一次加载并调用了init方法");
    return servlet;
  }

}
```

### 3.4响应体暂存

1. 我们的响应内容不是在 `resp.getWriter().write("hello");`直接响应出去的，而是在各种操作都处理完后才拼接成响应协议发送出去的
2. 注意：在HttpServletResponse中我们new 了一个PrintWriter，将字节流转换成了字符流，因为中间多了一层BufferWriter，需要在响应前执行一次`writer.flush();`

```java
public class ServletOutputStream extends OutputStream {

  private byte[] bytes = new byte[1024];
  private int pos = 0;

  // 此处只需要重写write(int b)即可 其他write方法最终流向是该方法
  @Override
  public void write(int b) throws IOException {
    bytes[pos++] = (byte) b;
  }

  //当使用Printwriter时使用
  public void write(char[] str, int off, int len) throws IOException {
    for (char c : str) {
      write((byte)c);
    }
  }

  public byte[] getBytes() {
    return bytes;
  }

  public int getPos() {
    return pos;
  }

}
```

### 3.4Cookie

1. 不多说

```java
public class Cookie {

  private String name; 
  private String value;
  private int maxAge = -1;
  private String path;

  public Cookie(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getMaxAge() {
    return maxAge;
  }

  public void setMaxAge(int maxAge) {
    this.maxAge = maxAge;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  } 

}
```

### 3.6异常

```java
public class ServletException extends RuntimeException {
  public ServletException() {
  }
  public ServletException(String s) {
    super(s);
  }
}
```
