package martinezruiz.javier.pmdmtarea02.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.databinding.FragmentDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String IDIMG = "idImg";
    private static final String  NAME = "name";
    private static final String DESCRIPTION = "desc";
    private static final String SKILL = "skill";

    // TODO: Rename and change types of parameters
    private int idImg;
    private String name;
    private String description;
    private String skill;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(int imgId, String name, String description, String skill) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(IDIMG, imgId);
        args.putString(NAME, name);
        args.putString(DESCRIPTION, description);
        args.putString(SKILL, skill);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idImg = getArguments().getInt(IDIMG);
            name = getArguments().getString(NAME);
            description = getArguments().getString(DESCRIPTION);
            skill = getArguments().getString(SKILL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailsBinding binding = FragmentDetailsBinding.inflate(inflater);
        binding.idName.setText(name);
        binding.idBigImg.setImageResource(idImg);
        binding.idDescription.setText(description);
        binding.idSkill.setText(skill);

        StringBuilder psBuilder = new StringBuilder();
        psBuilder.append(this.getResources().getString(R.string.select_pj)).append(" ").append(name);
        Toast.makeText(getContext(), psBuilder.toString(), Toast.LENGTH_SHORT).show();

        // Esto seguro que se puede hacer de otra forma sin construir un nuevo FragmentHome
        //el popStack me devuelve false
        binding.idBtnBack.setOnClickListener(v -> {
            Fragment f = new FragmentHome();
            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.contenedor, f)
                    .commit();
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}