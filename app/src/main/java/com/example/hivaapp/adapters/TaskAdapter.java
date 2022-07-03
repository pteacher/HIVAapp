package com.example.hivaapp.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hivaapp.MainActivity;
import com.example.hivaapp.R;
import com.example.hivaapp.TasksActivity;
import com.example.hivaapp.models.Task;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class TaskAdapter extends FirebaseRecyclerAdapter<Task, TaskAdapter.taskViewholder> {
    public TaskAdapter(
            @NonNull FirebaseRecyclerOptions<Task> options) {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull taskViewholder holder,
                     int position, @NonNull Task model) {

        holder.date.setText(model.getDate());
        holder.suggestion_text.setText(model.getSuggestion_text());

        int pos = 0;
        if (model.getStatus().equals("new")) {
            holder.layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (model.getStatus().equals("inprogress")) {
            holder.layout.setBackgroundColor(Color.parseColor("#fbec5d"));
            pos = 1;
        }
        if (model.getStatus().equals("done")) {
            holder.layout.setBackgroundColor(Color.parseColor("#90ee90"));
            pos = 2;
        }
        if (model.getStatus().equals("rejected")) {
            holder.layout.setBackgroundColor(Color.parseColor("#cd4a4c"));
            pos = 3;
        }
        Spinner spinner = holder.status;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(holder.suggestion_text.getContext(),
                R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(pos);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {
                if (model.getUserAction()) {
                    DatabaseReference ref = getRef(position);
                    switch (pos) {
                        case 0: model.setStatus("new"); break;
                        case 1: model.setStatus("inprogress"); break;
                        case 2: model.setStatus("done"); break;
                        case 3: model.setStatus("rejected"); break;
                    }
                    ref.setValue(model);
                } else {
                    model.setUserAction(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @NonNull
    @Override
    public taskViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task, parent, false);
        return new TaskAdapter.taskViewholder(view);
    }

    class taskViewholder
            extends RecyclerView.ViewHolder {
        TextView author;
        TextView category;
        TextView date;
        Spinner status;
        TextView suggestion_text;
        View layout;

        public taskViewholder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            suggestion_text = itemView.findViewById(R.id.suggestion_text);
            status = itemView.findViewById(R.id.status_spinner);
            layout = itemView.findViewById(R.id.task_card);
        }
    }
}
