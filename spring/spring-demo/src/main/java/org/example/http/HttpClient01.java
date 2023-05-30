package org.example.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.http.client.MultipartBodyBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClient01 {
  public static void main(String[] args) throws Exception {
    // String url = "https://www.xurou.com/notices/content/baoliao/229";
    String url = "https://www.baidu.com/";
    URL url2 = new URL(url);
    URLConnection openConnection = url2.openConnection();
    HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;

    // 设置请求类型
    httpURLConnection.setRequestMethod("GET");
    // 设置请求头
    httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");

    // 获取httpURLConnection的输入流

    try (InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(inputStreamReader);) {
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    }

  }
}

class h01 {
  public static void main(String[] args) throws IOException {
    // 可关闭的HttpClient客户端，相当于一个打开的浏览器
    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    // 模拟get的表单提交
    // 浏览器会自动进行urLdecode编码
    // 但如果自己提交会出现特殊字符变成空格的现象
    String pwd1 = "abc+123";// abc 123
    pwd1 = URLEncoder.encode(pwd1, StandardCharsets.UTF_8);
    String url = "https://www.baidu.com/?pwd=" + pwd1;

    // 构造httpGet请求对象
    HttpGet httpGet = new HttpGet(url);
    // 真人认证
    // httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)
    // AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36
    // Edg/113.0.1774.42");
    // 访问时来源网站
    // httpGet.addHeader("Referer", "https://www.baidu.com/");

    // 响应
    CloseableHttpResponse response = null;

    try {
      response = closeableHttpClient.execute(httpGet);
      // 代表本次请求的成功、失败的状态
      StatusLine statusLine = response.getStatusLine();
      if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
        System.out.println("成功");
        // 获取全部响应头
        Header[] allHeaders = response.getAllHeaders();
        for (Header header : allHeaders) {
          System.out.println(header + "------");
        }
        // 获取响应结果
        // DecompressingEntity 解压缩
        // HttpEntity 不仅可以作为结果，也可以作为请求的参数实体，有很多实现
        HttpEntity entity = response.getEntity();
        // 获取content-type
        System.out.println(entity.getContentType().getValue());

        // 工具类 对HttpEntity操作
        String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // 打印结果
        System.out.println(toStringResult);
        // 确保流关闭
        EntityUtils.consume(entity);
      } else {
        System.out.println("失败");
      }

    } catch (Exception e) {
      // TODO: handle exception
    } finally {
      if (closeableHttpClient != null) {
        closeableHttpClient.close();
      }
      if (response != null) {
        response.close();
      }
    }

  }
}

class ph01 {
  public static void main(String[] args) throws IOException {
    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    String url = "https://img-operation.csdnimg.cn/csdn/silkroad/img/1683703834483.png";
    HttpGet httpGet = new HttpGet(url);
    CloseableHttpResponse response = null;

    try {
      response = closeableHttpClient.execute(httpGet);
      StatusLine statusLine = response.getStatusLine();
      if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
        System.out.println("成功");
        HttpEntity entity = response.getEntity();
        String contentType = entity.getContentType().getValue();
        String suffix = ".jpg";
        // 图片类型
        if (contentType.contains("jpg") || contentType.contains("jpeg")) {
          suffix = ".jpg";
        } else if (contentType.contains("bmp") || contentType.contains("bitmap")) {
          suffix = ".bmp";
        } else if (contentType.contains("png")) {
          suffix = ".png";
        } else if (contentType.contains("gif")) {
          suffix = ".gif";
        }
        // 获取文件的字节流
        byte[] bytes = EntityUtils.toByteArray(entity);
        String path = "E:/新建文件夹 (2)/aaa" + suffix;
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(bytes);
        fos.close();
        EntityUtils.consume(entity);
      } else {
        System.out.println("失败");
      }

    } catch (Exception e) {
    } finally {
      if (closeableHttpClient != null) {
        closeableHttpClient.close();
      }
      if (response != null) {
        response.close();
      }
    }
  }

}

