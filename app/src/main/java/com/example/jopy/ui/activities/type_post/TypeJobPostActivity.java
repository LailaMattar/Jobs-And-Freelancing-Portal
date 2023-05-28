package com.example.jopy.ui.activities.type_post;

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
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.presenters.CreatePostPresenter;
import com.example.jopy.mvp.presenters.CreatePostServicePresenter;
import com.example.jopy.mvp.presenters.GetCategoriesPresenter;
import com.example.jopy.ui.activities.HomeCompany;
import com.example.jopy.ui.activities.HomePageActivity;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class TypeJobPostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CreatePostPresenter.Listener, GetCategoriesPresenter.Listener {


    private CreatePostPresenter createPostPresenter;
    private EditText positionTJ;
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

    public Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_type_job_post);

        initView();

        getCategoriesPresenter = new GetCategoriesPresenter(TypeJobPostActivity.this, this);

        allCategories = new ArrayList<>();
        allCategories.add("Category");
        getCategoriesPresenter.sendToServer(null);




        ArrayAdapter adapterCategoryTJ = new ArrayAdapter(this, R.layout.spinner_selector_style2, allCategories);
        adapterCategoryTJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTJSpinner.setSelection(0);
        categoryTJSpinner.setAdapter(adapterCategoryTJ);
        categoryTJSpinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapterLocationTJ = ArrayAdapter.createFromResource(this, R.array.city, R.layout.spinner_selector_style2);
        adapterLocationTJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTJSpinner.setSelection(0);
        locationTJSpinner.setAdapter(adapterLocationTJ);
        locationTJSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterEmpTypTJ = ArrayAdapter.createFromResource(this, R.array.employmentType, R.layout.spinner_selector_style2);
        adapterEmpTypTJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employmentTypeTJSpinner.setSelection(0);
        employmentTypeTJSpinner.setAdapter(adapterEmpTypTJ);
        employmentTypeTJSpinner.setOnItemSelectedListener(this);


        ///Tag chip

        nachoTextViewTJ.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
        createPostPresenter = new CreatePostPresenter(TypeJobPostActivity.this, this);
 /////////////////////////////////////////////////////

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                categories = new ArrayList<>();
                tags = new ArrayList<>();


                type = "job";
                title = positionTJ.getText().toString();
                about = descriptionTJ.getText().toString();
                money =  salaryFromTJ.getText().toString()+"-"+ salaryToTJ.getText().toString();


                categories.add(catgr);
                required_experience = requiredExpTJ.getText().toString();
                employment_type = empTyp;
                location = location1;
                servise_time = "none";


                for (Chip chip : nachoTextViewTJ.getAllChips()) {
                    String textt= chip.getText().toString();
                    tags.add(textt);
                    Log.e("TAG", "onClick: tags : " + textt);
                    Object data = chip.getData();
                }
                //////////////////////////////
                if(categories == null || tags== null || location1.equals("city")|| location.equals("")||
                        catgr.equals("Category")|| empTyp.equals("Employment Type")||title.equals("")
                        || employment_type.equals("") || money.equals("")|| about.equals("")|| required_experience.equals("")){
                    Toast.makeText(TypeJobPostActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                post = new Post();
                post.setType("job");
                post.setPost_type("job");
                post.setAbout(about);
                post.setCategories(categories);
                post.setEmploymentType(employment_type);
                post.setLocation(location);
                post.setMoney(money);
                post.setRequiredExperience(required_experience);
                post.setTitle(title);
                post.setServiceTime(servise_time);
                post.setTags(tags);
//post.setImage(null);

                createPostPresenter.sendToServer(post);


            }}
        });


    }

    void initView() {
        categoryTJSpinner = findViewById(R.id.categoryTJSpinner);
        locationTJSpinner = findViewById(R.id.locationTJSpinner);
        employmentTypeTJSpinner = findViewById(R.id.employmentTypeTJSpinner);
        nachoTextViewTJ = findViewById(R.id.tagTJ);
        positionTJ = findViewById(R.id.positionTJ);
        descriptionTJ = findViewById(R.id.descriptionTJ);
        requiredExpTJ = findViewById(R.id.requiredExpTJ);
        salaryToTJ = findViewById(R.id.salaryToTJ);
        salaryFromTJ = findViewById(R.id.salaryFromTJ);
        postButton = findViewById(R.id.postTJ);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.locationTJSpinner:
                location1 = parent.getItemAtPosition(position).toString();
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                break;
            case R.id.categoryTJSpinner:
                catgr = parent.getItemAtPosition(position).toString();
                String text1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.employmentTypeTJSpinner:
                empTyp = parent.getItemAtPosition(position).toString();
                String empTyp = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), empTyp, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void categoriesDone(List<String> categories) {
        for (int i = 0; i < categories.size(); i++) {
            Log.e("TAG", "done: categories : " + categories.get(i));
            this.allCategories.add(categories.get(i));
        }
    }

    @Override
    public void categoriesFailure() {

    }

    @Override
    public void createPostDone() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(TypeJobPostActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        Log.e("TAG", "postDone: Doneeeee!!!");

        sweetAlertDialog
                .setTitleText("DONE")
                .setContentText("The post has been published successfully")
                .show();
        sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Intent intent = new Intent(TypeJobPostActivity.this, HomeCompany.class);
                sweetAlertDialog.dismiss();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                TypeJobPostActivity.this.finish();
            }
        });
    }

    @Override
    public void createPostFailure() {

    }
}
