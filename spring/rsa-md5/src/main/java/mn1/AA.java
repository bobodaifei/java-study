package mn1;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AA {

	public static void main(String[] args) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/w1/getStu?id=53");

		CloseableHttpResponse response = httpclient.execute(httpGet);

		HttpEntity entity = response.getEntity();
		
		String resp = EntityUtils.toString(entity);
		System.out.println(resp);

	}
}
