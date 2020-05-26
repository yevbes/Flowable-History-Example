package com.acme.flowablehistory.models;


/**
 * @author Ievgenii Bespal
 */
public class Task {

    private String id;

    public Task(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
