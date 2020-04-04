package com.sebastianopighi.todolistweb.data.services;

import com.sebastianopighi.todolistweb.data.models.Todo;

import java.util.List;

public interface IWebServer {
    void onTodosFetched(boolean success, List<Todo> todos, int errorCode, String errorMessage);
}
