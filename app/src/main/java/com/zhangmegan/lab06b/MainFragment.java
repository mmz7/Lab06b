package com.zhangmegan.lab06b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragment extends Fragment{
    ViewPager2 vp;
    int position;
    public static Fragment newInstance(ViewPager2 myVP2, int position) {
        MainFragment fragment = new MainFragment();
        fragment.vp = myVP2;
        fragment.position=position;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String array[] = getResources().getStringArray(R.array.days);
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, vp, (tab, position) -> tab.setText(array[position])
            ).attach();
        TextView textView = view.findViewById(R.id.textview);
        SharedPreferences sP = getActivity().getSharedPreferences("com.zhangmegan.lab06b",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        String str = sP.getString(""+position, "");
        textView.setText(str);
        Button reset = view.findViewById(R.id.resetButton);
        reset.setText(position+"");
//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("");
//                editor.putString(position+"", "");
//                editor.apply();
//            }
//        });
    }
}
