package com.vp.f1;

import java.util.List;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.widget.Toast;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HomeFragment extends Fragment
{
    private List<Racer> racers;
    private RacerAdapter racerAdapter;
    private RecyclerView racersRecyclerView;
    private DatabaseReference racersDatabaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);
        racersRecyclerView = view.findViewById(R.id.racers_recyclerview);

        racersRecyclerView.setHasFixedSize(true);
        racersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        racers = new ArrayList<>();
        racerAdapter = new RacerAdapter(getActivity(), racers);

        racersRecyclerView.setAdapter(racerAdapter);
        racersDatabaseReference = FirebaseDatabase.getInstance().getReference("racers");

        fetchRacers();
        return view;
    }

    private void fetchRacers()
    {
        racersDatabaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                racers.clear();
                for (DataSnapshot racerSnapshot : dataSnapshot.getChildren())
                {
                    Racer racer = racerSnapshot.getValue(Racer.class);
                    racers.add(racer);
                }
                racerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            { Toast.makeText(getActivity(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show(); }
        });
    }
}