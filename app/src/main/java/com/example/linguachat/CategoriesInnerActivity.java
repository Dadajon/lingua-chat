package com.example.linguachat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;


public class CategoriesInnerActivity extends AppCompatActivity {
    private CollectionReference sectionsRef;
    private SectionsViewAdapter adapter;

    ScrollView scrollView;
    CardView cardView;
    ImageView category_bg;
    TextView category_title, category_desc;
    MultiSnapRecyclerView multiSnapRecyclerView;
    private static final String TAG = "CategoriesInnerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_inner);

        getIncomingIntent();
        setUpRecyclerView();
    }

    private void getIncomingIntent() {
        final Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String categoryBg = bundle.getString("category_bg");
            String categoryDesc = bundle.getString("category_desc");
            String categoryLevel = bundle.getString("category_level");
            String categoryTitle = bundle.getString("category_title");
            int categoryColor = bundle.getInt("category_color");
            int categoryId = bundle.getInt("category_id");

            setData(categoryBg, categoryDesc, categoryLevel, categoryTitle, categoryColor, categoryId);

            switch (categoryId) {
                case 1:
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    sectionsRef = db.collection("/Categories/Kirby/Sections");
                    break;
                case 2:
                    db = FirebaseFirestore.getInstance();
                    sectionsRef = db.collection("/Categories/Cecilia/Sections");
                    break;
                case 3:
                    db = FirebaseFirestore.getInstance();
                    sectionsRef = db.collection("/Categories/Carl/Sections");
                    break;
            }
        }
    }

    private void setData(String categoryBg, String categoryDesc, String categoryLevel, String categoryTitle, int categoryColor, int categoryId) {
        scrollView = findViewById(R.id.category_inner_activity);
        cardView = findViewById(R.id.category_bg_card);
        category_bg = findViewById(R.id.category_bg_image);
        category_title = findViewById(R.id.category_title);
        category_desc = findViewById(R.id.category_desc);
        multiSnapRecyclerView = findViewById(R.id.categories_sections);

        scrollView.setBackgroundColor(categoryColor);
        cardView.setCardBackgroundColor(categoryColor);
        Glide.with(CategoriesInnerActivity.this)
                .load(categoryBg)
                .into(category_bg);
        category_title.setText(categoryTitle);
        category_desc.setText(categoryDesc);
    }

    private void setUpRecyclerView() {
        Query query = sectionsRef.orderBy("section_id", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Sections> options = new FirestoreRecyclerOptions.Builder<Sections>()
                .setQuery(query, Sections.class)
                .build();

        adapter = new SectionsViewAdapter(options, this);
        RecyclerView recyclerView = findViewById(R.id.categories_sections);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
