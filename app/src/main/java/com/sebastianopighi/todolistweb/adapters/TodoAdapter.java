package com.sebastianopighi.todolistweb.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sebastianopighi.todolistweb.R;
import com.sebastianopighi.todolistweb.data.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private Context context;
    private List<Todo> todos = new ArrayList<>();

    public TodoAdapter(Context context) {
        this.context = context;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= todos.size())
            return null;
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (position >= todos.size())
            return 0;
        return todos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.todo_cell, parent, false);
        }

        Todo currentTodo = todos.get(position);

        TextView todoName = convertView.findViewById(R.id.todo_name_label);
        ImageView todoDone = convertView.findViewById(R.id.todo_done_image);

        todoName.setText(currentTodo.getTodoTitle());
        todoDone.setImageTintList(ColorStateList.valueOf(currentTodo.isCompleted() ? Color.GREEN : Color.GRAY));

        return convertView;
    }
}
