package com.example.f1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    MainAdapter mainAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>().
                        setQuery(FirebaseDatabase.getInstance().
                                getReference().child("contestants"), MainModel.class).
                        build();

        mainAdapter = new com.example.f1.MainAdapter(options, this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void onItemClicked(String name, String car, String team) {
        Intent intent = new Intent(MainActivity.this, MainItemActivity.class);

        intent.putExtra("Name", name);
        intent.putExtra("Car", car);
        intent.putExtra("Team", team);
        startActivity(intent);
    }

    protected void onStart(){
        super.onStart();
        mainAdapter.startListening();
    }

    protected void onStop(){
        super.onStop();
        mainAdapter.stopListening();
    }
}

