package com.example.linguachat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

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
        holder.categoryDesc.setText(model.getCard_desc());
        holder.categoryLevel.setText(model.getCard_level());
        holder.categoryTitle.setText(model.getCard_title());
        final int color = Color.parseColor(model.getCard_color());
        holder.card.setCardBackgroundColor(color);
        holder.startBtn.setCardBackgroundColor(color);
        String pageNumber = model.getId()+"/11";
        holder.categoryNumber.setText(pageNumber);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, CategoriesInnerActivity.class);
                intent.putExtra("category_bg", model.getCard_bg());
                intent.putExtra("category_desc", model.getCard_desc());
                intent.putExtra("category_level", model.getCard_level());
                intent.putExtra("category_title", model.getCard_title());
                intent.putExtra("category_color", color);
                intent.putExtra("category_id", model.getId());
                mCtx.startActivity(intent);
            }
        });

        holder.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, CategoriesInnerActivity.class);
                intent.putExtra("category_bg", model.getCard_bg());
                intent.putExtra("category_desc", model.getCard_desc());
                intent.putExtra("category_level", model.getCard_level());
                intent.putExtra("category_title", model.getCard_title());
                intent.putExtra("category_color", color);
                intent.putExtra("category_id", model.getId());
                mCtx.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_categories_cardview, viewGroup, false);
        return new CategoriesHolder(view);
    }

    class CategoriesHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryTitle, categoryDesc, categoryLevel, categoryNumber;
        RelativeLayout pLayout;
        CardView card, startBtn;

        CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.card_bg);
            categoryTitle = itemView.findViewById(R.id.card_title);
            categoryDesc = itemView.findViewById(R.id.card_desc);
            categoryLevel = itemView.findViewById(R.id.card_level);
            categoryNumber = itemView.findViewById(R.id.card_number);
            pLayout = itemView.findViewById(R.id.pLayout);
            card = itemView.findViewById(R.id.parentLayout);
            startBtn = itemView.findViewById(R.id.start_conversation_btn);
        }
    }
}
