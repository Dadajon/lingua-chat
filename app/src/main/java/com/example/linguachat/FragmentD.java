package com.example.linguachat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentD extends Fragment {
    private static final String TAG = "FragmentD";

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoriesRef = db.collection("Categories");
    final DocumentReference usersRef = db.document("Users/dadajonjurakuziev@gmail.com");
    private String profile_providerId, profile_name, profile_email;
    Uri profile_photoUrl;

    //layout vars
    TextView profileProvider, profileName, profileEmail, sentences_spoken, concepts_learned, days_practiced, longest_streaks;
    CircleImageView profilePhoto;
    Button resetPassword, logoutButton, deleteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if (mAuth.getCurrentUser() != null) {
            //user has already signed in
            Log.d("AUTH", mAuth.getCurrentUser().getEmail());

//            user.sendEmailVerification()
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d(TAG, "EmailVerification successfully sent!");
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error sending EmailVerification", e);
//                        }
//                    });

            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
//                profile_providerId = profile.getProviderId();

                // Name, email address, and profile photo Url
                profile_name = profile.getDisplayName();
                profile_email = profile.getEmail();
                profile_photoUrl = profile.getPhotoUrl();
            }


        } else {
            //If there is no user
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("some", "some data");
            startActivity(intent);
        }

        profilePhoto = view.findViewById(R.id.profilePhoto);
        profileName = view.findViewById(R.id.profileName);
        profileEmail = view.findViewById(R.id.profileEmail);
        resetPassword = view.findViewById(R.id.profileResetPassword);
        logoutButton = view.findViewById(R.id.profileLogout);
        deleteButton = view.findViewById(R.id.profileDelete);

        sentences_spoken = view.findViewById(R.id.sentences_spoken);
        concepts_learned = view.findViewById(R.id.concepts_learned);
        days_practiced = view.findViewById(R.id.days_practiced);
        longest_streaks = view.findViewById(R.id.longest_streak);


        Glide.with(this)
                .load(profile_photoUrl)
                .into(profilePhoto);
        profileName.setText(profile_name);
        profileEmail.setText(profile_email);

        usersRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
//                    sentences_spoken.setText(snapshot.get("sentences_spoken").toString());
                    concepts_learned.setText(snapshot.get("concepts_learned").toString());
                    days_practiced.setText(snapshot.get("days_practiced").toString());
                    longest_streaks.setText(snapshot.get("longest_streak").toString());

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(user.getEmail())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "PasswordResetEmail successfully sent!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "Error sending PasswordResetEmail!");
                            }
                        });
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("AUTH", "USER LOGGED OUT!");
                                Intent intent = new Intent(getActivity(), SignInActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .delete(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("AUTH", "USER WAS DELETED!");
                                Intent intent = new Intent(getActivity(), SignInActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });

        return view;
    }
}
