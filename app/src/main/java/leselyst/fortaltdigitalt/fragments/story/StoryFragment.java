package leselyst.fortaltdigitalt.fragments.story;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import leselyst.fortaltdigitalt.MainActivity;
import leselyst.fortaltdigitalt.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoryFragment extends Fragment{
    // the fragment initialization parameters, e.g. ARG_PAGE_NUMBER
    private static final String ARG_PAGE_NUMBER = "page number";

    // TODO: Rename and change types of parameters
    private int pageNumber;

    private LinearLayout menuBar;
    private ImageButton menuButton;
    private TextView page;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pageNumber Parameter 1.
     * @return A new instance of fragment StoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoryFragment newInstance(int pageNumber) {
        StoryFragment fragment = new StoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public StoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageNumber = getArguments().getInt(ARG_PAGE_NUMBER);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layout = getLayout(pageNumber);
        View view =  inflater.inflate(layout, container, false);
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        menuBar = (LinearLayout) view.findViewById(R.id.menu_bar);
        menuButton = (ImageButton) view.findViewById(R.id.menu_button);

        if(menuBar != null) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getRawY() <= menuBar.getY()) {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 0);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START, 1);
                        menuBar.setLayoutParams(layoutParams);
                        menuButton.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
            });
            ImageButton homeButton = (ImageButton) menuBar.findViewById(R.id.home_button);
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
            page = (TextView) menuBar.findViewById(R.id.page_nr);
            page.setText("Side: "+(pageNumber+1) + " / " + 20);
        }


        if(menuButton != null) {
            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (menuBar.getLayoutParams().height == 0) {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) ((50*MainActivity.scale)+0.5f));
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,1);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,1);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START,1);
                        menuBar.setLayoutParams(layoutParams);
                        menuButton.setVisibility(View.GONE);
                    } else {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 0);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,1);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,1);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START, 1);
                        menuBar.setLayoutParams(layoutParams);
                        menuButton.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        return view;
    }


    private int getLayout(int page){
        int layout;
        switch (page){
            case 0:
                layout = R.layout.page1;
                break;
            case 1:
                layout = R.layout.page2;
                break;
            case 2:
                layout = R.layout.page4;
                break;
            case 3:
                layout = R.layout.page8;
                break;
            case 4:
                layout = R.layout.page9;
                break;
            case 5:
                layout = R.layout.page10;
                break;
            case 6:
                layout = R.layout.page11;
                break;
            case 7:
                layout = R.layout.page12;
                break;
            case 8:
                layout = R.layout.page13;
                break;
            default:
                layout = R.layout.page1;
                break;
        }
        return layout;
    }




}
