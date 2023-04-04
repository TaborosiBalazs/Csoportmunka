package com.vp.f1;

import java.util.List;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import com.squareup.picasso.Picasso;
import com.google.firebase.auth.FirebaseAuth;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RacerAdapter extends RecyclerView.Adapter<RacerAdapter.RacerViewHolder>
{
    private Context context;
    private List<Racer> racers;
    private FirebaseAuth mAuth;
    private DatabaseReference favoritesRef;

    public RacerAdapter(Context context, List<Racer> racers)
    {
        this.racers = racers;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        favoritesRef = FirebaseDatabase.getInstance().getReference("favorites").child(userId);
    }

    @NonNull
    @Override
    public RacerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_racer, parent, false);
        return new RacerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RacerViewHolder holder, int position)
    {
        Racer racer = racers.get(position);
        String racerName = racer.getName();

        holder.racerName.setText(racer.getName());
        Picasso.get().load(racer.getImg()).into(holder.racerImage);

        ImageView favoriteIcon = holder.itemView.findViewById(R.id.favorite_icon);
        checkFavoriteStatus(racerName, holder.favoriteIcon);

        holder.favoriteIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            { handleFavoriteClick(racerName, holder.favoriteIcon); }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, RacerDetailActivity.class);

                intent.putExtra("racerName", racer.getName());
                intent.putExtra("racerImage", racer.getImg());
                intent.putExtra("racerTeam", racer.getTeam());
                intent.putExtra("racerCountry", racer.getCountry());
                intent.putExtra("racerDateOfBirth", racer.getDateOfBirth());

                context.startActivity(intent);
            }
        });
    }

    private void checkFavoriteStatus(String racerId, ImageView favoriteIcon)
    {
        favoritesRef.child(racerId).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()) favoriteIcon.setImageResource(R.drawable.ic_favorite);
                else favoriteIcon.setImageResource(R.drawable.ic_favorite_border);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void handleFavoriteClick(String racerId, ImageView favoriteIcon)
    {
        favoritesRef.child(racerId).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()) dataSnapshot.getRef().removeValue();
                else favoritesRef.child(racerId).setValue(racers.get(getRacerPositionById(racerId)));

                checkFavoriteStatus(racerId, favoriteIcon);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private int getRacerPositionById(String racerId)
    {
        for (int i = 0; i < racers.size(); i++)
        { if (racers.get(i).getName().equals(racerId)) return i; }

        return -1;
    }

    @Override
    public int getItemCount()
    { return racers.size(); }

    public class RacerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView racerImage;
        TextView racerName;
        ImageView favoriteIcon;

        public RacerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            racerName = itemView.findViewById(R.id.racer_name);
            racerImage = itemView.findViewById(R.id.racer_image);
            favoriteIcon = itemView.findViewById(R.id.favorite_icon);
        }
    }
}