package com.example.f1;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.f1.MainActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainItemActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_item_extended);

        Intent i = getIntent();
        String N = i.getStringExtra("Name");
        String CA = i.getStringExtra("Car");
        String TE = i.getStringExtra("Team");

        TextView name = findViewById(R.id.nameText);
        TextView car = findViewById(R.id.carText);
        TextView team = findViewById(R.id.teamText);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        name.setText(name.getText() + "\n\t" + N);
        car.setText(car.getText() + "\n\t" + CA);
        team.setText(team.getText() + "\n\t" + TE);
    }
}
