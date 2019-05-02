package com.example.linguachat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SectionsActivity extends AppCompatActivity {
    private static final String TAG = "SectionsActivity";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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

            Map<String, Object> docData = new HashMap<>();
            docData.put("section_completed", true);

            switch (sectionId) {
                case 1:
                    db.collection("Categories").document("Carl")
                            .collection("Sections").document("Section01")
                            .update(docData);
                    break;
                case 2:
                    db.collection("Categories").document("Carl")
                            .collection("Sections").document("Section02")
                            .update(docData);
                    break;
                case 3:
                    db.collection("Categories").document("Carl")
                            .collection("Sections").document("Section03")
                            .update(docData);
                    break;
            }


//                    .update(docData)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d(TAG, "DocumentSnapshot successfully updated!");
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error updating document", e);
//                        }
//                    });
        }
    }
}
