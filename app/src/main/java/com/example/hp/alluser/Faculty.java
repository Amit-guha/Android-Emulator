package com.example.hp.alluser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Upload>uploadList;
    DatabaseReference databaseReference;
    private  ImageAdapter imageAdapter;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        getSupportActionBar().setTitle("Faculty");
        //getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Adding Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView= (RecyclerView) findViewById(R.id.Recyclerview_Show);
        recyclerView.setHasFixedSize(true);
        //int numberOfcolms=2;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfcolms));

        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.keepSynced(true);

       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploadList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    upload.setKey(dataSnapshot1.getKey());//using datasnapshot1 to get the real key
                   final String amit=dataSnapshot1.getKey();
                    uploadList.add(upload);
                    //  Toast.makeText(Image_show.this, ""+amit, Toast.LENGTH_SHORT).show();
                }
                imageAdapter=new ImageAdapter(Faculty.this,uploadList);
                recyclerView.setAdapter(imageAdapter);

                imageAdapter.setOnItemClickLisitiner(new ImageAdapter.OnItemClickLisitiner() {
                    @Override
                    public void OnitemClick(int position) {
                        // String text=uploadList.get(position).getmName().toString();
                        Upload SelectedItem=uploadList.get(position);//to get the selecteditem position
                        String text=SelectedItem.getKey();//to get the selecteditem key
                        String Facultyname=SelectedItem.getmName();
                       // Toast.makeText(Faculty.this, text+"  ", Toast.LENGTH_SHORT).show();


                       /*Intent dept=new Intent(Faculty.this,All_Department.class);
                        dept.putExtra("name",text);
                        Toast.makeText(Faculty.this, "All_department"+text, Toast.LENGTH_SHORT).show();
                        startActivity(dept);*/

                        Intent intent=new Intent(Faculty.this,Deparment_Show.class);
                        intent.putExtra("Selectedkey",text);
                        intent.putExtra("Faculty",Facultyname);
                        startActivity(intent);

                  /*  SharedPreferences sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("Selectedkey",text);
                        editor.commit();
*/

                    }

                    @Override
                    public void delete(int position) {

                    }
                    @Override
                    public void Update(int position) {
                      //  Toast.makeText(Image_show.this, "Update", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//If you want to retrieve only the data on each new post added to your blogging app, you could use child_added:

      /*  databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded( DataSnapshot dataSnapshot,  String s) {

                if(dataSnapshot.getValue()!=null){
                   // Map<Upload,Upload> map= (Map<Upload, Upload>) dataSnapshot.getValue();
                   Upload newPost = dataSnapshot.getValue(Upload.class);
                   newPost.setKey(dataSnapshot.getKey());
                   uploadList.add(newPost);
                   // String data=map.get(dataSnapshot.getKey()).toString();
                    //uploadList.add(data);
                    imageAdapter=new ImageAdapter(Faculty.this,uploadList);
                    recyclerView.setAdapter(imageAdapter);

                }

                //Adding onitemclicklisitiner
                imageAdapter.setOnItemClickLisitiner(new ImageAdapter.OnItemClickLisitiner() {
                    @Override
                    public void OnitemClick(int position) {

                        Upload SelectedItem=uploadList.get(position);//to get the selecteditem position
                        String text=SelectedItem.getKey();//to get the selecteditem key
                        String Facultyname=SelectedItem.getmName();



                        Intent intent=new Intent(Faculty.this,Deparment_Show.class);
                        intent.putExtra("Selectedkey",text);
                        intent.putExtra("Faculty",Facultyname);
                        startActivity(intent);

                    }

                    @Override
                    public void delete(int position) {

                    }

                    @Override
                    public void Update(int position) {

                    }
                });

                //finishing onitemclick lisitiner


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
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
