package com.example.hp.alluser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Deparment_Show extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Upload> uploadList;
    DatabaseReference databaseReference;
    private  ImageAdapter3 imageAdapter;
    private FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle Eng=getIntent().getExtras();
        String value=Eng.getString("Faculty");

       getSupportActionBar().setTitle(value);

       //Adding Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_deparment__show);
        int numberOfcolms=2;

        recyclerView= (RecyclerView) findViewById(R.id.Recyclerview_DepShow);
        //recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfcolms));

        Bundle bundle=getIntent().getExtras();///get the privious activity key
        String amit=bundle.getString("Selectedkey");



/*

        Intent nul=new Intent(Deparment_Show.this,Null_Activity.class);
        nul.putExtra("nul",amit);
        Toast.makeText(this, "nul="+amit, Toast.LENGTH_SHORT).show();
        startActivity(nul);

*/





        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference=  FirebaseDatabase.getInstance().getReference().child(amit);
        databaseReference.keepSynced(true);

       // Query searchQueryNum2 = databaseReference.child(amit);//unknown

        //AddValue Event Lisitiner
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploadList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    //String commentkey=dataSnapshot1.getKey();
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    upload.setKey(dataSnapshot1.getKey());//using datasnapshot1 to get the real key

                    uploadList.add(upload);
                }
                imageAdapter=new ImageAdapter3(Deparment_Show.this,uploadList);
                recyclerView.setAdapter(imageAdapter);

                imageAdapter.setOnItemClickLisitiner(new ImageAdapter3.OnItemClickLisitiner() {
                    @Override
                    public void OnitemClick(int position) {
                        Upload SelectedItem=uploadList.get(position);//to get the selecteditem position
                        String text=SelectedItem.getKey();//to get the selecteditem key
                        String word=SelectedItem.getmName();
                        Toast.makeText(Deparment_Show.this, text+"  ", Toast.LENGTH_SHORT).show();

                       Intent intent=new Intent(Deparment_Show.this,Teacher_show.class);
                        intent.putExtra("Selectedkey",text);
                        intent.putExtra("word",word);
                        startActivity(intent);


                        //All dept to pass the key
                       /* Intent dept=new Intent(Deparment_Show.this,All_Department.class);
                        dept.putExtra("Selectedkey",text);
                        startActivity(dept);*/
                    }

                    @Override
                    public void delete(int position) {

                    }

                    @Override
                    public void Update(int position) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //Adding ChildEvent Lisitiner

        /*databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded( DataSnapshot dataSnapshot,  String s) {

                if(dataSnapshot.getValue()!=null){
                    // Map<Upload,Upload> map= (Map<Upload, Upload>) dataSnapshot.getValue();
                    uploadList.clear();
                    Upload newPost = dataSnapshot.getValue(Upload.class);
                    newPost.setKey(dataSnapshot.getKey());
                    uploadList.add(newPost);
                    // String data=map.get(dataSnapshot.getKey()).toString();
                    //uploadList.add(data);
                    imageAdapter=new ImageAdapter3(Deparment_Show.this,uploadList);
                    recyclerView.setAdapter(imageAdapter);

                }

                //Adding onitemclicklisitiner
                imageAdapter.setOnItemClickLisitiner(new ImageAdapter3.OnItemClickLisitiner() {
                    @Override
                    public void OnitemClick(int position) {
                        Upload SelectedItem=uploadList.get(position);//to get the selecteditem position
                        String text=SelectedItem.getKey();//to get the selecteditem key
                        String word=SelectedItem.getmName();
                        Toast.makeText(Deparment_Show.this, text+"  ", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(Deparment_Show.this,Teacher_show.class);
                        intent.putExtra("Selectedkey",text);
                        intent.putExtra("word",word);
                        startActivity(intent);


                        //All dept to pass the key
                       *//* Intent dept=new Intent(Deparment_Show.this,All_Department.class);
                        dept.putExtra("Selectedkey",text);
                        startActivity(dept);*//*
                    }

                    @Override
                    public void delete(int position) {

                    }

                    @Override
                    public void Update(int position) {

                    }
                //finishing onitemclick lisitiner
                });

            }



            @Override
            public void onChildChanged( DataSnapshot dataSnapshot,  String s) {

            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });

*/

    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    upload.setKey(dataSnapshot1.getKey());
                    uploadList.add(upload);
                }

                imageAdapter=new ImageAdapter3(Deparment_Show.this,uploadList);
                recyclerView.setAdapter(imageAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
