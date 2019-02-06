package ru.stqa.pft.rest;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(525);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New Test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues.size(), oldIssues.size());
  }


  private Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json?limit=200")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }


  private int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()), new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}


