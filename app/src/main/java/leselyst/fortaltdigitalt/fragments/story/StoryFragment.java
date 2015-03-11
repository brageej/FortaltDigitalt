package leselyst.fortaltdigitalt.fragments.story;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leselyst.fortaltdigitalt.FragmentCommunication;
import leselyst.fortaltdigitalt.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoryFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PAGE_NUMBER = "page number";

    // TODO: Rename and change types of parameters
    private int pageNumber;

    private GestureDetector gestureDetector;

    private FragmentCommunication mListener;
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
        return view;


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private int getLayout(int page){
        int layout = 0;
        switch (page){
            case 0:
                layout = R.layout.page1;
                break;
            case 1:
                layout = R.layout.page2;
                break;
            case 2:
                layout = R.layout.page3;
                break;
            case 3:
                layout = R.layout.page4;
                break;
            case 4:
                layout = R.layout.page5;
                break;
            default:
                layout = R.layout.page1;
                break;
        }
        return layout;
    }

}
