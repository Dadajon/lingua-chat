package com.example.linguachat;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class MyButtonListener implements View.OnClickListener {
    Context context;

    public MyButtonListener() {
    }

    public MyButtonListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.section_layout_bg:
                Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
