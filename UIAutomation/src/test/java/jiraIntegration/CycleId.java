package jiraIntegration;

import org.json.JSONArray;
import org.json.JSONObject;

import jiraIntegration.HttpHelper;
import jiraIntegration.HttpResult;
import jiraIntegration.JwtTokenGenerator;

public class CycleId {
	public String get(String projectId, String versionId) throws Exception {

		String jwtToken = new JwtTokenGenerator().generateTokenToGetCycleId(projectId, versionId);
		HttpHelper request = new HttpHelper();
		String uri = "cycles/search?versionId=" + versionId + "&projectId=" + projectId + "";
		HttpResult response = request.getZephyrId(versionId, projectId, jwtToken, uri);

		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			JSONArray jsonarr = new JSONArray(response.getResponse());
			for (int i = 0; i <= jsonarr.length() - 1; i++) {
				JSONObject jsonobj = jsonarr.getJSONObject(i);
				String id = jsonobj.getString("id");
				String name = jsonobj.getString("name");
				String cyclename = System.getenv("cyclename");
				if (name.compareTo(cyclename) == 0) {
					return id;
				}
			}
		}
		return null;
	}
}
