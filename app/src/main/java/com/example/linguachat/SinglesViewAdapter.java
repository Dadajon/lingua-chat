package com.example.linguachat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class SinglesViewAdapter extends FirestoreRecyclerAdapter<Singles, SinglesViewAdapter.SinglesHolder> {
    private static final String TAG = "CategoriesViewAdapter";
    private Context mCtx;

    public SinglesViewAdapter(@NonNull FirestoreRecyclerOptions<Singles> options, Context mCtx) {
        super(options);
        this.mCtx = mCtx;
    }

    @Override
    protected void onBindViewHolder(@NonNull SinglesViewAdapter.SinglesHolder holder, int position, @NonNull final Singles model) {
        Glide.with(mCtx)
                .load(model.getSingles_icon())
                .into(holder.singlesIcon);

        holder.singlesTitle.setText(model.getSingles_text());
        holder.singlesCount.setText(model.getSingles_count());

        holder.singlesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onChildChanged(@NonNull ChangeEventType type, @NonNull DocumentSnapshot snapshot, int newIndex, int oldIndex) {
        super.onChildChanged(type, snapshot, newIndex, oldIndex);
    }

    @NonNull
    @Override
    public SinglesViewAdapter.SinglesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_singles_cardview, viewGroup, false);
        return new SinglesViewAdapter.SinglesHolder(view);
    }

    class SinglesHolder extends RecyclerView.ViewHolder {
        CardView singlesCard;
        ImageView singlesIcon;
        TextView singlesTitle, singlesCount;

        SinglesHolder(@NonNull View itemView) {
            super(itemView);
            singlesCard = itemView.findViewById(R.id.singles_card);
            singlesIcon = itemView.findViewById(R.id.singles_icon);
            singlesTitle = itemView.findViewById(R.id.singles_title);
            singlesCount = itemView.findViewById(R.id.singles_count);
        }
    }


}
