package leselyst.fortaltdigitalt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import leselyst.fortaltdigitalt.fragments.HomeFragment;
import leselyst.fortaltdigitalt.fragments.story.StoryFragment;


public class MainActivity extends Activity implements FragmentCommunication {

    private FragmentManager fragmentManager;

//    private SwipeGestureDetector detector = new SwipeGestureDetector();
    private int currentPage = 0;
    private boolean storyStarted = false;
    private GestureDetectorCompat detectorCompat;
    public static final String STORAGE_NAME = "SN";
    public static final String STORAGE_KEY_PAGENUMBER = "SKP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();

        getFragmentManager().beginTransaction().replace(R.id.content_frame, HomeFragment.newInstance()).commit();


/* This can be done to start sounds
//        MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.forest_sound);
//        mp.start();
*/
        detectorCompat = new GestureDetectorCompat(this,new SwipeGestureDetector(this));
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

        Log.v("onBackPressed()","back pressed with bool: " + storyStarted);

        if(storyStarted){
            storyStarted = false;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.flip_left_in, R.animator.flip_left_out, R.animator.flip_right_in, R.animator.flip_right_out);
            fragmentTransaction.add(R.id.content_frame, HomeFragment.newInstance()).commit();
        }
        else {
            super.onBackPressed();
        }
    }

    public void startStory(boolean fromBeginnning){
        storyStarted = true;
        if (fromBeginnning)
            currentPage = 1;
        else
            currentPage = getMarkedPage();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.flip_right_in, R.animator.flip_right_out, R.animator.flip_left_in, R.animator.flip_left_out);
        fragmentTransaction.add(R.id.content_frame, StoryFragment.newInstance(currentPage)).commit();//.addToBackStack(null).commit();

    }

    /**
     * Uses the shared preferences to find the page number which the app exited from last time.
     *
     * @return the value stored in sharedpreferences for the marked page number
     */
    private int getMarkedPage() {
        return getSharedPreferences(STORAGE_NAME,0).getInt(STORAGE_KEY_PAGENUMBER,-1);
    }

    /**
     * saves the pagenumber in sharedprefernces to act like a bookmark.
     *
     * @param pageNumber the number to be stored
     */
    private void setMarkedPage(int pageNumber){
        Log.v("setMarkedPage","pagenumber: " + pageNumber);
        SharedPreferences storage = getSharedPreferences(STORAGE_NAME,0);
        storage.edit().putInt(STORAGE_KEY_PAGENUMBER,pageNumber).commit();

    }

    @Override
    public void nextFragment(){
        if(storyStarted) {
            currentPage++;
            setMarkedPage(currentPage);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.flip_right_in, R.animator.flip_right_out, R.animator.flip_left_in, R.animator.flip_left_out);
            fragmentTransaction.add(R.id.content_frame, StoryFragment.newInstance(currentPage)).commit();//.addToBackStack(null).commit();
            System.out.println(currentPage);
        }
    }

    @Override
    public void prevFragment(){
        if(storyStarted) {
            currentPage--;
            setMarkedPage(currentPage);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.flip_left_in, R.animator.flip_left_out, R.animator.flip_right_in, R.animator.flip_right_out);
            fragmentTransaction.add(R.id.content_frame, StoryFragment.newInstance(currentPage)).commit();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
