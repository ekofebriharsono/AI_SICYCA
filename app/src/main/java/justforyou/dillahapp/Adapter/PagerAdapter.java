package justforyou.dillahapp.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import justforyou.dillahapp.Fragment.JadwalHariIniFragment;
import justforyou.dillahapp.Fragment.JadwalMingguIniFragment;
import justforyou.dillahapp.Fragment.JadwalUASFragment;
import justforyou.dillahapp.Fragment.JadwalUTSFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int number_tabs;
    Activity activity;

    public PagerAdapter(FragmentManager fm, int number_tabs, Activity activity) {
        super(fm);
        this.number_tabs = number_tabs;
        this.activity = activity;
    }

    //Mengembalikan Fragment yang terkait dengan posisi tertentu
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new JadwalHariIniFragment(activity);
            case 1:
                return new JadwalMingguIniFragment(activity);
            case 2:
                return new JadwalUTSFragment(activity);
            case 3:
                return new JadwalUASFragment(activity);
            default:
                return null;
        }
    }

    //Mengembalikan jumlah tampilan yang tersedia.
    @Override
    public int getCount() {
        return number_tabs;
    }
}
