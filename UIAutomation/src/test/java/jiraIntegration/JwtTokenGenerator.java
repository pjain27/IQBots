package jiraIntegration;

import java.net.URI;
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

public class JwtTokenGenerator {
	public String generateTokenToGetCycleId(String Projectid, String Versionid) throws Exception {
		long start = System.currentTimeMillis();
		String zephyrBaseUrl = System.getenv("zephyrBaseUrl");
		String accessKey = System.getenv("accessKey");
		String secretKey = System.getenv("secretKey");
		String userName = System.getenv("userName");
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName)
				.build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycles/search?versionId=" + Versionid
				+ "&projectId=" + Projectid + "";
		URI uri = new URI(createCycleUri);
		int expirationInSec = 136000;
		String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("generate tocken for cycle id duration -milisec:" + total);
		return jwt;
	}

	public String generateTokenToGetIssueId(String Projectid, String Versionid, String cycleId) throws Exception {
		long start = System.currentTimeMillis();
		String zephyrBaseUrl = System.getenv("zephyrBaseUrl");
		String accessKey = System.getenv("accessKey");
		String secretKey = System.getenv("secretKey");
		String userName = System.getenv("userName");
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName)
				.build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/search/cycle/" + cycleId
				+ "?projectId=" + Projectid + "&versionId=" + Versionid + "";
		URI uri = new URI(createCycleUri);
		int expirationInSec = 136000;
		String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("generate tocken for issueid duration -milisec:" + total);
		return jwt;
	}

	public String generateTokenToGetExecutionId(String Projectid, String Versionid, String cycleId) throws Exception {
		long start = System.currentTimeMillis();
		try {
			String zephyrBaseUrl = System.getenv("zephyrBaseUrl");
			String accessKey = System.getenv("accessKey");
			String secretKey = System.getenv("secretKey");
			String userName = System.getenv("userName");
			ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName)
					.build();
			JwtGenerator jwtGenerator = client.getJwtGenerator();
			String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/search/cycle/" + cycleId
					+ "?versionId=" + Versionid + "&projectId=" + Projectid + "";
			URI uri = new URI(createCycleUri);

			int expirationInSec = 13600000;
			String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
			long end = System.currentTimeMillis();
			long total = (long) ((end - start));
			System.out.println("generate tocken for exe id duration -milisec:" + total);
			return jwt;
		} catch (Exception e) {

			System.out.println(e);
		}
		return "";
	}

	public String generateTokenToUpdateExecutionStatus(long issueId, String executionId, String Projectid)
			throws Exception {
		long start = System.currentTimeMillis();
		String zephyrBaseUrl = System.getenv("zephyrBaseUrl");
		String accessKey = System.getenv("accessKey");
		String secretKey = System.getenv("secretKey");
		String userName = System.getenv("userName");
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName)
				.build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/execution/" + executionId + "?projectId="
				+ Projectid + "&issueId=" + issueId + "";

		URI uri = new URI(createCycleUri);
		int expirationInSec = 360;
		String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("generate tocken for exe status duration -milisec:" + total);
		return jwt;
	}
}
