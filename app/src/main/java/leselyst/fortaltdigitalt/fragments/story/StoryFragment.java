package leselyst.fortaltdigitalt.fragments.story;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    private Button next;
    private Button prev;
    private TextView currentPageLbl;

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


    private void addButtonListeners(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.nextFragment();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.prevFragment();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int layout = getLayout(pageNumber);
        View view =  inflater.inflate(layout, container, false);
        next = (Button) view.findViewById(R.id.next_btn);
        prev = (Button) view.findViewById(R.id.prev_btn);
        currentPageLbl = (TextView) view.findViewById(R.id.current_pg_lbl);
        currentPageLbl.setText(""+pageNumber);
        addButtonListeners();
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        /*view.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                System.out.println("key pressed");
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    mListener.fragmentBackButtonPressed();
                    System.out.println("working");
                    return true;
                }
                return false;
            }
        });*/

        return view;


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

    private int getLayout(int page){
        int layout;
        switch (page){
            case 1:
                layout = R.layout.page1;
                break;
            case 2:
                layout = R.layout.page2;
                break;
            default:
                layout = R.layout.page1;
        }
        return layout;
    }

}
