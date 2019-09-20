package com.example.linguachat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentB extends Fragment {
    CardView card_feel, card_dont_feel, card_for_good, card_plan_dif, card_not_used;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_b, container, false);

        card_feel = view.findViewById(R.id.card_feel);
        card_dont_feel = view.findViewById(R.id.card_dont_feel);
        card_for_good = view.findViewById(R.id.card_for_good);
        card_plan_dif = view.findViewById(R.id.card_plan_diff);
        card_not_used = view.findViewById(R.id.card_not_used);

        card_feel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), FragmentB.class);
//                startActivity(intent);
                Toast.makeText(getActivity(), "Clicked on cardView", Toast.LENGTH_SHORT).show();
            }
        });

        card_dont_feel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked on cardView", Toast.LENGTH_SHORT).show();
            }
        });

        card_for_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked on cardView", Toast.LENGTH_SHORT).show();
            }
        });

        card_plan_dif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked on cardView", Toast.LENGTH_SHORT).show();
            }
        });

        card_not_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked on cardView", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
