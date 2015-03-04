package leselyst.fortaltdigitalt.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import leselyst.fortaltdigitalt.FragmentCommunication;
import leselyst.fortaltdigitalt.R;
import leselyst.fortaltdigitalt.CustomDialogClass;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private ImageButton storyButton;
    private ImageButton animalsButton;
    private ImageButton gamesButton;

    private FragmentCommunication mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        storyButton = (ImageButton)view.findViewById(R.id.storyButton);
        animalsButton = (ImageButton) view.findViewById(R.id.animalButtpn);
        gamesButton = (ImageButton) view.findViewById(R.id.gameButton);

        addButtonListeners();

        return view;
    }

    private void addButtonListeners(){
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CustomDialogClass dialog = new CustomDialogClass(getActivity());
                Window dialogWindow = dialog.getWindow();

                dialogWindow.getAttributes().x = (int) storyButton.getX() + storyButton.getWidth();
                dialogWindow.getAttributes().y = (int) storyButton.getY() - storyButton.getHeight();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dialog.beginningBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.startStory(true);
                        dialog.dismiss();
                    }
                });
                dialog.continueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.startStory(false);
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentCommunication) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentCommunication");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
