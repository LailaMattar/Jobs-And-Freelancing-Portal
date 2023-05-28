package com.example.jopy.ui.activities.edit_posts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.presenters.GetCategoriesPresenter;
import com.example.jopy.mvp.presenters.editPostPresenter;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class EditJobPostActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener , editPostPresenter.Listener, GetCategoriesPresenter.Listener {
    private EditText descriptionTJ;
    private EditText requiredExpTJ;
    private EditText salaryToTJ;
    private EditText salaryFromTJ;
    private Spinner categoryTJSpinner;
    private Spinner locationTJSpinner;
    private Spinner employmentTypeTJSpinner;
    private NachoTextView nachoTextViewTJ;
    private Button postButton;

    private String type;
    private String title;
    private String about;
    private String money;
    private List<String> tags;
    private List<String> categories, allCategories;
    private String required_experience;
    private String employment_type;
    private String location;
    private String servise_time;
    private String empTyp, location1, catgr;

    private GetCategoriesPresenter getCategoriesPresenter;
private editPostPresenter editPostPresenter;
    public Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_edit_job_post);



        Spinner ecategoryTJSpinner = findViewById(R.id.ecategoryTJSpinner);
        ArrayAdapter<CharSequence> eadapterCategoryTJ = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        eadapterCategoryTJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ecategoryTJSpinner.setAdapter(eadapterCategoryTJ);
        ecategoryTJSpinner.setOnItemSelectedListener(this);
//selected item
    ecategoryTJSpinner.setSelection(((ArrayAdapter<String>)ecategoryTJSpinner.getAdapter()).getPosition("Country"));


///get recent selected value
        ecategoryTJSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Add new category"))
                {
                    // do your stuff
                }
                Log.d("hi", selectedItem);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        ////////
        Spinner elocationTJSpinner = findViewById(R.id.elocationTJSpinner);
        ArrayAdapter<CharSequence> eadapterLocationTJ = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        eadapterLocationTJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elocationTJSpinner.setAdapter(eadapterLocationTJ);
        elocationTJSpinner.setOnItemSelectedListener(this);


        Spinner eemploymentTypeTJSpinner = findViewById(R.id.eemploymentTypeTJSpinner);
        ArrayAdapter<CharSequence> eadapterEmpTypTJ = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        eadapterEmpTypTJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       eemploymentTypeTJSpinner.setAdapter(eadapterEmpTypTJ);
        eemploymentTypeTJSpinner.setOnItemSelectedListener(this);



        ///Tag chip

        NachoTextView enachoTextViewTJ=findViewById(R.id.etagTJ);
        enachoTextViewTJ.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

        //suggest tag
        String[] suggestions = new String[]{"Tortilla Chips", "Melted Cheese", "Salsa", "Guacamole", "Mexico", "Jalapeno"};
        ArrayAdapter<String> eeadapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
        enachoTextViewTJ.setAdapter(eeadapter);
///////////////////


///to get data (tags) with button
// Iterate over all of the chips in the NachoTextView
        for (Chip chip : enachoTextViewTJ.getAllChips()) {
            // Do something with the text of each chip
            CharSequence text = chip.getText();
            // Do something with the data of each chip (this data will be set if the chip was created by tapping a suggestion)
            Object data = chip.getData();
        }


        ////////
        enachoTextViewTJ.setText("mays" +'\n'+"fer"+'\n');
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void categoriesDone(List<String> categories) {

    }

    @Override
    public void categoriesFailure() {

    }

    @Override
    public void editPostDone() {

    }

    @Override
    public void editPostFailure() {

    }
}