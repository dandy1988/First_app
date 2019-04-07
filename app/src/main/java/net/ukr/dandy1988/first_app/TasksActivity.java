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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);


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
