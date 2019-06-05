package com.example.linguachat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.QueryListener;

import java.util.HashMap;
import java.util.Map;

public class SectionsActivity extends AppCompatActivity {
    private static final String TAG = "SectionsActivity";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private boolean isFinished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        final Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String sectionTitle = bundle.getString("section_title");
            boolean isVisited = bundle.getBoolean("section_is_visited");
            int sectionId = bundle.getInt("section_id");

            mAuth = FirebaseAuth.getInstance();
            if (mAuth.getCurrentUser() != null) {
                user = mAuth.getCurrentUser();

                Map<String, Object> docData = new HashMap<>();
                docData.put("section_started", true);
//                docData.put("completed_users", Arrays.asList(user.getEmail()));

                if (isFinished){
                    docData.put("section_completed", true);
                }

                switch (sectionId) {
                    case 1:
                        db.collection("Categories").document("Kirby")
                                .collection("Sections").document("Section01")
                                .update(docData);
                        break;
                    case 2:
                        db.collection("Categories").document("Kirby")
                                .collection("Sections").document("Section02")
                                .update(docData);
                        break;
                    case 3:
                        db.collection("Categories").document("Kirby")
                                .collection("Sections").document("Section03")
                                .update(docData);
                        break;
                    case 4:
                        db.collection("Categories").document("Kirby")
                                .collection("Sections").document("Section04")
                                .update(docData);
                        break;
                }
            }
        }
    }
}
