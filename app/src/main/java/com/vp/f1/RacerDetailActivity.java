package com.vp.f1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;

public class RacerDetailActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racer_detail);

        Button backButton = findViewById(R.id.back_button);
        TextView racerDetailName = findViewById(R.id.racer_detail_name);
        ImageView racerImageView = findViewById(R.id.racer_detail_image);
        TextView racerNameTextView = findViewById(R.id.racer_detail_name);
        TextView racerTeamTextView = findViewById(R.id.racer_detail_team);
        ImageView racerDetailImage = findViewById(R.id.racer_detail_image);
        TextView racerCountryTextView = findViewById(R.id.racer_detail_country);
        TextView racerDateOfBirthTextView = findViewById(R.id.racer_detail_dateOfBirth);

        Intent intent = getIntent();
        String racerTeam = intent.getStringExtra("racerTeam");
        String racerName = intent.getStringExtra("racerName");
        String racerImage = intent.getStringExtra("racerImage");
        String racerCountry = intent.getStringExtra("racerCountry");
        String racerDateOfBirth = intent.getStringExtra("racerDateOfBirth");

        racerDetailName.setText(racerName);
        racerTeamTextView.setText("Team: " + racerTeam);
        Picasso.get().load(racerImage).into(racerDetailImage);
        racerCountryTextView.setText("Country: " + racerCountry);
        racerDateOfBirthTextView.setText("Date of Birth: " + racerDateOfBirth);

        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)  { finish(); }
        });
    }
}