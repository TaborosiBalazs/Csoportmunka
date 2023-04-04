package com.vp.f1;

import java.util.List;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import android.support.annotation.Nullable;
import com.google.firebase.auth.FirebaseAuth;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FavoritesFragment extends Fragment
{
    private String userId;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private RacerAdapter racerAdapter;
    private List<Racer> favoriteRacers;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_favorites_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("favorites").child(userId);

        recyclerView = view.findViewById(R.id.favorite_racers_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoriteRacers = new ArrayList<>();
        loadFavoriteRacers();

        return view;
    }

    private void loadFavoriteRacers()
    {
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                favoriteRacers.clear();

                for (DataSnapshot racerSnapshot : dataSnapshot.getChildren())
                {
                    Racer racer = racerSnapshot.getValue(Racer.class);
                    favoriteRacers.add(racer);
                }
                racerAdapter = new RacerAdapter(getContext(), favoriteRacers);
                recyclerView.setAdapter(racerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}