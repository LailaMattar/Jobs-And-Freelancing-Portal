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
import com.example.jopy.ui.activities.HomePageActivity;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class TypeFreelancePostActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener , CreatePostPresenter.Listener, GetCategoriesPresenter.Listener {

    private CreatePostPresenter createPostPresenter;
    private EditText positionTF;
    private EditText descriptionTF;
    private EditText salaryToTF;
    private EditText salaryFromTF;
    private Spinner categoryTFSpinner;
    private Button postButton;
    private NachoTextView nachoTextViewTF;


    private String type;
    private String title;
    private String about;
    private String money;
    private List<String> tags;
    private List<String> categories, allCategories;
    private String location;
    private String servise_time;
    private String catgr;

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
        setContentView(R.layout.activity_type_freelance_post);

        initView();

        getCategoriesPresenter = new GetCategoriesPresenter(TypeFreelancePostActivity.this, this);

        allCategories = new ArrayList<>();
        allCategories.add("Category");
        getCategoriesPresenter.sendToServer(null);


        ArrayAdapter adapterCategoryTF = new ArrayAdapter(this, R.layout.spinner_selector_style2, allCategories);
        adapterCategoryTF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTFSpinner.setSelection(0);
        categoryTFSpinner.setAdapter(adapterCategoryTF);
        categoryTFSpinner.setOnItemSelectedListener(this);


        ///Tag chip


        nachoTextViewTF.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
        createPostPresenter = new CreatePostPresenter(TypeFreelancePostActivity.this, this);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                categories = new ArrayList<>();
                tags = new ArrayList<>();


                type = "freelance";
                title = positionTF.getText().toString();
                about = descriptionTF.getText().toString();
                money =  salaryFromTF.getText().toString()+"-"+ salaryToTF.getText().toString();


                categories.add(catgr);
                servise_time = "none";


                for (Chip chip : nachoTextViewTF.getAllChips()) {
                    String textt= chip.getText().toString();
                    tags.add(textt);
                    Log.e("TAG", "onClick: tags : " + textt);
                    Object data = chip.getData();
                }
                //////////////////////////////

                if(categories == null || tags== null ||catgr.equals("Category")|| money.equals("")||
                        title.equals("") || about.equals("")){
                    Toast.makeText(TypeFreelancePostActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                post = new Post();
                post.setType("freelance");
                post.setPost_type("freelance");
                post.setAbout(about);
                post.setEmploymentType("Contract");
                post.setLocation("Aleppo");
                post.setCategories(categories);
                post.setMoney(money);
                post.setTitle(title);
                post.setRequiredExperience(" ");
                post.setServiceTime(servise_time);
                post.setTags(tags);
//post.setImage(null);

                createPostPresenter.sendToServer(post);


            }}
        });


    }
    void initView() {
        nachoTextViewTF=findViewById(R.id.tagTF);
        categoryTFSpinner = findViewById(R.id.categoryTFSpinner);
        positionTF = findViewById(R.id.positionTF);
        descriptionTF = findViewById(R.id.descriptionTF);
        salaryToTF = findViewById(R.id.salaryToTF);
        salaryFromTF = findViewById(R.id.salaryFromTF);
        postButton = findViewById(R.id.postTF);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.categoryTFSpinner:
                catgr = parent.getItemAtPosition(position).toString();
                String text1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text1, Toast.LENGTH_SHORT).show();
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
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(TypeFreelancePostActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        Log.e("TAG", "postDone: Doneeeee!!!");

        sweetAlertDialog
                .setTitleText("DONE")
                .setContentText("The post has been published successfully")
                .show();
        sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Intent intent = new Intent(TypeFreelancePostActivity.this, HomePageActivity.class);
                sweetAlertDialog.dismiss();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                TypeFreelancePostActivity.this.finish();
            }
        });
    }

    @Override
    public void createPostFailure() {

    }
}