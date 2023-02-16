package com.tuananh.aptrotrain;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DetailActivity extends Activity {
    private TextView mContent, mImportant;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mContent = findViewById(R.id.detail_content);
        mImportant = findViewById(R.id.detail_important);

        Reminder reminder = (Reminder) getIntent().getSerializableExtra("reminder");
        mContent.setText(reminder.getNoidung());
        mImportant.setText(reminder.isQuantrong() ? "Yes" : "No");
    }
}