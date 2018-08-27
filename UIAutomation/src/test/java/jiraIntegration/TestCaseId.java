package jiraIntegration;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import jiraIntegration.HttpHelper;
import jiraIntegration.HttpResult;
import jiraIntegration.JwtTokenGenerator;

public class TestCaseId {
	public long get(String cycleId, String projectId, String versionId, String scenarioName) throws Exception {
		//System.out.println("Inside TestCaseID - Scenario: " + scenarioName);
		String jwtToken = new JwtTokenGenerator().generateTokenToGetIssueId(projectId, versionId, cycleId);
		HttpHelper request = new HttpHelper();
		String uri = "executions/search/cycle/" + cycleId + "?projectId=" + projectId + "&versionId=" + versionId + "";
		HttpResult response = request.getZephyrId(versionId, projectId, jwtToken, uri);
		//System.out.println("uri-" + uri);
		//System.out.println("response" + response);

		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			try {
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JSONObject allIssues = new JSONObject(response.getResponse());
			JSONArray IssuesArray = allIssues.getJSONArray("searchObjectList");

			if (IssuesArray.length() == 0) {
				return 0;
			}
			for (int j = 0; j <= IssuesArray.length() - 1; j++) {
				JSONObject jobj = IssuesArray.getJSONObject(j);
				//String IssueSummary = (String) jobj.get("issueLabel");
				String IssueSummary = (String) jobj.get("issueSummary");
				JSONObject executionId = jobj.getJSONObject("execution");
				long IssueId = executionId.getLong("issueId");

				if (IssueSummary.compareTo(scenarioName.toString()) == 0) {
					//System.out.println("IssueId: " + IssueId);
					return IssueId;
				}
			}
		}
		return 0;
	}

}
