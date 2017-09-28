package me.sadboyz.freelo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.activities.MainActivity;
import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Category;
import me.sadboyz.freelo.repositories.CategoriesRepository;
import me.sadboyz.freelo.repositories.WorksRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWorkFragment extends Fragment {

    List<Category> categories;
    Spinner spinner;
    Button createButton;
    TextInputEditText nameTextInput;
    TextInputEditText descriptionTextInput;
    TextInputEditText prieceTextInput;
    public NewWorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        categories = new CategoriesRepository().getCategories();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_work, container, false);
        spinner = (Spinner) view.findViewById(R.id.categorySpinner);
        createButton =(Button) view.findViewById(R.id.createButton);
        nameTextInput = (TextInputEditText) view.findViewById(R.id.nameInputTextView);
        descriptionTextInput = (TextInputEditText) view.findViewById(R.id.descriptionInputTextView);
        prieceTextInput = (TextInputEditText) view.findViewById(R.id.pubPrieceInputTextView);
        try {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, toStringList(categories));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorksRepository wr = new WorksRepository();
                try {
                    wr.AddWorkToDatabase(nameTextInput.getText().toString(),
                            descriptionTextInput.getText().toString(), 0.9 * Double.parseDouble(prieceTextInput.getText().toString()),
                            Double.parseDouble(prieceTextInput.getText().toString()),
                            SessionVariables.CurrentidUser, "",
                            parseSelected((String) spinner.getSelectedItem()).getIdCategory());
                    Toast.makeText(getContext(),"Freelo Creado",Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(getContext(),"Por Favor llena todos los campos",Toast.LENGTH_SHORT).show();
                }
                nameTextInput.setText("");
                descriptionTextInput.setText("");
                prieceTextInput.setText("");


            }
        });
        return view;
    }
    public List<String> toStringList(List<Category> categories){
        List<String> nameCollector = new ArrayList<String>();
        this.categories = categories;
        for (int i =0 ; i<categories.size();i++){
            nameCollector.add(categories.get(i).getName());
        }
        return nameCollector;
    }
    public Category parseSelected(String name){
        for (int i =0; i<categories.size();i++){
            if(categories.get(i).getName()==name){
                return categories.get(i);
            }
        }
        return null;
    }

}
