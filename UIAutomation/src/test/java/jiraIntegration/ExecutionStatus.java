package jiraIntegration;

import jiraIntegration.HttpHelper;
import jiraIntegration.JwtTokenGenerator;

public class ExecutionStatus {
	public void update(long issueId, String executionId, int result, String projectId) throws Exception {
		long start = System.currentTimeMillis();
		String jwtToken = new JwtTokenGenerator().generateTokenToUpdateExecutionStatus(issueId, executionId, projectId);
		HttpHelper request = new HttpHelper();
		String uri = "execution/" + executionId + "?projectId=" + projectId + "&issueId=" + issueId + "";
		String payload = "{\"status\":{\"id\":" + result + "},\"id\":\"" + executionId + "\",\"projectId\":" + projectId
				+ ",\"issueId\":" + issueId + ",\"cycleId\":-1,\"versionId\":-1}";
		request.updateZephyrStatus(uri, payload, jwtToken);
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("update issueid api with payload duration -milisec:" + total);
	}
}
