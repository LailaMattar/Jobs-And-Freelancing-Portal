package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jopy.R;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class SelectTypeActivity extends AppCompatActivity {

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_select_type);


        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.up);
        // Implement it's on click listener.
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 2;//institution
                // Show a toast message.
                Toast.makeText(SelectTypeActivity.this, "Institution", Toast.LENGTH_SHORT).show();
                Intent employeeIntent = new Intent(SelectTypeActivity.this,CreateAccountCommonActivity.class);
                employeeIntent.putExtra("type",type);
                startActivity(employeeIntent);
            }
        });

        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.down);
        // Implement it's on click listener.
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "onClick: test employee");
                type = 1;//employee
                // Show a toast message.
                Toast.makeText(SelectTypeActivity.this, "Employee", Toast.LENGTH_SHORT).show();
                Intent employeeIntent = new Intent(SelectTypeActivity.this,CreateAccountCommonActivity.class);
                employeeIntent.putExtra("type",type);
                startActivity(employeeIntent);
            }
        });






    }
}

