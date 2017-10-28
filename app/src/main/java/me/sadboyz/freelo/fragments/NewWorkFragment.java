package me.sadboyz.freelo.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
    TextInputEditText priceTextInput;
    WorksRepository wr;
    boolean hasError = false;

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
        priceTextInput = (TextInputEditText) view.findViewById(R.id.pubPrieceInputTextView);
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
                 wr = new WorksRepository();
                try {
                    showCreateAlert(nameTextInput.getText().toString(),
                            descriptionTextInput.getText().toString(),
                            Double.parseDouble(priceTextInput.getText().toString()),
                            parseSelected((String) spinner.getSelectedItem()).getIdCategory());
                    hasError = false;
                }
                catch (Exception ex){
                    Toast.makeText(getContext(),R.string.text_error_new_work,Toast.LENGTH_SHORT).show();
                    hasError = true;
                }
                if(!hasError)
                {
                    nameTextInput.setText("");
                    descriptionTextInput.setText("");
                    priceTextInput.setText("");
                }
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

    private void showCreateAlert(final String nameWork, final String description, final Double workPrice, final String categoryID)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this.getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Crear Freelo");
        builder.setMessage("¿Estás seguro de crear este Freelo? Se descontará la inversión de tus créditos Freelo.");

        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean work = wr.AddWorkToDatabase(nameWork,description,workPrice,categoryID);
                if(work){
                    Toast.makeText(getContext(),R.string.text_success_new_work,Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(getContext(),R.string.text_no_credit_new_work,Toast.LENGTH_SHORT).show(); hasError = true;}
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

}
