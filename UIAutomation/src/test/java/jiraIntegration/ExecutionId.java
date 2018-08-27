package jiraIntegration;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import jiraIntegration.HttpHelper;
import jiraIntegration.HttpResult;
import jiraIntegration.JwtTokenGenerator;

public class ExecutionId {
	public String get(long issueId, String cycleId, String projectId, String versionId) throws Exception {
		String jwtToken = new JwtTokenGenerator().generateTokenToGetExecutionId(projectId, versionId, cycleId);
		HttpHelper request = new HttpHelper();
		String uri = "executions/search/cycle/" + cycleId + "?versionId=" + versionId + "&projectId=" + projectId + "";
		HttpResult response = request.getZephyrId(versionId, projectId, jwtToken, uri);

		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			try {
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JSONObject allIssues = new JSONObject(response.getResponse());
			JSONArray IssuesArray = allIssues.getJSONArray("searchObjectList");
			if (IssuesArray.length() == 0) {
				return "";
			}
			for (int j = 0; j <= IssuesArray.length() - 1; j++) {
				JSONObject jobj = IssuesArray.getJSONObject(j);
				JSONObject jobj2 = jobj.getJSONObject("execution");
				String executionId = jobj2.getString("id");
				long IssueId1 = jobj2.getLong("issueId");

				if (IssueId1 == issueId) {
					System.out.println("executionID =" + executionId);
					return executionId;
				}
			}
		}
		return "";
	}
}
