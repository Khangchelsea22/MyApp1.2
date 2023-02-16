package com.tuananh.aptrotrain;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private CustomAdapter mCustomAdapter;
    private ArrayList<Reminder> mListReminder = new ArrayList<Reminder>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initialize();
        String test = "Test thanh cong";
    }

    private void initialize() {
        for (int i = 0; i < 10; i++) {
            mListReminder.add(new Reminder("Buy cream", i % 2 == 0));
        }
        mListView = findViewById(R.id.lv_reminder);
        mCustomAdapter = new CustomAdapter(this, R.layout.list_item, mListReminder);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemClickListener(this);
        registerForContextMenu(mListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId()) {
            case R.id.item_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View content = LayoutInflater.from(this).inflate(R.layout.content_dialog, null,
                        false);


                TextView tvTitle = content.findViewById(R.id.tv_title);
                EditText edtContent = content.findViewById(R.id.edt_content);
                CheckBox cbImportant = content.findViewById(R.id.cb_important);
                tvTitle.setBackgroundColor(Color.BLUE);
                tvTitle.setTextColor(Color.WHITE);
                tvTitle.setText("Edit Reminder");
                edtContent.setText(mListReminder.get(position).getNoidung());
                cbImportant.setChecked(mListReminder.get(position).isQuantrong());
                builder.setView(content);
                builder.setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,
                                        int i) {
                        Reminder reminder = new Reminder(edtContent.getText().toString(),
                                cbImportant.isChecked());
                        mListReminder.set(position, reminder);
                        mCustomAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.item_delete:
                mListReminder.remove(position);
                mCustomAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View content = LayoutInflater.from(this).inflate(R.layout.content_dialog,
                        null, false);
                TextView tvTitle = content.findViewById(R.id.tv_title);
                EditText edtContent = content.findViewById(R.id.edt_content);
                CheckBox cbImportant = content.findViewById(R.id.cb_important);
                tvTitle.setText("New Reminder");
                tvTitle.setTextColor(Color.RED);
                tvTitle.setBackgroundColor(Color.YELLOW);
                builder.setView(content);
                builder.setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,
                                        int i) {
                        Reminder reminder = new Reminder(edtContent.getText().toString(),
                                cbImportant.isChecked());
                        mListReminder.add(reminder);
                        mCustomAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.item_exit:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView,
                            View view,
                            int position,
                            long l) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("reminder", mListReminder.get(position));
        startActivity(intent);
    }
}