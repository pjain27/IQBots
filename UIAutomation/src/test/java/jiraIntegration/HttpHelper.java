package jiraIntegration;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import com.sun.jersey.core.util.Base64;

@SuppressWarnings("deprecation")
public class HttpHelper {

	String jiraurl = null;

	public HttpResult getJiraId(String uri) throws Exception {
		long start = System.currentTimeMillis();
		long start1 = System.currentTimeMillis();
		String token = new String(Base64.encode(System.getenv("passWord")));
		jiraurl = System.getenv("Jiraloginurl");
		long end1 = System.currentTimeMillis();
		long total1 = (long) ((end1 - start1));
		System.out.println("token generation duration -milisec:" + total1);
		System.out.println("getJiraid Token" + token);
		String baseuri = jiraurl + "/rest/api/2/project/" + System.getenv("ProjectName");
		if (uri != null) {
			baseuri += "/" + uri + "";
		}

		@SuppressWarnings({ "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(baseuri);
		request.addHeader("Authorization", "Basic " + token);
		HttpResponse response = httpClient.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		String statusResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("Get Jira id duration -milisec:" + total);
		return new HttpResult(statusCode, statusResponse);
	}

	public HttpResult getZephyrId(String versionId, String projectId, String jwtToken, String uri) throws IOException {
		String baseuri = System.getenv("JIRA_URL") + "/rest/api/1.0/" + uri;
		long start = System.currentTimeMillis();
		@SuppressWarnings({ "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(baseuri);
		request.addHeader("content-type", "text/plain");
		request.addHeader("Authorization", jwtToken);
		request.addHeader("zapiAccessKey", System.getenv("accessKey"));
		HttpResponse response = httpClient.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		String statusResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("Zephyr Id" + "Get zephyr id duration -milisec:" + total);
		return new HttpResult(statusCode, statusResponse);
	}

	public HttpResult updateZephyrStatus(String uri, String payload, String jwtToken) throws Exception {
		String baseuri = System.getenv("JIRA_URL") + "/rest/api/1.0/" + uri;
		long start = System.currentTimeMillis();
		@SuppressWarnings("resource")
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut request = new HttpPut(baseuri);
		StringEntity params = new StringEntity(payload, "UTF-8");
		params.setContentType("application/json");
		request.addHeader("content-type", "application/json");
		request.addHeader("Authorization", jwtToken);
		request.addHeader("zapiAccessKey", System.getenv("accessKey"));
		request.setEntity(params);
		HttpResponse response = httpClient.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("Update zephyr status duration -milisec:" + total);
		return new HttpResult(statusCode, null);
	}
}
