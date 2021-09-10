package com.xandone.vpknife;

import com.test.vpknife.BindVp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author Admin
 * created on: 2021/9/10 10:21
 * description:
 */

public class MyVpStateAdapter extends FragmentStatePagerAdapter {
    List<Fragment> datas;

    public MyVpStateAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public void bindData(List<Fragment> list) {
        this.datas = list;
    }
}
