package com.example.jopy.ui.activities.payment;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Package1;
import com.example.jopy.mvp.models.Package2;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.AllPackagePresenter;
import com.example.jopy.mvp.presenters.BuyPackPresenter;

import java.util.ArrayList;
import java.util.List;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class E_WalletActivity extends AppCompatActivity implements  AllPackagePresenter.Listener, BuyPackPresenter.Listener {

    private BuyPackPresenter buyPackPresenter;
    private AllPackagePresenter allpackagePresenter;
    public  List<Package1> allPackages;
    public  List<Package1> normal_user_Packages;
    public  List<Package1> institute_Packages;
    private Package1 p;
    private RadioGroup radioGroupEW;
private TextView myAccount,myPosts;
private Button confEW;
private  RadioButton r0,r1,r2;
private RadioButton radioButtonpckg;
   private static List<Package1> temp;
private List<RadioButton>allRadioBtn;
private static Package2 package_id;
 private static User statUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_e__wallet);

        initView();

        buyPackPresenter=new BuyPackPresenter(E_WalletActivity.this,this);
        allpackagePresenter = new AllPackagePresenter(E_WalletActivity.this, this);
        allPackages = new ArrayList<>();
        normal_user_Packages = new ArrayList<>();
        institute_Packages = new ArrayList<>();
        temp = new ArrayList<>();
        allRadioBtn = new ArrayList<>();
        p = new Package1();
        package_id=new Package2();
        ////////////
    //    user=new User("mays","123");//test
////////////////////

        myAccount.setText(LoginActivity.user.getAccountMoney()+" S.P ");
        myPosts.setText(LoginActivity.user.getStill_have_posts()+" Posts ");

        allRadioBtn.add(r0);
        allRadioBtn.add(r1);
        allRadioBtn.add(r2);



      // user.setAccountType("company");/////////
        allpackagePresenter.sendToServer(p);


        radioGroupEW.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                radioButtonpckg = findViewById(checkedId);

                switch (radioButtonpckg.getId()) {

                    case R.id.pckg1: {

                        package_id.setPackage_id(temp.get(0).getId());
                        Log.e("TAG", "0: "+temp.get(0).getId());
                    }
                    break;
                    case R.id.pckg2: {
                        package_id.setPackage_id(temp.get(1).getId());
                        Log.e("TAG", "1: "+temp.get(1).getId());

                    }
                    break;
                    case R.id.pckg3: {
                        package_id.setPackage_id(temp.get(2).getId());
                        Log.e("TAG", "2: "+temp.get(2).getId());

                    }
                    break;

                }
            }
        });
confEW.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        buyPackPresenter.sendToServer(package_id);
    }
});

    }

    @Override
    public void allPckgDone(List<Package1> t) {
        allPackages = t;

        for (int i = 0; i < allPackages.size(); i++) {
            if (allPackages.get(i).getPackage_for().equals("normal user")) {
                normal_user_Packages.add(allPackages.get(i));
            } else {
                institute_Packages.add(allPackages.get(i));
            }
        }


       if (LoginActivity.user.getAccountTypeId()==2) {
            temp = normal_user_Packages;
        } else {
         temp = institute_Packages;
        }


        for (int i = 0; i < temp.size(); i++) {
            allRadioBtn.get(i).setText(temp.get(i).getPosts_number() +
                    " Posts for " + temp.get(i).getPrice() + " S.P ");
        }
        Log.e("TAG", "userr  money "+ LoginActivity.user.toString()+" name "+LoginActivity.user.getFirstName());

    }

        @Override
        public void allPckgFailure ()  {
        Log.e("TAG", "allPackagesFailure: failure");
        }


    void initView(){
        radioGroupEW = findViewById(R.id.radioGroupEW);
        r0 = (RadioButton) findViewById(R.id.pckg1);
        r1 = (RadioButton) findViewById(R.id.pckg2);
        r2 = (RadioButton) findViewById(R.id.pckg3);
myAccount=findViewById(R.id.accEW2);
myPosts=findViewById(R.id.postEW2);
        confEW=findViewById(R.id.confEW);
    }

    @Override
    public void buyDone() {

    }

    @Override
    public void buyFailure() {

    }
}