package com.example.hp.alluser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Custom_adapter extends BaseAdapter {
    private Context context;
    private List<Upload>uploadList;
    private  int resource;
    ArrayList<Upload> arrayList;//it;s a new object

    public void setfilter(List<Upload> arrayLists){
        uploadList = new ArrayList<>();
        uploadList.addAll(arrayLists);
        notifyDataSetChanged();
    }



    public Custom_adapter(Context context,int resource, List<Upload> uploadList) {
        //super(context,resource,uploadList);
        this.context = context;
        this.resource=resource;
        this.uploadList = uploadList;
        this.arrayList=new ArrayList<Upload>();//its new
        this.arrayList.addAll(uploadList);//add all the list
    }


    @Override
    public int getCount() {
        return uploadList.size();
    }

    @Override
    public Object getItem(int position) {
        return uploadList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v= layoutInflater.inflate(R.layout.department,parent,false);
        TextView tvcse = v.findViewById(R.id.tvcse);
        ImageView imgcse = v.findViewById(R.id.Imgcse);
        tvcse.setText(uploadList.get(position).getmName().toString());
        Picasso.with(context).load(uploadList.get(position).getmImageUrl()).networkPolicy(NetworkPolicy.OFFLINE).into(imgcse, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(context)
                        .load(uploadList.get(position).getmImageUrl())
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(imgcse);

            }
        });


        return v;
    }

    //Filter Method
  /*  public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        uploadList.clear();
        if (charText.length()==0){
            uploadList.addAll(arrayList);
        }
        else {
            for (Upload upload : arrayList){
                if (upload.getmName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    uploadList.add(upload);
                }
            }
        }
        notifyDataSetChanged();
    }*/



}