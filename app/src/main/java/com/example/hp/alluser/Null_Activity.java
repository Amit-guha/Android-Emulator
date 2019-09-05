package com.example.hp.alluser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Null_Activity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_null_);

        textView=findViewById(R.id.tvid);
        Bundle bundle=getIntent().getExtras();
       /* if(bundle!=null){
            String facultykey=bundle.getString("nul");
            Toast.makeText(this, ""+facultykey, Toast.LENGTH_SHORT).show();
            textView.setText(facultykey);
        }
*/
    }
}
