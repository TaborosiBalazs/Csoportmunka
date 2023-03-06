package com.example.f1;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.f1.MainModel;
import com.example.f1.RecyclerViewInterface;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private RecyclerViewInterface recyclerViewInterface;
    private ArrayList<String> cName = new ArrayList<>();
    private ArrayList<String> car = new ArrayList<>();
    private ArrayList<String> team = new ArrayList<>();

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options, RecyclerViewInterface recyclerViewInterface)
    {
        super(options);
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        cName.add(model.getName());
        car.add(model.getCar());
        team.add(model.getTeam());
        holder.name.setText(model.getName());
        Glide.with(holder.img.getContext()).load(model.getImg()).
                placeholder(com.firebase.ui.database.R.drawable.
                        common_google_signin_btn_icon_dark).
                circleCrop().error(com.firebase.ui.database.R.drawable.
                        common_google_signin_btn_icon_dark_normal).
                into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view, recyclerViewInterface);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name;

        public myViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nameText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectedIndex = getAbsoluteAdapterPosition();
                    recyclerViewInterface.onItemClicked(cName.get(selectedIndex), car.get(selectedIndex), team.get(selectedIndex));
                }
            });
        }
    }
}
