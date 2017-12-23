package com.city.smart.raspberrytest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class SettingFragment extends Fragment {
    int viewnum = 0;
    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("응답 설정");
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        final Button btn_setiv = (Button)view.findViewById(R.id.btn_setting_setiv);
        final ImageView iv_set = (ImageView)view.findViewById(R.id.iv_setting_setiv);

        btn_setiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewnum == 0) {
                    viewnum = 1;
                    iv_set.setImageResource(R.drawable.rdcord2);
                } else {
                    viewnum = 0;
                    iv_set.setImageResource(R.drawable.record1);
                }
            }
        });
        return view;
    }
}
