package com.example.linguachat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        if (model.isSection_started() && !model.isSection_completed()) {
            holder.sectionIcon.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
        } else if (model.isSection_completed()) {
            holder.sectionIcon.setImageResource(R.drawable.ic_check_circle_white_24dp);
        } else {
            holder.sectionIcon.setImageResource(R.drawable.ic_lock_black_24dp);
        }

        holder.sectionTitle.setText(model.getSection_title());
        String sectionNumber = model.getSection_id() + " : ";
        holder.sectionId.setText(sectionNumber);
        holder.sectionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!model.isSection_started()) {
                    dialogMessage();
                } else {
                    Intent intent = new Intent(mCtx, SectionsActivity.class);
                    intent.putExtra("section_title", model.getSection_title());
                    intent.putExtra("section_is_visited", model.isSection_completed());
                    intent.putExtra("section_id", model.getSection_id());
                    mCtx.startActivity(intent);
                }
            }
        });
    }

    private void dialogMessage() {
        final Dialog dialog = new Dialog(mCtx);
        // Include dialog.xml file
        dialog.setContentView(R.layout.locked_dialog);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View v = dialog.getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        // Set dialog title
        dialog.setTitle(R.string.level_locked);

        // set values for custom dialog components - text, image and button
        TextView text = dialog.findViewById(R.id.textDialog);
        text.setText(R.string.unlock_instructions);

        dialog.show();

        Button okButton = dialog.findViewById(R.id.okButton);
        // if decline button is clicked, close the custom dialog
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
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
