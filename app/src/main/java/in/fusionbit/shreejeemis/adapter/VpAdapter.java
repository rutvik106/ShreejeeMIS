package in.fusionbit.shreejeemis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class VpAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> titles;


    public VpAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        titles = new ArrayList<>();
    }

    public void addFragment(final Fragment fragment, final String title) {
        fragmentList.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
