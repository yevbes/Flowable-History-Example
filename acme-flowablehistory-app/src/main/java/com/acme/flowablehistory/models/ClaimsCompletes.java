package com.acme.flowablehistory.models;


import java.sql.Timestamp;

/**
 * @author Ievgenii Bespal
 */

public class ClaimsCompletes {
    private String taskId;
    private Timestamp timestamp;
    private String action;
    private String values;
    private String user;

    public ClaimsCompletes(String taskId, Timestamp timestamp, String action, String values, String user) {
        this.taskId = taskId;
        this.timestamp = timestamp;
        this.action = action;
        this.values = values;
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
