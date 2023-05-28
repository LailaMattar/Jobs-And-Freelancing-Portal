package com.example.jopy.ui.activities.type_post;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.presenters.CreatePostServicePresenter;
import com.example.jopy.mvp.presenters.GetCategoriesPresenter;
import com.example.jopy.ui.activities.HomePageActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class TypeServicePostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CreatePostServicePresenter.Listener, GetCategoriesPresenter.Listener {


    private CreatePostServicePresenter createPostServicePresenter;
    private EditText positionTS;
    private EditText descriptionTS;
    private EditText priceTS;
    private EditText deliveryTS;
    private Spinner categoryTSSpinner;
    private Button postButton;
    private NachoTextView nachoTextViewTS;
    private ImageView ImageTS;
    private Button chooseImageTS;


    private String type;
    private String title;
    private String about;
    private String money;
    public static String path;
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
        setContentView(R.layout.activity_type_service_post);

        initView();

        getCategoriesPresenter = new GetCategoriesPresenter(TypeServicePostActivity.this, this);

        allCategories = new ArrayList<>();
        allCategories.add("Category");
        getCategoriesPresenter.sendToServer(null);





        ArrayAdapter adapterCategoryTS = new ArrayAdapter(this, R.layout.spinner_selector_style2, allCategories);
        adapterCategoryTS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTSSpinner.setSelection(0);
        categoryTSSpinner.setAdapter(adapterCategoryTS);
        categoryTSSpinner.setOnItemSelectedListener(this);


        chooseImageTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ImagePicker.with(TypeServicePostActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(2048)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });
        ///Tag chip

        nachoTextViewTS.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

        createPostServicePresenter = new CreatePostServicePresenter(TypeServicePostActivity.this, this);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                categories = new ArrayList<>();
                tags = new ArrayList<>();


                type = "service";
                title = positionTS.getText().toString();
                about = descriptionTS.getText().toString();
                money =  priceTS.getText().toString();


                categories.add(catgr);
                servise_time =deliveryTS.getText().toString();


                for (Chip chip : nachoTextViewTS.getAllChips()) {
                    String textt= chip.getText().toString();
                    tags.add(textt);
                    Log.e("TAG", "onClick: tags : " + textt);
                    Object data = chip.getData();
                }
                //////////////////////////////

                if(categories == null || tags== null || catgr.equals("Category")||title.equals("") || servise_time.equals("")|| money.equals("")|| about.equals("")){
                    Toast.makeText(TypeServicePostActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    post = new Post();
                    post.setType("service");
                    post.setPost_type("service");
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

                    createPostServicePresenter.sendToServer(post);


                }}
        });






    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.categoryTSSpinner:
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        path = uri.getPath();
        Log.e("TAG", "onActivityResult: "+path);
        ImageTS.setImageURI(uri);
    }

    void initView() {
        nachoTextViewTS=findViewById(R.id.tagTS);
        categoryTSSpinner = findViewById(R.id.categoryTSSpinner);
        positionTS = findViewById(R.id.titleTS);
        descriptionTS = findViewById(R.id.descriptionTS);
        deliveryTS = findViewById(R.id.deliveryTS);
        priceTS = findViewById(R.id.priceTS);
        ImageTS = findViewById(R.id.ImageTS);
        chooseImageTS = findViewById(R.id.chooseImageTS);
        postButton = findViewById(R.id.postTS);
        nachoTextViewTS = findViewById(R.id.tagTS);

    }

    @Override
    public void postDone() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(TypeServicePostActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        Log.e("TAG", "postDone: Doneeeee!!!");

         sweetAlertDialog
                .setTitleText("DONE")
                .setContentText("The post has been published successfully")
                .show();
         sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
             @Override
             public void onClick(SweetAlertDialog sweetAlertDialog) {
                 Intent intent = new Intent(TypeServicePostActivity.this, HomePageActivity.class);
                 sweetAlertDialog.dismiss();
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);
                 TypeServicePostActivity.this.finish();
             }
         });

    }

    @Override
    public void postFailure() {

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
}