package net.ukr.dandy1988.first_app;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

//тут ViewHolder
public class TaskViewHolder extends RecyclerView.ViewHolder{
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
