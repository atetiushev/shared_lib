package com.example.jira

class JiraPayload {
    String projectKey
    String summary
    String description
    String issueType
    String assignee
    String priority
    String labels
    Map<String, String> customFields = [:]
}
