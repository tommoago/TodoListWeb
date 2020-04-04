package com.sebastianopighi.todolistweb.data.models;

import com.google.gson.annotations.SerializedName;

public class Todo {

    private long userId;
    private long id;
    @SerializedName("title")
    private String todoTitle;
    private boolean completed;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
