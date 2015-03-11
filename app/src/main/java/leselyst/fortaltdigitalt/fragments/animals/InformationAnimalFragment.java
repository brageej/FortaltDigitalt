package leselyst.fortaltdigitalt.fragments.animals;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leselyst.fortaltdigitalt.FragmentCommunication;
import leselyst.fortaltdigitalt.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationAnimalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationAnimalFragment extends Fragment {

    private static final String ARG_ANIMAL = "animal";
    private int animal;

    private FragmentCommunication mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InformationAnimalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationAnimalFragment newInstance(int animal) {
        InformationAnimalFragment fragment = new InformationAnimalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ANIMAL, animal);
        fragment.setArguments(args);
        return fragment;
    }

    public InformationAnimalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            animal = getArguments().getInt(ARG_ANIMAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layout = getLayout();

        // Inflate the layout for this fragment
        return inflater.inflate(layout, container, false);
    }

    private int getLayout() {
        int layout;
        switch (animal){
            case R.string.bear:
                layout = R.layout.fragment_information_animal;
                break;
            case R.string.elk:
                layout = R.layout.fragment_information_animal;
                break;
            default:
                layout = R.layout.fragment_information_animal;
        }
        return layout;    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentCommunication) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
