package com.example.hp.alluser;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class All_Department extends AppCompatActivity {
    private ListView List_DepartmetnId;
    private DatabaseReference databaseReference;
    private List<Upload> uploadList;
    StorageReference firebaseStorage;
    private Custom_adapter customAdapter;
    ArrayList<Upload> arrayList = new ArrayList<Upload>();//create new Arraylist


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__department);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Department Name:");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        List_DepartmetnId = findViewById(R.id.List_DepartmetnId);


       /* SharedPreferences sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("Selectedkey")){
            String amit=sharedPreferences.getString("Selectedkey","data not found");
            databaseReference= FirebaseDatabase.getInstance().getReference().child(amit);
        }
*/

        uploadList = new ArrayList<>();


        firebaseStorage = FirebaseStorage.getInstance().getReference("AllDepartment");
        databaseReference = FirebaseDatabase.getInstance().getReference("AllDepartment");
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploadList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Upload upload = dataSnapshot1.getValue(Upload.class);
                    uploadList.add(upload);
                    customAdapter = new Custom_adapter(All_Department.this, R.layout.department, uploadList);
                    List_DepartmetnId.setAdapter(customAdapter);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);

        MenuItem menuItem = menu.findItem(R.id.Searchviewid);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               /* customAdapter.getFilter().filter(newText);
                return true;*/

                List<Upload> arrayListo = filterlist(uploadList, newText);
                customAdapter.setfilter(arrayListo);

                return true;


               /* if (TextUtils.isEmpty(newText)){
                    customAdapter.filter("");
                    List_DepartmetnId.clearTextFilter();
                }
                else {
                    customAdapter.filter(newText);
                    //customAdapter.getFilter().filter(newText);
                }
                return true;*/

            }
        });

        return super.onCreateOptionsMenu(menu);

    }


    private List<Upload> filterlist(List<Upload> myarray, String query) {
        query = query.toLowerCase();
        final List<Upload> arrayLists = new ArrayList<>();
        for (Upload model : myarray) {
            String userinput = model.getmName().toLowerCase();
            if (userinput.startsWith(query)) {
                arrayLists.add(model);


            }

        }

        return arrayLists;

    }
}
