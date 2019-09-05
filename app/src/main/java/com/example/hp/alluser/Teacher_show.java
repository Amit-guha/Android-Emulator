package com.example.hp.alluser;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Teacher_show extends AppCompatActivity {
    public static final int REQUEST_CALL = 1;
    private RecyclerView recyclerView;
    private List<Teacher> uploadList;
    DatabaseReference databaseReference;
    private ImageAdapter2 imageAdapter;
    private FirebaseStorage firebaseStorage;
    private Button callbtn;
    Dialog mydialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle dep = getIntent().getExtras();
        String cse = dep.getString("word");

        getSupportActionBar().setTitle(cse);
        setContentView(R.layout.activity_teacher_show);

        //Adding Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        callbtn = findViewById(R.id.dialogue_call_id);
        recyclerView = (RecyclerView) findViewById(R.id.Recyclerview_showContact);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();///get the privious activity key
        final String amit = bundle.getString("Selectedkey");

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);


        uploadList = new ArrayList<>();
        firebaseStorage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(amit);
        databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploadList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    //String commentkey=dataSnapshot1.getKey();
                    Teacher teacher = dataSnapshot1.getValue(Teacher.class);
                    teacher.setKey(dataSnapshot1.getKey());//using datasnapshot1 to get the real key
                    uploadList.add(teacher);

                }
                imageAdapter = new ImageAdapter2(Teacher_show.this, uploadList);
                recyclerView.setAdapter(imageAdapter);


                imageAdapter.setOnItemClickLisitiner(new ImageAdapter2.OnItemClickLisitiner() {
                    @Override
                    public void OnitemClick(int position) {
                        Teacher SelectedItem = uploadList.get(position);//to get the selecteditem position
                        final String text = SelectedItem.getKey();//to get the selecteditem key


                        String name = SelectedItem.getmName();
                        String gmail = SelectedItem.getmEmail();
                        String imgurl = SelectedItem.getmImageUrl();
                        String phone2 = SelectedItem.getmPhoneNo2();
                        String des = SelectedItem.getmDesignation();
                        //   Toast.makeText(Teacher_show.this, text+"  "+name+" ", Toast.LENGTH_SHORT).show();

                        /*  Bitmap bmp = (Bitmap) BitmapFactory.decodeResource(imgurl,R.drawable.amit);*/
                        /* //    Bitmap bmp = BitmapFactory.decodeFile(path); You can use this also.*/
                        /*     ByteArrayOutputStream stream = new ByteArrayOutputStream();*/
                        /*     bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);*/
                        /*     byte[] byteArray = stream.toByteArray();*/

                        if (((text != null && !text.isEmpty() && !text.equals("null")) && (phone2 != null && !phone2.isEmpty() &&
                                !phone2.equals("null")) && (gmail != null && !gmail.isEmpty() && !gmail.equals("null")))) {

                            Intent intent = new Intent(Teacher_show.this, Contact_details.class);
                            intent.putExtra("database", amit);
                            intent.putExtra("name", name);
                            intent.putExtra("gmail", gmail);
                            intent.putExtra("key", text);
                            intent.putExtra("pic", imgurl);
                            intent.putExtra("cse", cse);
                            intent.putExtra("phone2", phone2);
                            intent.putExtra("des", des);

                            startActivity(intent);

                        } else if ((text != null && !text.isEmpty() && !text.equals("null")) && (gmail != null && !gmail.isEmpty() && !gmail.equals("null"))

                        ) {

                            Intent details3 = new Intent(Teacher_show.this, Contact_details3.class);
                            details3.putExtra("database", amit);
                            details3.putExtra("name", name);
                            details3.putExtra("key", text);
                            details3.putExtra("pic", imgurl);
                            details3.putExtra("des", des);
                            details3.putExtra("cse", cse);
                            details3.putExtra("gmail", gmail);

                            //  Toast.makeText(Teacher_show.this, "Third Activity", Toast.LENGTH_SHORT).show();
                            startActivity(details3);

                        } else if ((text != null && !text.isEmpty() && !text.equals("null")) && (phone2 != null && !phone2.isEmpty() && !phone2.equals("null"))

                        ) {

                            Intent details4 = new Intent(Teacher_show.this, Contact_details4.class);
                            details4.putExtra("database", amit);
                            details4.putExtra("name", name);
                            details4.putExtra("key", text);
                            details4.putExtra("pic", imgurl);
                            details4.putExtra("des", des);
                            details4.putExtra("cse", cse);
                            details4.putExtra("phone2", phone2);

                            // Toast.makeText(Teacher_show.this, "Fourth Activity", Toast.LENGTH_SHORT).show();
                            startActivity(details4);

                        } else {
                            Intent details2 = new Intent(Teacher_show.this, Contact_details2.class);
                            details2.putExtra("database", amit);
                            details2.putExtra("name", name);
                            details2.putExtra("key", text);
                            details2.putExtra("pic", imgurl);
                            details2.putExtra("des", des);
                            details2.putExtra("cse", cse);

                            //  Toast.makeText(Teacher_show.this, "Details2", Toast.LENGTH_SHORT).show();
                            startActivity(details2);


                        }



                        /* Intent intent=new Intent(Department_Show.this,TeacherPanel.class);*/
                        /*                        Intent intent=new Intent(Department_Show.this,NewTeacherAddPanel.class);*/
                        /*                        intent.putExtra("Selectedkey",text);*/
                        /*                        startActivity(intent);*/



                 /* if(ContextCompat.checkSelfPermission(Teacher_show.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                            ActivityCompat.requestPermissions(Teacher_show.this,new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_CALL);
                        }
                        else{

                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +text));
                            startActivity(intent);
                        }    */


                    }

                    @Override
                    public void delete(int position) {
                        Teacher selectedItem = uploadList.get(position);
                        final String delete = selectedItem.getKey();
                        StorageReference storageReference = firebaseStorage.getReferenceFromUrl(selectedItem.getmImageUrl());
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                databaseReference.child(delete).removeValue();
                                Toast.makeText(Teacher_show.this, "Item Deleted", Toast.LENGTH_SHORT).show();

                            }
                        });


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


    }

    //Onstart Method


   /* @Override
    protected void onStart() {
        super.onStart();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Teacher teacher=dataSnapshot1.getValue(Teacher.class);
                    uploadList.add(teacher);
                }
                imageAdapter = new ImageAdapter2(Teacher_show.this, uploadList);
                recyclerView.setAdapter(imageAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        };
    }
*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
