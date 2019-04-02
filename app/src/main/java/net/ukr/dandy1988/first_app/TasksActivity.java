package net.ukr.dandy1988.first_app;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private RecyclerView rv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rv.setAdapter(new TasksAdapter(generatedFakeData()));
        TasksAdapter adapter = new TasksAdapter(new TaskClickListener() {
            @Override public void onClick(Task task) {
                String taskText = task.getName() + " in progress..";
                Toast.makeText(TasksActivity.this, taskText, Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(adapter);

        adapter.seData(generatedFakeData());
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

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTaskName;

        public TaskViewHolder(@NonNull View itemView){
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
//            tvTaskName.setText("ProgKievUA");
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

    public interface TaskClickListener{
        public void onClick(Task task);
    }
}
