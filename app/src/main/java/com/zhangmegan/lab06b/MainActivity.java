package com.zhangmegan.lab06b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ViewPager2 vp;
    RecyclerView.Adapter mfragmentStateAdapter;
    int NUM_ITEMS = 7;
    int setDay = 0;
    SharedPreferences sP;
    SharedPreferences.Editor editor;
    Spinner spinner;
    Button button;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.container);
        // create an adapter for viewpager2
        mfragmentStateAdapter = new MyFragmentStateAdapter(this);
        vp.setAdapter(mfragmentStateAdapter);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        sP = getSharedPreferences("com.zhangmegan.lab06b", MODE_PRIVATE);
        editor = sP.edit();

        edit = findViewById(R.id.editText);

        button = findViewById(R.id.enter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = edit.getText().toString();
                String prev = sP.getString(""+setDay, "");
                editor.putString(""+setDay, prev+item+"\n");
                editor.apply();
            }
        });
//        String s = sP.getString(""+setDay,"");
//        button.setText(s);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        setDay = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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