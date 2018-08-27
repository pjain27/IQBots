package jiraIntegration;

import org.json.JSONObject;

import jiraIntegration.HttpHelper;
import jiraIntegration.HttpResult;

public class ProjectId {
	public String get() throws Exception {
		HttpHelper request = new HttpHelper();
		HttpResult response = request.getJiraId(null);
		long start = System.currentTimeMillis();
		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			JSONObject projectinfo = new JSONObject(response.getResponse());
			String projectid = (String) projectinfo.get("id");
			long end = System.currentTimeMillis();
			long total = (long) ((end - start));
			System.out.println("Get Project id duration -milisec:" + total);
			return projectid;
		}
		
		return null;
	}
}
