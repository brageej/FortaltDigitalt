package leselyst.fortaltdigitalt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import leselyst.fortaltdigitalt.fragments.story.StoryFragment;


public class StoryFragmentActivity extends FragmentActivity{

    public static final String STORAGE_NAME = "SN";
    public static final String STORAGE_KEY_PAGENUMBER = "SKP";
    private FragmentStatePagerAdapter fragmentPagerAdapter;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vpPager);
        fragmentPagerAdapter = new MyPageAdapter(getSupportFragmentManager(),16);
        viewPager.setAdapter(fragmentPagerAdapter);
        Bundle bundle = getIntent().getExtras();
        viewPager.setCurrentItem(bundle.getInt("Page"));
    }

    public static Intent newInstace(Context context){
        Intent intent = new Intent(context,StoryFragmentActivity.class);
        return intent;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setMarkedPage(viewPager.getCurrentItem());
        super.onBackPressed();
    }

    /**
     * saves the pagenumber in sharedprefernces to act like a bookmark.
     *
     * @param pageNumber the number to be stored
     */
    private void setMarkedPage(int pageNumber){
        System.out.println("Saving current page: " + pageNumber);
        SharedPreferences storage = getSharedPreferences(STORAGE_NAME,0);
        storage.edit().putInt(STORAGE_KEY_PAGENUMBER,pageNumber).commit();

    }


}
