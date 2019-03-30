package net.ukr.dandy1988.first_app;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new TasksAdapter(generatedFakeData()));
    }

    public List<Task> generatedFakeData(){
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tasks.add( new Task("Task"+i,3));

        }
        return  tasks;
    }

    public static class TasksAdapter extends RecyclerView.Adapter<TaskViewHolder>{

        private final List<Task> data;

        public TasksAdapter(List<Task> data){
            this.data = data;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.item_task, viewGroup, false);
            TaskViewHolder viewHolder = new TaskViewHolder(view);
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
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTaskName;

        public TaskViewHolder(@NonNull View itemView){
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
            tvTaskName.setText("ProgKievUA");
        }

        public void setData(Task task) {
            tvTaskName.setText(task.getName());
        }
    }
}
