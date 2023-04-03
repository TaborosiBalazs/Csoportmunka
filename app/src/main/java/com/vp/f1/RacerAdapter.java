package com.vp.f1;

import java.util.List;
import android.view.View;
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