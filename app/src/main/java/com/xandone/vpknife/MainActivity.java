package com.xandone.vpknife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.test.vpknife.BindVp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> datas;

    @BindVp
    FragmentStatePagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        datas = new ArrayList<>();
        datas.add(new TestFragment());
        datas.add(new TestFragment());
        datas.add(new TestFragment());

        BindVpUtils.bindVp(this, viewPager, mAdapter, datas);
    }
}
