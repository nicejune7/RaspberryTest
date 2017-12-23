package com.city.smart.raspberrytest;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.city.smart.raspberrytest.dummy.DummyContent;
import com.city.smart.raspberrytest.dummy.DummyContent.DummyItem;

import java.util.List;

public class AlertFragment extends Fragment {
    int viewnum = 0;

    public AlertFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("방문 기록");
        View view = inflater.inflate(R.layout.fragment_alert, container, false);

        return view;
    }
}
