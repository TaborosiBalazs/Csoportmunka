package com.example.f1;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public interface RecyclerViewInterface {
    void onItemClicked(String name, String car, String team);
}
