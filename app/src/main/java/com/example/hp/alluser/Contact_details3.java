package com.example.hp.alluser;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Contact_details3 extends AppCompatActivity {

    private TextView Tv_contactname,Tv_contDesg,Tv_contDept,Tv_conactFirstnum,Tv_gmail;
    private ImageView Img_contact,Img_call,Img_msg,Img_gmail;
    private CircleImageView Circle_contact;

    public static  final int REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details3);

        //Adding Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Tv_contactname=findViewById(R.id.Tv_contactname);
        Tv_contDesg=findViewById(R.id.Tv_contDesg);
        Tv_conactFirstnum=findViewById(R.id.Tv_conactFirstnum);
        Tv_gmail=findViewById(R.id.Tv_gmail);
        Circle_contact=findViewById(R.id.Circle_contact);
        Img_call=findViewById(R.id.Img_call);
        Img_msg=findViewById(R.id.Img_msg);
        Img_gmail=findViewById(R.id.Img_gmail);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            String userName=bundle.getString("name");
            String userDes=bundle.getString("des");
            String userFirstnum=bundle.getString("key");
            String userGmail=bundle.getString("gmail");
            String userDept=bundle.getString("cse");


            getSupportActionBar().setTitle(userDept);
            Tv_contactname.setText(userName);
            Tv_conactFirstnum.setText(userFirstnum);
            Tv_gmail.setText(userGmail);
            Tv_contDesg.setText(userDes);


            Img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ContextCompat.checkSelfPermission(Contact_details3.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(Contact_details3.this,new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_CALL);
                    }
                    else{

                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +userFirstnum));
                        Toast.makeText(Contact_details3.this, "First call", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
            });

            Img_msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", userFirstnum, null)));
                }
            });

            Img_gmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL,userGmail);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }

                }
            });


        }

        Bundle simple=getIntent().getExtras();///get the privious activity key
        final String amit=simple.getString("database");
        final  String img=bundle.getString("pic");

        Picasso.with(Contact_details3.this).load(img).networkPolicy(NetworkPolicy.OFFLINE).into(Circle_contact, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(Contact_details3.this)
                        .load(img)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(Circle_contact);


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
