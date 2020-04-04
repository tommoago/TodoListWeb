package com.sebastianopighi.todolistweb.data.services;

import android.util.Log;

import com.sebastianopighi.todolistweb.data.models.Todo;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private String TODO_BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static WebService instance;
    private TodoService todoService;

    private WebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TODO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        todoService = retrofit.create(TodoService.class);
    }

    public static WebService getInstance() {
        if (instance == null)
            instance = new WebService();
        return instance;
    }

    public void getTodos(final IWebServer callback) {
        Call<List<Todo>> todosRequest = todoService.getTodos();
        todosRequest.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.code() == 200) {
                    callback.onTodosFetched(true, response.body(), -1, null);
                } else {
                    try {
                        callback.onTodosFetched(true, null, response.code(), response.errorBody().string());
                    } catch (IOException ex) {
                        Log.e("WebService", ex.toString());
                        callback.onTodosFetched(true, null, response.code(), "Generic error message");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                callback.onTodosFetched(false, null, -1, t.getLocalizedMessage());
            }
        });
    }

}
