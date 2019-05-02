package com.example.linguachat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentD extends Fragment {
    private static final String TAG = "FragmentD";

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String profile_providerId, profile_name, profile_email;
    Uri profile_photoUrl;

    //layout vars
    TextView profileProvider, profileName, profileEmail;
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

            user.sendEmailVerification()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "EmailVerification successfully sent!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error sending EmailVerification", e);
                        }
                    });

            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                profile_providerId = profile.getProviderId();

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
        profileProvider = view.findViewById(R.id.profileProvider);
        profileName = view.findViewById(R.id.profileName);
        profileEmail = view.findViewById(R.id.profileEmail);
        resetPassword = view.findViewById(R.id.profileResetPassword);
        logoutButton = view.findViewById(R.id.profileLogout);
        deleteButton = view.findViewById(R.id.profileDelete);

        Glide.with(this)
                .load(profile_photoUrl)
                .into(profilePhoto);
        profileProvider.setText(profile_providerId);
        profileName.setText(profile_name);
        profileEmail.setText(profile_email);

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
