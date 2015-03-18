package leselyst.fortaltdigitalt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import leselyst.fortaltdigitalt.fragments.animals.AnimalsFragment;
import leselyst.fortaltdigitalt.fragments.animals.AnimalsFragmentActivity;
import leselyst.fortaltdigitalt.fragments.animals.InformationAnimalFragment;

/**
 * Created by BrageEkroll on 11.03.2015.
 */
public class MainActivity extends Activity {

    private ImageButton storyButton;
    private ImageButton animalsButton;
    private ImageButton gamesButton;
    private RelativeLayout customDialog;

    public static final String STORAGE_NAME = "SN";
    public static final String STORAGE_KEY_PAGENUMBER = "SKP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        bindViews();
    }

    private void bindViews(){
        storyButton = (ImageButton) findViewById(R.id.storyButton);
        customDialog = (RelativeLayout) findViewById(R.id.custom_dialog);
        animalsButton = (ImageButton) findViewById(R.id.animalButtpn);
        gamesButton = (ImageButton) findViewById(R.id.gameButton);
        addButtonListeners();
    }

    private void addButtonListeners(){
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btnBeginning = (Button) customDialog.findViewById(R.id.btn_beginning);
                Button btnContinue = (Button) customDialog.findViewById(R.id.btn_continue);
                customDialog.setVisibility(View.VISIBLE);

                btnBeginning.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startStory(true);
                        customDialog.setVisibility(View.INVISIBLE);
                    }
                });
                btnContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startStory(false);
                        customDialog.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }

    public void startStory(boolean fromBeginnning){
        if (fromBeginnning)
            startActivity(StoryFragmentActivity.newInstace(this).putExtra("Page", 0));
        else
            startActivity(StoryFragmentActivity.newInstace(this).putExtra("Page", getMarkedPage()));
    }

    /**
     * Uses the shared preferences to find the page number which the app exited from last time.
     *
     * @return the value stored in sharedpreferences for the marked page number
     */
    private int getMarkedPage() {
        return getSharedPreferences(STORAGE_NAME,0).getInt(STORAGE_KEY_PAGENUMBER,-1);
    }

    public void openAnimalsView(View view) {
        startActivity(AnimalsFragmentActivity.newInstace(this));
    }
}
