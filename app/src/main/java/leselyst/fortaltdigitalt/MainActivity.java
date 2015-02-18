package leselyst.fortaltdigitalt;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import leselyst.fortaltdigitalt.fragments.story.StoryFragment;


public class MainActivity extends Activity implements FragmentCommunication {

    private FragmentManager fragmentManager;
    private ImageButton storyButton;
    private ImageButton animalsButton;
    private ImageButton gamesButton;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
/* This can be done to start sounds
//        MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.forest_sound);
//        mp.start();
*/
        bindViews();
        addButtonListeners();
        addFragmentManagerListener();
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
        super.onBackPressed();
        System.out.println(fragmentManager.getBackStackEntryCount());
        if(fragmentManager.getBackStackEntryCount() == 0) {
            finish();
            System.exit(0);
        }
        else{
            prevFragment();
        }
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//    }



    private void bindViews(){
        storyButton = (ImageButton)findViewById(R.id.storyButton);
        animalsButton = (ImageButton) findViewById(R.id.animalButtpn);
        gamesButton = (ImageButton) findViewById(R.id.gameButton);
    }

    private void addButtonListeners(){
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextFragment();
            }
        });
    }

    private void addFragmentManagerListener(){
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
            }
        });
    }

    @Override
    public void nextFragment(){
        currentPage = currentPage++;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.flip_right_in, R.animator.flip_right_out,R.animator.flip_left_in,R.animator.flip_left_out);
        StoryFragment storyFragment = StoryFragment.newInstance(Integer.toString(currentPage),null);
        fragmentTransaction.add(android.R.id.content, storyFragment).addToBackStack(null).commit();
        System.out.println(currentPage);
    }

    @Override
    public void prevFragment(){
        fragmentManager.popBackStack();
        currentPage = currentPage--;
        System.out.println(currentPage);
    }

    @Override
    public void fragmentBackButtonPressed() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }




    /*
    use this to call the custom dialog
    source: http://stackoverflow.com/questions/13341560/how-to-create-a-custom-dialog-box-in-android
    CustomDialogClass cdd = new CustomDialogClass(MainActivity.this);
    cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    cdd.show();
     */
}
