package jiraIntegration;

import org.json.JSONArray;
import org.json.JSONObject;

import jiraIntegration.HttpHelper;
import jiraIntegration.HttpResult;

public class VersionId {
	public String get() throws Exception {
		HttpHelper request = new HttpHelper();
		HttpResult response = request.getJiraId("versions");

		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			String name = null;
			JSONArray jsonarr = new JSONArray(response.getResponse());
			for (int i = 0; i <= jsonarr.length() - 1; i++) {
				JSONObject jsonobj = jsonarr.getJSONObject(i);
				name = jsonobj.getString("name");
				String Version = System.getenv("Projectversion");
				if (name.contains(Version)) {
					return jsonobj.getString("id");
				}
			}
		}
		return null;
	}
}
