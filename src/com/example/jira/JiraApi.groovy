package com.example.jira

import com.cloudbees.groovy.cps.NonCPS
import com.example.http.HttpResponse
import com.example.http.JsonHttpClient

class JiraApi implements Serializable {

    private static final String JIRA_URL = "smth here"
    private static final String JIRA_API_CRED_ID_ENV_VARIABLE_NAME = "JIRA_IPA_CREDENTIALS_ID"
    private static final String DEFAULT_JIRA_API_CREDENTIALS_ID = "JiraToken"

    def script

    JsonHttpClient httpClient;

    JiraApi(script) {
        if (script == null) {
            throw new IllegalArgumentException("script must be not null")
        }
        this.script = script
        httpClient = new JsonHttpClient(this.script)
        def jiraApiCredentials = getCredentials(script)
        def userName = jiraApiCredentials['username']
        def password = jiraApiCredentials['password']
        httpClient.withUserName(userName).withPassword(password)
    }

    getCredentials(script) {
        def credId = script.env.JIRA_API_CRED_ID_ENV_VARIABLE_NAME
        if (!credId) {
            credId = DEFAULT_JIRA_API_CREDENTIALS_ID
        }
        def credentials = [:]
        script.withCredentials([script.usernamePassword(
                credentialsId: "${credId}",
                usernameVariable: 'USERNAME',
                passwordVariable: 'PASSWORD')]) {
            credentials['username'] = script.env.USERNAME
            credentials['password'] = script.env.PASSWORD
        }
        return credentials
    }

    @NonCPS
    def executeQuery(String queryString) {
        HttpResponse response = httpClient.withUrl(JIRA_URL + "api..." /).execute()
        if (response.failure) {
            throw new RuntimeException("Can't execute lira API search" + response.body)
        }
        return response.body
    }
}
