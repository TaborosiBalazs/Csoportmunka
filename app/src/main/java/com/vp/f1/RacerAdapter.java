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
import androidx.recyclerview.widget.RecyclerView;

public class RacerAdapter extends RecyclerView.Adapter<RacerAdapter.RacerViewHolder>
{
    private Context context;
    private List<Racer> racers;

    public RacerAdapter(Context context, List<Racer> racers)
    {
        this.racers = racers;
        this.context = context;
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
        holder.racerName.setText(racer.getName());
        Picasso.get().load(racer.getImg()).into(holder.racerImage);

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

    @Override
    public int getItemCount()
    { return racers.size(); }

    public class RacerViewHolder extends RecyclerView.ViewHolder
    {
        TextView racerName;
        ImageView racerImage;

        public RacerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            racerName = itemView.findViewById(R.id.racer_name);
            racerImage = itemView.findViewById(R.id.racer_image);
        }
    }
}