package jiraIntegration;

import jiraIntegration.CycleId;
import jiraIntegration.ExecutionId;
import jiraIntegration.ExecutionStatus;
import jiraIntegration.ProjectId;
import jiraIntegration.TestCaseId;
import jiraIntegration.VersionId;

public class UpdateExecutionStatusToJira {
	public void update(String scenarioName, int methodExecutionStatus) throws Exception {
		long start = System.currentTimeMillis();
		String projectId = new ProjectId().get();
		System.out.println("Project Id:" + projectId);
		String versionId = new VersionId().get();
		String cycleId = new CycleId().get(projectId, versionId);
		long issueId = new TestCaseId().get(cycleId, projectId, versionId, scenarioName.toString());
		String executionId = new ExecutionId().get(issueId, cycleId, projectId, versionId);
		System.out.println(executionId);
		new ExecutionStatus().update(issueId, executionId, methodExecutionStatus, projectId);
		long end = System.currentTimeMillis();
		long total = (long) ((end - start));
		System.out.println("Get all[issue id,cycle id,project id,version id,methodname and update duration -milisec:" + total);

	}
}
