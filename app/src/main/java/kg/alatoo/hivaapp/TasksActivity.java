package kg.alatoo.hivaapp;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kg.alatoo.hivaapp.adapters.TaskAdapter;
import kg.alatoo.hivaapp.models.Task;
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
    static public String categoryChoice;
    private String[] categories = {"rector", "vicerector", "ahc_prorector", "hod", "ahc", "it", "lib", "chief", "cks", "hr", "accountant", "science prorector"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                categoryChoice= null;
            } else {
                categoryChoice= categories[extras.getInt("key")];
            }
        } else {
            categoryChoice = categories[0];
        }

        Log.d("CAT", categoryChoice);

        mDatabase = FirebaseDatabase.getInstance().getReference("suggestions");

        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .child(categoryChoice)
                .orderByChild("status")
                .equalTo("new");


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);


        setContentView(R.layout.activity_tasks);

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, kg.alatoo.hivaapp.models.Task.class)
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
                .child(categoryChoice)
                .orderByChild("status")
                .equalTo("new");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, kg.alatoo.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);
    }

    public void getInProgressTasks(View view) {
        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .child(categoryChoice)
                .orderByChild("status")
                .equalTo("inprogress");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, kg.alatoo.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);
    }

    public void getDoneTasks(View view) {
        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .child(categoryChoice)
                .orderByChild("status")
                .equalTo("done");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, kg.alatoo.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);
    }

    public void getRejectedTasks(View view) {
        Query onlyNew = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("suggestions")
                .child(categoryChoice)
                .orderByChild("status")
                .equalTo("rejected");

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(onlyNew, kg.alatoo.hivaapp.models.Task.class)
                .build();

        taskAdapter.updateOptions(options);

    }
}