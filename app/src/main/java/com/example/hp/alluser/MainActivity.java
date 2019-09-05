package com.example.hp.alluser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button BtnFaculty;
    private TextView tvfaaculty,Tvdept,Tvweb,Tvcalerder;
    private ImageView img,imgDept,imgWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hiding the title bar

        //getSupportActionBar().hide();

       getSupportActionBar().setTitle("BSMRSTU TEACHER APP");

        //Adding logo on acitonbar

        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        tvfaaculty=findViewById(R.id.Tvfaculty);
        img=findViewById(R.id.img);
        Tvdept=findViewById(R.id.Tvdept);
        imgDept=findViewById(R.id.imgDept);
        Tvweb=findViewById(R.id.Tvweb);
        imgWebsite=findViewById(R.id.imgWebsite);
        Tvcalerder=findViewById(R.id.Tvcalender);

        tvfaaculty.setOnClickListener(this);
        img.setOnClickListener(this);
        Tvdept.setOnClickListener(this);
        imgDept.setOnClickListener(this);
        imgWebsite.setOnClickListener(this);
        Tvweb.setOnClickListener(this);
        //Tvcalerder.setOnClickListener(this);





    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Tvfaculty){
            Intent intent=new Intent(MainActivity.this,Faculty.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.img){
            Intent intent=new Intent(MainActivity.this,Faculty.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.Tvdept){
            Intent intent=new Intent(MainActivity.this,All_Department.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.imgDept){
            Intent intent=new Intent(MainActivity.this,All_Department.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.Tvweb){
            Intent intent=new Intent(MainActivity.this,Webview.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.imgWebsite){
            Intent intent=new Intent(MainActivity.this,Webview.class);
            startActivity(intent);
        }

       /* if(view.getId()==R.id.Tvcalender){
            Intent intent=new Intent(MainActivity.this,Null_Activity.class);
            Toast.makeText(this, "calender", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }*/

    }
}
