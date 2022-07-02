package com.example.hivaapp;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hivaapp.adapters.TaskAdapter;
import com.example.hivaapp.models.Task;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.snapshot.Node;

public class TasksActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference("suggestions");

        Query onlyInProgress = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .orderByChild("status")
                .equalTo("inprogress");

        Query onlyDone = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .orderByChild("status")
                .equalTo("done");

        Query onlyRejected = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .orderByChild("status")
                .equalTo("rejected");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);

        setContentView(R.layout.activity_tasks);

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyInProgress, com.example.hivaapp.models.Task.class)
                .build();
        taskAdapter = new TaskAdapter(options);
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        taskAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        taskAdapter.stopListening();
    }

    public void getNewTasks(View view) {
        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .orderByChild("status")
                .equalTo("new");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, com.example.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);
    }

    public void getInProgressTasks(View view) {
        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .orderByChild("status")
                .equalTo("inprogress");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, com.example.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);
    }

    public void getDoneTasks(View view) {
        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .orderByChild("status")
                .equalTo("done");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, com.example.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);
    }

    public void getRejectedTasks(View view) {
        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .orderByChild("status")
                .equalTo("rejected");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, com.example.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);
    }
}