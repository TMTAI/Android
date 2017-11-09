package ctu.tmtai.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ctu.tmtai.com.quanlytiendien.FragmentTabListCustomer;
import ctu.tmtai.com.quanlytiendien.FragmentTabMaps;
import ctu.tmtai.com.quanlytiendien.MapsActivity;

/**
 * Created by tranm on 04-Aug-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new FragmentTabListCustomer();
                break;
            case 1:
                frag = new FragmentTabMaps();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "List Customer";
                break;
            case 1:
                title = "Maps";
                break;
        }
        return title;
    }
}
