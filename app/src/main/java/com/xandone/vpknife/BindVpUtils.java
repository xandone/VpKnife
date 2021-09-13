package com.xandone.vpknife;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @author: Admin
 * created on: 2021/9/13 11:47
 * description:
 */
public final class BindVpUtils {

    public static void bindVp(FragmentActivity activity, ViewPager viewPager, FragmentStatePagerAdapter adapter, List<Fragment> fragments) {
        viewPager.setAdapter(new Adapter$Creater(activity.getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                fragments));
    }
}
