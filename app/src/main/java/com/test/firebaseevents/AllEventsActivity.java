package com.test.firebaseevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.firebaseevents.listener.ItemClickListener;
import com.test.firebaseevents.model.Events;
import com.test.firebaseevents.viewholder.MenuViewHolder;

public class AllEventsActivity extends AppCompatActivity {

    FirebaseRecyclerAdapter<Events, MenuViewHolder> adapter;
    RecyclerView recycler_menu;
    DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        initViews();

        loadMenu();
    }

    void initViews(){
        recycler_menu = findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        recycler_menu.setLayoutManager(new LinearLayoutManager(AllEventsActivity.this));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Events");
    }

    private void loadMenu(){

        FirebaseRecyclerOptions<Events> options = new FirebaseRecyclerOptions.Builder<Events>()
                .setQuery(databaseReference,Events.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Events, MenuViewHolder>(options) {
            @Override
            public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_item,parent,false);
                return new MenuViewHolder(itemView);
            }
            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder viewHolder, int position, @NonNull Events model) {
                Log.d("TAG", "onBindViewHolder: "+model.getCustomer_Name());
                viewHolder.txtMenuName.setText(model.getCustomer_Name());
                viewHolder.custNumber.setText(model.getCustomer_Number());
                viewHolder.tvEventType.setText(model.getEvent_Type());
                viewHolder.tvEventDesc.setText(model.getEvent_Description());
                final Events clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick){
                        Toast.makeText(AllEventsActivity.this, ""+clickItem.getCustomer_Name(), Toast.LENGTH_SHORT).show();
                    }
                });
            }


        };
        adapter.startListening();
        recycler_menu.setAdapter(adapter);
    }

}