class daili01 {
  public static void main(String[] args) throws IOException {
    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    String url = "https://www.baidu.com/";
    HttpGet httpGet = new HttpGet(url);

    String ip = "203.110.176.69";
    int port = 8111;
    // 创建代理
    HttpHost proxy = new HttpHost(ip, port);
    // 对每一个请求进行配置，会覆盖全局的默认请求配置
    RequestConfig config = RequestConfig.custom()
        .setProxy(proxy)
        // 连接超时，tcp三次握手的时间上限
        .setConnectTimeout(5000)
        // 读取超时 从请求网址处获得响应数据的时间间隔
        .setSocketTimeout(5000)
        .build();
    httpGet.setConfig(config);
    CloseableHttpResponse response = null;

    try {
      response = closeableHttpClient.execute(httpGet);
      StatusLine statusLine = response.getStatusLine();
      if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
        System.out.println("成功");
        HttpEntity entity = response.getEntity();
        // 工具类 对HttpEntity操作
        String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // 打印结果
        System.out.println(toStringResult);
        EntityUtils.consume(entity);
      } else {
        System.out.println("失败");
      }

    } catch (Exception e) {
    } finally {
      if (closeableHttpClient != null) {
        closeableHttpClient.close();
      }
      if (response != null) {
        response.close();
      }
    }
  }

}

class post01from {
  public static void main(String[] args) throws IOException {
    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    String url = "https://www.baidu.com/";
    // 创建httpPost
    HttpPost httpPost = new HttpPost(url);
    httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    // 设置参数
    // NameValuePair input标签的name和value的构成
    List<NameValuePair> list = new ArrayList<>();
    list.add(new BasicNameValuePair("name", "admin"));
    list.add(new BasicNameValuePair("pwd", "123456"));
    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
    httpPost.setEntity(formEntity);

    CloseableHttpResponse response = null;

    try {
      response = closeableHttpClient.execute(httpPost);
      StatusLine statusLine = response.getStatusLine();
      if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
        System.out.println("成功");
        HttpEntity entity = response.getEntity();
        // 工具类 对HttpEntity操作
        String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // 打印结果
        System.out.println(toStringResult);
        EntityUtils.consume(entity);
      } else {
        System.out.println("失败");
      }

    } catch (Exception e) {
    } finally {
      if (closeableHttpClient != null) {
        closeableHttpClient.close();
      }
      if (response != null) {
        response.close();
      }
    }
  }

}

class post02json {
  public static void main(String[] args) throws IOException {
    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    String url = "https://www.baidu.com/";
    // 创建httpPost
    HttpPost httpPost = new HttpPost(url);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("pwd", "123456");
    // 设置参数
    StringEntity jsonEntity = new StringEntity(jsonObject.toJSONString(), Consts.UTF_8);
    // 也需要给Entity设置内容类型
    jsonEntity.setContentEncoding(new BasicHeader("Content-Type", "application/json;charset=utf-8"));
    // 设置Entity编码
    jsonEntity.setContentEncoding(Consts.UTF_8.name());
    httpPost.setEntity(jsonEntity);

    CloseableHttpResponse response = null;

    try {
      response = closeableHttpClient.execute(httpPost);
      StatusLine statusLine = response.getStatusLine();
      if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
        System.out.println("成功");
        HttpEntity entity = response.getEntity();
        // 工具类 对HttpEntity操作
        String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // 打印结果
        System.out.println(toStringResult);
        EntityUtils.consume(entity);
      } else {
        System.out.println("失败");
      }

    } catch (Exception e) {
    } finally {
      if (closeableHttpClient != null) {
        closeableHttpClient.close();
      }
      if (response != null) {
        response.close();
      }
    }
  }

}

