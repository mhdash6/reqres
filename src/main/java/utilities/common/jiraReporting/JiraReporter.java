package utilities.common.jiraReporting;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

public class JiraReporter implements AutoCloseable {

    private static final long ISSUE_TYPE_ID_BUG = 10003L;
    private final JiraRestClient client;
    private final IssueRestClient issueClient;

    public JiraReporter(String jiraUrl, String email, String apiToken) {
        try {
            client = new AsynchronousJiraRestClientFactory()
                    .createWithBasicHttpAuthentication(new URI(jiraUrl), email, apiToken);
            issueClient = client.getIssueClient();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid JIRA URL: " + jiraUrl, e);
        }
    }

    private BasicIssue createIssue(String projectKey, String summary, String description) {
        BasicIssue issue = issueClient.createIssue(
                new IssueInputBuilder(projectKey, ISSUE_TYPE_ID_BUG, summary)
                        .setDescription(description)
                        .build()
        ).claim();
        System.out.println("Created JIRA issue: " + issue.getKey());
        return issue;
    }

    public void reportBug(String projectKey, String summary, String description,String reporter) {
        createIssue(projectKey, summary, description);
    }

    public void reportBug(String projectKey,
                          String summary,
                          String description,
                          String reporter,
                          String pngFilePath) {
        BasicIssue issue = createIssue(projectKey, summary, description);

        Issue full = issueClient.getIssue(issue.getKey()).claim();
        try (InputStream in = new FileInputStream(pngFilePath)) {
            String fileName = Paths.get(pngFilePath).getFileName().toString();
            issueClient.addAttachment(full.getAttachmentsUri(), in, fileName).claim();
            System.out.println("Attached file: " + fileName);
        } catch (Exception e) {
            System.err.println("Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            client.close();
        } catch (Exception e) {
            System.err.println("Error closing JIRA client: " + e.getMessage());
        }
    }
}



