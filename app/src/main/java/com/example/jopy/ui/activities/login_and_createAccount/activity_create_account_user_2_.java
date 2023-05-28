package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.jopy.mvp.presenters.GetCategoriesPresenter;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class activity_create_account_user_2_ extends AppCompatActivity  implements AdapterView.OnItemSelectedListener, GetCategoriesPresenter.Listener {

    private Button next;
    private String mainProfession,educationLevel,workFiledTemp;
    private List<String> tags, categories,educationLevelArray;
    private EditText mainProfessionText;
    private Spinner educationLevelSpinner,workFieldSpinner;
    private NachoTextView nachoTextView;
    private GetCategoriesPresenter getCategoriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_create_account_user_2_);
        initView();

        getCategoriesPresenter = new GetCategoriesPresenter(activity_create_account_user_2_.this,this);
        categories             = new ArrayList<>();
        educationLevelArray    = new ArrayList<>();
        educationLevelArray.add("Education Level");
        educationLevelArray.add("Less than High school");
        educationLevelArray.add("High school");
        educationLevelArray.add("Diploma");
        educationLevelArray.add("University");
        educationLevelArray.add("Graduated ");
        educationLevelArray.add("Master ");
        educationLevelArray.add("Doctorate ");
        categories.add("Work Filed");
        getCategoriesPresenter.sendToServer(null);

        ArrayAdapter adapterEdu = new ArrayAdapter(this,R.layout.spinner_selector_style,educationLevelArray);
        adapterEdu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationLevelSpinner.setSelection(0);
        educationLevelSpinner.setAdapter(adapterEdu);
        educationLevelSpinner.setOnItemSelectedListener(this);


        ArrayAdapter adapterWork = new ArrayAdapter(this,R.layout.spinner_selector_style,categories);
        adapterWork.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workFieldSpinner.setSelection(0);
        workFieldSpinner.setAdapter(adapterWork);
        workFieldSpinner.setOnItemSelectedListener(this);





        ///Tag chip
        nachoTextView.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categories = new ArrayList<>();
                tags = new ArrayList<>();
                for (Chip chip : nachoTextView.getAllChips()) {
                    String text = chip.getText().toString();
                    tags.add(text);
                    Log.e("TAG", "onClick: tags : "+text);
                    Object data = chip.getData();
                }
                categories.add(workFiledTemp);
                mainProfession = mainProfessionText.getText().toString();
                CreateAccountCommonActivity.user.setTags(tags);
                CreateAccountCommonActivity.user.setCategories(categories);
                CreateAccountCommonActivity.user.setEducationLevel(educationLevel);
                CreateAccountCommonActivity.user.setMainProfession(mainProfession);
                Intent intent = new Intent(activity_create_account_user_2_.this,CreateCVActivity.class);
                startActivity(intent);
            }
        });
    }

    void initView(){
        next                  = findViewById(R.id.nextUser2);
        mainProfessionText    = findViewById(R.id.mainProfession);
        educationLevelSpinner = findViewById(R.id.educationLevelSpinner);
        workFieldSpinner      = findViewById(R.id.workFieldSpinner);
        nachoTextView         = findViewById(R.id.tagChip);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.educationLevelSpinner:
                educationLevel = parent.getItemAtPosition(position).toString();
                break;
            case R.id.workFieldSpinner:
                workFiledTemp = parent.getItemAtPosition(position).toString();
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void categoriesDone(List<String> categories) {
        for(int i = 0 ; i < categories.size() ; i ++){
            Log.e("TAG", "done: categories : "+ categories.get(i));
            this.categories.add(categories.get(i));
        }
    }

    @Override
    public void categoriesFailure() {

    }
}
