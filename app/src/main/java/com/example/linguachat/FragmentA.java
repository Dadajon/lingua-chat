package com.example.linguachat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class FragmentA extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoriesRef = db.collection("Categories");
    private CategoriesViewAdapter adapter;
    View view;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_a, container, false);

        Query query = categoriesRef.orderBy("id", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Categories> options = new FirestoreRecyclerOptions.Builder<Categories>()
                .setQuery(query, Categories.class)
                .build();

        adapter = new CategoriesViewAdapter(options, getActivity());
        recyclerView = view.findViewById(R.id.categories_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter.stopListening();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Instance State: ", "onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("Instance State: ", "onViewStateRestored");
    }
}
