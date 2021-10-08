package com.zhangmegan.lab06b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager2 vp;
    RecyclerView.Adapter mfragmentStateAdapter;
    int NUM_ITEMS = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.container);
        // create an adapter for viewpager2
        mfragmentStateAdapter = new MyFragmentStateAdapter(this);
        vp.setAdapter(mfragmentStateAdapter);
    }

    private class MyFragmentStateAdapter extends FragmentStateAdapter {

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(vp, position);
        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }
    }
}