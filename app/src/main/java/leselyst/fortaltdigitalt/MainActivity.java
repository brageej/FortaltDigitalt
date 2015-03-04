package leselyst.fortaltdigitalt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import leselyst.fortaltdigitalt.fragments.HomeFragment;
import leselyst.fortaltdigitalt.fragments.story.CustomDialogClass;
import leselyst.fortaltdigitalt.fragments.story.StoryFragment;


public class MainActivity extends Activity implements FragmentCommunication {

    private FragmentManager fragmentManager;
//    private ImageButton storyButton;
//    private ImageButton animalsButton;
//    private ImageButton gamesButton;
//    private SwipeGestureDetector detector = new SwipeGestureDetector();
    private int currentPage = 0;
    private boolean storyStarted = false;
    private GestureDetectorCompat detectorCompat;

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
        //bindViews();
        //addButtonListeners();
        //addFragmentManagerListener();
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

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.flip_left_in, R.animator.flip_left_out, R.animator.flip_right_in, R.animator.flip_right_out);
            fragmentTransaction.add(R.id.content_frame, HomeFragment.newInstance()).commit();
        }
        else {
            super.onBackPressed();
        }
        /*
        System.out.println(fragmentManager.getBackStackEntryCount());
        if(fragmentManager.getBackStackEntryCount() == 0) {
            finish();
            System.exit(0);
        }
        else{
            prevFragment();
        }
        */

    }


//    @Override
//    public void onFragmentInteraction(Uri uri) {
//    }



    /*private void bindViews(){
        storyButton = (ImageButton)findViewById(R.id.storyButton);
        animalsButton = (ImageButton) findViewById(R.id.animalButtpn);
        gamesButton = (ImageButton) findViewById(R.id.gameButton);
    }*/

/*    private void addButtonListeners(){
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                storyStarted = true;
//                nextFragment();
//                CustomDialogClass cdd = new CustomDialogClass(MainActivity.this);
//                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                cdd.show();
                //nextFragment();
                final CustomDialogClass dialog = new CustomDialogClass(MainActivity.this);
                Window dialogWindow = dialog.getWindow();

                dialogWindow.getAttributes().x = (int) storyButton.getX() + storyButton.getWidth();
                dialogWindow.getAttributes().y = (int) storyButton.getY() - storyButton.getHeight();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dialog.beginningBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startStory(true);
                        dialog.dismiss();
                    }
                });
                dialog.continueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startStory(false);
                        dialog.dismiss();
                    }
                });
            }
        });
    }*/

    private void addFragmentManagerListener(){
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
            }
        });
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
        return 3;
    }

    /**
     * saves the pagenumber in sharedprefernces to act like a bookmark.
     *
     * @param pageNumber the number to be stored
     */
    private void setMarkedPage(int pageNumber){

    }

    @Override
    public void nextFragment(){
        if(storyStarted) {
            currentPage++;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.flip_right_in, R.animator.flip_right_out, R.animator.flip_left_in, R.animator.flip_left_out);
            fragmentTransaction.add(R.id.content_frame, StoryFragment.newInstance(currentPage)).commit();//.addToBackStack(null).commit();
            System.out.println(currentPage);
        }
    }

    @Override
    public void prevFragment(){
        if(storyStarted) {
            /*fragmentManager.popBackStack();
            currentPage = currentPage - 1;
            System.out.println(currentPage);
            if(currentPage == 0){
                storyStarted = false;
            }*/

            currentPage--;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.flip_left_in, R.animator.flip_left_out, R.animator.flip_right_in, R.animator.flip_right_out);
            fragmentTransaction.add(R.id.content_frame, StoryFragment.newInstance(currentPage)).commit();
        }
    }

    /*@Override
    public void fragmentBackButtonPressed() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        currentPage = 0;
        storyStarted = false;
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }






    /*
    use this to call the custom dialog
    source: http://stackoverflow.com/questions/13341560/how-to-create-a-custom-dialog-box-in-android
    CustomDialogClass cdd = new CustomDialogClass(MainActivity.this);
    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    cdd.show();
     */

}
