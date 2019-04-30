package com.example.linguachat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SectionsViewAdapter extends FirestoreRecyclerAdapter<Sections, SectionsViewAdapter.SectionsHolder> {
    private static final String TAG = "SectionsViewAdapter";
    private Context mCtx;

    public SectionsViewAdapter(@NonNull FirestoreRecyclerOptions<Sections> options, Context mCtx) {
        super(options);
        this.mCtx = mCtx;
    }

    @Override
    protected void onBindViewHolder(@NonNull SectionsHolder holder, int position, @NonNull final Sections model) {
        if (position == 0) {
            holder.sectionIcon.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
        } else {
            holder.sectionIcon.setImageResource(R.drawable.ic_lock_black_24dp);
        }

        holder.sectionTitle.setText(model.getSection_title());
        String sectionNumber = model.getSection_id() + " : ";
        holder.sectionId.setText(sectionNumber);
        holder.sectionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, SectionsActivity.class);
                intent.putExtra("category_title", model.getSection_title());
                mCtx.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public SectionsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_sections, viewGroup, false);
        return new SectionsViewAdapter.SectionsHolder(view);
    }

    public class SectionsHolder extends RecyclerView.ViewHolder {
        RelativeLayout sectionLayout;
        ImageView sectionIcon;
        TextView sectionId, sectionTitle;

        public SectionsHolder(@NonNull View itemView) {
            super(itemView);
            sectionIcon = itemView.findViewById(R.id.section_icon);
            sectionId = itemView.findViewById(R.id.section_id);
            sectionTitle = itemView.findViewById(R.id.section_title);
            sectionLayout = itemView.findViewById(R.id.section_layout_bg);
        }
    }
}
