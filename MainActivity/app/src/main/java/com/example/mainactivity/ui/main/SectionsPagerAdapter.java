package com.example.mainactivity.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mainactivity.ProfileFragment;
import com.example.mainactivity.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_home, R.string.tab_text_profile};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) { //supplies instances of fragment as new pages to be displayed
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        /*return PlaceholderFragment.newInstance(position + 1); //calls fragment class*/
        Fragment curFragment = new Fragment();
        switch (position) {
            case 0:
                curFragment = new HomeFragment();
                break;
            case 1:
                curFragment = new ProfileFragment();
                break;
        }
        return curFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) { //idk how this works but it sets the tab titles i guess
        return mContext.getResources().getString(TAB_TITLES[position]);
    }



    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}