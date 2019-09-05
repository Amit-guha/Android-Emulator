package com.example.hp.alluser;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Contact_details extends AppCompatActivity {
    private TextView Tv_contactname,Tv_contDesg,Tv_contDept,Tv_conactFirstnum,Tv_gmail,Tv_contactSecond;
    private ImageView Img_contact,Img_call,Img_msg,Img_gmail,Img_callerSecond,Img_msgsecond;
    private CircleImageView Circle_contact;

    public static  final int REQUEST_CALL=1;
    private RecyclerView recyclerView;
    private List<Teacher> uploadList;
    DatabaseReference databaseReference;
    private ImageAdapter4 imageAdapter;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        //Adding Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Tv_contactname=findViewById(R.id.Tv_contactname);
        Tv_contDesg=findViewById(R.id.Tv_contDesg);
       // Tv_contDept=findViewById(R.id.Tv_contDept);
        Tv_conactFirstnum=findViewById(R.id.Tv_conactFirstnum);
        Tv_gmail=findViewById(R.id.Tv_gmail);
        Tv_contactSecond=findViewById(R.id.Tv_contactSecond);

        Circle_contact=findViewById(R.id.Circle_contact);
        Img_call=findViewById(R.id.Img_call);
        Img_msg=findViewById(R.id.Img_msg);
        Img_gmail=findViewById(R.id.Img_gmail);
        Img_callerSecond=findViewById(R.id.Img_callerSecond);
        Img_msgsecond=findViewById(R.id.Img_msgsecond);



        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            String userNmae=bundle.getString("name");
            String userGmail=bundle.getString("gmail");
            String userPhone=bundle.getString("key");
            String userDept=bundle.getString("cse");
            String userPhone2=bundle.getString("phone2");
            String userDes=bundle.getString("des");


            getSupportActionBar().setTitle(userDept);
            Tv_contactname.setText(userNmae);
            Tv_conactFirstnum.setText(userPhone);
            Tv_gmail.setText(userGmail);
            Tv_contactSecond.setText(userPhone2);
            Tv_contDesg.setText(userDes);
            //Tv_contDept.setText(userDept);


            Img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ContextCompat.checkSelfPermission(Contact_details.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(Contact_details.this,new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_CALL);
                    }
                    else{

                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +userPhone));
                        Toast.makeText(Contact_details.this, "First call", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
            });


            Img_callerSecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ContextCompat.checkSelfPermission(Contact_details.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(Contact_details.this,new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_CALL);
                    }
                    else{

                        Intent amit = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +userPhone2));
                        Toast.makeText(Contact_details.this, "Second Call", Toast.LENGTH_SHORT).show();
                        startActivity(amit);
                    }
                }
            });



            Img_msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", userPhone, null)));
                }
            });


            Img_msgsecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", userPhone2, null)));


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

        /*uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference().child(amit);
        databaseReference.keepSynced(true);*/

       Picasso.with(Contact_details.this).load(img).networkPolicy(NetworkPolicy.OFFLINE).into(Circle_contact, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(Contact_details.this)
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
