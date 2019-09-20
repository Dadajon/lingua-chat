package com.example.linguachat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class CategoriesViewAdapter extends FirestoreRecyclerAdapter<Categories, CategoriesViewAdapter.CategoriesHolder> {
    private static final String TAG = "CategoriesViewAdapter";
    private Context mCtx;

    public CategoriesViewAdapter(@NonNull FirestoreRecyclerOptions<Categories> options, Context mCtx) {
        super(options);
        this.mCtx = mCtx;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoriesHolder holder, int position, @NonNull final Categories model) {
        Glide.with(mCtx)
                .load(model.getCard_bg())
                .into(holder.categoryImage);

        if (model.isIs_started() && !model.isIs_inprogress()) {
            holder.btnText.setText(R.string.start_conversation);
            holder.isCategoryLocked.setVisibility(View.GONE);
            holder.isCategoryBtnLocked.setVisibility(View.GONE);
        } else if (model.isIs_inprogress() && !model.isIs_completed()) {
            holder.btnText.setText(R.string.continue_talking);
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.progressBar.setProgress(10);
            holder.isCategoryLocked.setVisibility(View.GONE);
            holder.isCategoryBtnLocked.setVisibility(View.GONE);
        } else if (model.isIs_completed()) {
            holder.btnText.setText(R.string.review);
            holder.isCategoryLocked.setVisibility(View.GONE);
            holder.isCategoryBtnLocked.setVisibility(View.GONE);
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.progressBar.setProgress(100);
        } else {
            holder.isCategoryLocked.setVisibility(View.VISIBLE);
            holder.isCategoryBtnLocked.setVisibility(View.VISIBLE);
        }

        holder.categoryDesc.setText(model.getCard_desc());
        holder.categoryLevel.setText(model.getCard_level());
        holder.categoryTitle.setText(model.getCard_title());
        final int color = Color.parseColor(model.getCard_color());
        holder.card.setCardBackgroundColor(color);
        holder.startBtn.setCardBackgroundColor(color);
        String pageNumber = model.getId() + "/11";
        holder.categoryNumber.setText(pageNumber);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!model.isIs_started()) {
                    dialogMessage();
                } else {
                    Intent intent = new Intent(mCtx, CategoriesInnerActivity.class);
                    intent.putExtra("category_bg", model.getCard_bg());
                    intent.putExtra("category_desc", model.getCard_desc());
                    intent.putExtra("category_level", model.getCard_level());
                    intent.putExtra("category_title", model.getCard_title());
                    intent.putExtra("category_color", color);
                    intent.putExtra("category_id", model.getId());
                    mCtx.startActivity(intent);
                }
            }
        });

        holder.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!model.isIs_started()) {
                    dialogMessage();
                } else {
                    Intent intent = new Intent(mCtx, CategoriesInnerActivity.class);
                    intent.putExtra("category_bg", model.getCard_bg());
                    intent.putExtra("category_desc", model.getCard_desc());
                    intent.putExtra("category_level", model.getCard_level());
                    intent.putExtra("category_title", model.getCard_title());
                    intent.putExtra("category_color", color);
                    intent.putExtra("category_id", model.getId());
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

    @Override
    public void onChildChanged(@NonNull ChangeEventType type, @NonNull DocumentSnapshot snapshot, int newIndex, int oldIndex) {
        super.onChildChanged(type, snapshot, newIndex, oldIndex);
    }

    @NonNull
    @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_categories_cardview, viewGroup, false);
        return new CategoriesHolder(view);
    }

    class CategoriesHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage, isCategoryLocked, isCategoryBtnLocked;
        TextView categoryTitle, categoryDesc, categoryLevel, categoryNumber, btnText;
        RelativeLayout pLayout;
        CardView card, startBtn;
        ProgressBar progressBar;

        CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.card_bg);
            isCategoryLocked = itemView.findViewById(R.id.is_category_locked);
            isCategoryBtnLocked = itemView.findViewById(R.id.is_category_btn_locked);
            categoryTitle = itemView.findViewById(R.id.card_title);
            categoryDesc = itemView.findViewById(R.id.card_desc);
            categoryLevel = itemView.findViewById(R.id.card_level);
            categoryNumber = itemView.findViewById(R.id.card_number);
            pLayout = itemView.findViewById(R.id.pLayout);
            card = itemView.findViewById(R.id.parentLayout);
            startBtn = itemView.findViewById(R.id.start_conversation_btn);
            btnText = itemView.findViewById(R.id.card_btn);
            progressBar = itemView.findViewById(R.id.category_progress);
        }
    }


}
