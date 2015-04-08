package leselyst.fortaltdigitalt;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import leselyst.fortaltdigitalt.fragments.story.StoryFragment;

/**
 * Created by BrageEkroll on 11.03.2015.
 */
public class MyPageAdapter extends FragmentStatePagerAdapter {


    private static int NUM_PAGES;

    public MyPageAdapter(FragmentManager fragmentManager, int size){
        super(fragmentManager);
        NUM_PAGES = size;
    }

    @Override
    public Fragment getItem(int position) {
        return StoryFragment.newInstance(position);
//        switch (position){
//            case 0:
//                return StoryFragment.newInstance(position);
//            case 1:
//                return StoryFragment.newInstance(position);
//            case 2:
//                return StoryFragment.newInstance(position);
//            case 3:
//                return StoryFragment.newInstance(position);
//            case 4:
//                return StoryFragment.newInstance(position);
//            case 5:
//                return StoryFragment.newInstance(position);
//            case 6:
//                return StoryFragment.newInstance(position);
//            case 7:
//                return StoryFragment.newInstance(position);
//            case 8:
//                return StoryFragment.newInstance(position);
//            case 9:
//                return StoryFragment.newInstance(position);
//            case 10:
//                return StoryFragment.newInstance(position);
//            case 11:
//                return StoryFragment.newInstance(position);
//            case 12:
//            default:
//                return null;
//        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
