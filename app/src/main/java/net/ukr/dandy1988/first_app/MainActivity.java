package net.ukr.dandy1988.first_app;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etName = findViewById(R.id.etName);

     //   Spannable text = new SpannableString(".");
     //   text.setSpan(new UnderlineSpan(), 8, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
     //   text.setSpan(new StyleSpan(Typeface.BOLD), 22, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView etPriority = findViewById(R.id.etPriority);
        etPriority.setText("Hello");

        final Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setEnabled(false);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                btnAdd.setEnabled(!text.isEmpty());
            }
        });


    }
}
