package com.sebastianopighi.todolistweb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sebastianopighi.todolistweb.R;
import com.sebastianopighi.todolistweb.adapters.TodoAdapter;
import com.sebastianopighi.todolistweb.data.models.Todo;
import com.sebastianopighi.todolistweb.data.services.IWebServer;
import com.sebastianopighi.todolistweb.data.services.WebService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loadingBar;
    private ListView todoList;
    private Button reloadButton;

    private TodoAdapter adapter;

    private WebService webService;
    private IWebServer webServerListener = new IWebServer() {
        @Override
        public void onTodosFetched(boolean success, List<Todo> todos, int errorCode, String errorMessage) {
            if (success) {
                adapter.setTodos(todos);
                adapter.notifyDataSetChanged();
                loadingBar.setVisibility(View.GONE);
                todoList.setVisibility(View.VISIBLE);
            } else {
                loadingBar.setVisibility(View.GONE);
                todoList.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webService = WebService.getInstance();

        todoList = findViewById(R.id.todo_list);
        loadingBar = findViewById(R.id.loading_bar);
        reloadButton = findViewById(R.id.reload_button);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTodos();
            }
        });

        adapter = new TodoAdapter(this);
        todoList.setAdapter(adapter);

        loadTodos();

    }

    private void loadTodos() {
        loadingBar.setVisibility(View.VISIBLE);
        todoList.setVisibility(View.GONE);
        webService.getTodos(webServerListener);
    }
}