class post03fromfile {
  public static void main(String[] args) throws IOException {
    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    String url = "https://www.baidu.com/";
    // 创建httpPost
    HttpPost httpPost = new HttpPost(url);

    // 构建 ContentBody 的实现类对象
    FileBody fileBody = new FileBody(new File("E:/book.xml"));

    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    // 编码
    builder.setCharset(Consts.UTF_8);
    // 文件上传格式
    builder.setContentType(ContentType.create("multipart/form-data", Consts.UTF_8));
    // 设置浏览器模式
    builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

    StringBody stringBody = new StringBody("张三", ContentType.create("text/plain", Consts.UTF_8));

    HttpEntity httpEntity = builder
        // 两种上传方式
        .addPart("fileName", fileBody)
        // 通过file,byte[],inputstream来上传文件
        .addBinaryBody("fileName", new File("e:/诚信声明.png"))
        // 对于表单数据出现中文不能通过addTextBody ,否则乱码
        // 中文乱码方案一
        .addPart("username", stringBody)
        // 不能出现中文
        .addTextBody("pwd", "123456")
        // 中文乱码方案二
        .addTextBody("name", "例四", ContentType.APPLICATION_JSON)
        .build();
    // 设置Entity编码
    httpPost.setEntity(httpEntity);
    CloseableHttpResponse response = null;

    try {
      response = closeableHttpClient.execute(httpPost);
      StatusLine statusLine = response.getStatusLine();
      if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
        System.out.println("成功");
        HttpEntity entity = response.getEntity();
        // 工具类 对HttpEntity操作
        String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // 打印结果
        System.out.println(toStringResult);
        EntityUtils.consume(entity);
      } else {
        System.out.println("失败");
      }

    } catch (Exception e) {
    } finally {
      if (closeableHttpClient != null) {
        closeableHttpClient.close();
      }
      if (response != null) {
        response.close();
      }
    }
  }

}

class post01https {

  //另一种方式
  public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
    SSLContext sc = SSLContext.getInstance("SSLv3");

    // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
    X509TrustManager trustManager = new X509TrustManager() {
      @Override
      public void checkClientTrusted(
          java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
          String paramString) throws CertificateException {
      }

      @Override
      public void checkServerTrusted(
          java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
          String paramString) throws CertificateException {
      }

      @Override
      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
      }
    };

    sc.init(null, new TrustManager[] { trustManager }, null);
    return sc;
  }

  private static ConnectionSocketFactory trustHttpsCertificates()
      throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
    sslContextBuilder.loadTrustMaterial(null, new TrustStrategy() {
      //判断是否信任
      @Override
      public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        return true;
      }
    });
    SSLContext sslContext = createIgnoreVerifySSL();
    // SSLContext sslContext = sslContextBuilder.build();
    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
        //支持的协议
        new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" },
        null, NoopHostnameVerifier.INSTANCE);

    return sslConnectionSocketFactory;
  }

  public static void main(String[] args) throws Exception {
    // 绕过https的安全认证
    // 连接池所需的参数
    Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
        // http 使用默认的ConnectionSocketFactory
        .register("http", PlainConnectionSocketFactory.INSTANCE)
        // https 使用自己配置的
        .register("https", trustHttpsCertificates())
        .build();

    // 创建连接池
    PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(registry);
    // 定制CloseableHttpClient 此处setConnectionManager为连接池
    HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(pool);
    // 配置好HttpClientBuilder 通过build方法获取
    CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
    String url = "https://www.xurou.com/notices/content/baoliao/229";
    // 创建httpPost
    HttpPost httpPost = new HttpPost(url);
    httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    // 设置参数
    // NameValuePair input标签的name和value的构成
    List<NameValuePair> list = new ArrayList<>();
    list.add(new BasicNameValuePair("name", "admin"));
    list.add(new BasicNameValuePair("pwd", "123456"));
    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
    httpPost.setEntity(formEntity);

    CloseableHttpResponse response = null;

    try {
      response = closeableHttpClient.execute(httpPost);
      StatusLine statusLine = response.getStatusLine();
      if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
        System.out.println("成功");
        HttpEntity entity = response.getEntity();
        // 工具类 对HttpEntity操作
        String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // 打印结果
        System.out.println(toStringResult);
        EntityUtils.consume(entity);
      } else {
        System.out.println("失败");
      }

    } catch (Exception e) {
    } finally {
      if (closeableHttpClient != null) {
        closeableHttpClient.close();
      }
      if (response != null) {
        response.close();
      }
    }
  }

}