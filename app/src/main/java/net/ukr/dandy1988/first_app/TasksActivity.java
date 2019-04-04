package net.ukr.dandy1988.first_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {
    public static final int ADD_TASK_REQUEST_CODE = 101;
    private RecyclerView rv ;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivityForResult(new Intent(TasksActivity.this, AddTaskActivity.class), ADD_TASK_REQUEST_CODE);
            }
        });

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        TasksAdapter adapter = new TasksAdapter(new TaskClickListener() {
            @Override public void onClick(Task task) {
                String taskText = task.getName() + " in progress...";
                Toast.makeText(TasksActivity.this, taskText, Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(adapter);
        adapter.seData(generatedFakeData());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                Task task = ((Task) data.getSerializableExtra(Task.class.getName()));
                Toast.makeText(this, task.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public List<Task> generatedFakeData(){
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int colorOfTaskPiority = (int) (10*Math.random());
            tasks.add( new Task("Task #"+i+"  ",3,colorOfTaskPiority));
        }
        return  tasks;
    }

    public static class TasksAdapter extends RecyclerView.Adapter<TaskViewHolder>{
        private final List<Task> data = new ArrayList<>();
        private final TaskClickListener taskClickListener;

        public TasksAdapter(TaskClickListener taskClickListener) {
            this.taskClickListener = taskClickListener;
        }


        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.item_task, viewGroup, false);
            final TaskViewHolder viewHolder = new TaskViewHolder(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (RecyclerView.NO_POSITION != viewHolder.getAdapterPosition()) {
                        taskClickListener.onClick(data.get(viewHolder.getAdapterPosition()));
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {
            Task task = data.get(position);
            taskViewHolder.setData(task);
        }
//
        @Override
        public int getItemCount() {
            return data.size();
        }

        public void seData(List<Task> data) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    //тут ViewHolder
    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTaskName;

        public TaskViewHolder(@NonNull View itemView){
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
        }

        public void setData(Task task) {
            Spannable text = new SpannableString( "•   "+task.getName());
            if (task.getColor()>4){
                text.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            }else{
                text.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tvTaskName.setText(text);
        }
    }

    //клик на задачу
    public interface TaskClickListener{
        public void onClick(Task task);
    }
}
