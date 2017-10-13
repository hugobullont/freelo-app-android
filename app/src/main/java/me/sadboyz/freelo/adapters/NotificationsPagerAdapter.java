package me.sadboyz.freelo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.sadboyz.freelo.fragments.FollowingFreelosFragment;
import me.sadboyz.freelo.fragments.PublishedFreelosFragment;
import me.sadboyz.freelo.fragments.YourFreelosFragment;

/**
 * Created by hugo on 7/10/17.
 */

public class NotificationsPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    YourFreelosFragment tabYour;
    FollowingFreelosFragment tabFoll;
    PublishedFreelosFragment tabPub;

    public NotificationsPagerAdapter(FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                if(tabYour != null) return tabYour;
                 tabYour = new YourFreelosFragment();
                return tabYour;
            case 1:
                if(tabFoll != null) return tabFoll;
                tabFoll = new FollowingFreelosFragment();
                return tabFoll;
            case 2:
                if(tabPub!=null)return tabPub;
                tabPub = new PublishedFreelosFragment();
                return tabPub;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
