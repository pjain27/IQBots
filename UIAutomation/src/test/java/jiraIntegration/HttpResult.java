package jiraIntegration;

public class HttpResult {
	int statusCode;
	String response;

	public HttpResult(int statusCode, String response) {
		this.statusCode = statusCode;
		this.response = response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getResponse() {
		return response;
	}
}
