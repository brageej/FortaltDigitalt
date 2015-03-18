package leselyst.fortaltdigitalt.fragments.animals;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leselyst.fortaltdigitalt.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationAnimalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationAnimalFragment extends Fragment {

    private static final String ARG_ANIMAL = "animalView";
    private int animalView;

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
            animalView = getArguments().getInt(ARG_ANIMAL);
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
        switch (animalView){
            case R.id.bearImageButton:
                return R.layout.fragment_information_bear;
            case R.id.elkImageButton:
                return R.layout.fragment_information_elk;
            case R.id.snakeImageButton:
                return R.layout.fragment_information_snake;
            case R.id.frogImageButton:
                return R.layout.fragment_information_frog;
            case R.id.owlImageButton:
                return R.layout.fragment_information_owl;
            case R.id.squirrelImageButton:
                return R.layout.fragment_information_squirrel;
        }
        return -1;
    }
}
