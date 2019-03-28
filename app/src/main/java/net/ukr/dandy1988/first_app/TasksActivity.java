package net.ukr.dandy1988.first_app;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TasksActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public static class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>{

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public TaskViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
