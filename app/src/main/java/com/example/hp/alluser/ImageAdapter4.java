package com.example.hp.alluser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter4 extends RecyclerView.Adapter<ImageAdapter4.MyviewHolder> {
    private Context context;
    private List<Teacher> uploadList;
    private OnItemClickLisitiner lisitiner;



    public ImageAdapter4(Context context, List<Teacher> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialogue_contact, parent, false);

        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        final Teacher teacher = uploadList.get(position);
        holder.textView1.setText(teacher.getmName());
        holder.textView2.setText(teacher.getmPhoneNo());



        Picasso.with(context).load(teacher.getmImageUrl()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(context)
                        .load(teacher.getmImageUrl())
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(holder.imageView);


            }
        });


    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public  class MyviewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView textView1,textView2;
        ImageView imageView;

        public MyviewHolder(View itemView) {
            super(itemView);




            textView1= (TextView) itemView.findViewById(R.id.dialogue_contact_id);
            textView2= (TextView) itemView.findViewById(R.id.dialogue_phone_id);

            imageView = (ImageView) itemView.findViewById(R.id.dialoe_imag);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


        }



        @Override
        public void onClick(View v) {
            if (lisitiner != null) {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    lisitiner.OnitemClick(position);
                }

            }


        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Item:");
            MenuItem delete = menu.add(Menu.NONE, 1, 1, "Delete");
            MenuItem Update = menu.add(Menu.NONE, 2, 2, "Update");

            delete.setOnMenuItemClickListener(this);
            Update.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if (lisitiner != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    switch (item.getItemId()) {
                        case 1:
                            lisitiner.delete(position);
                            return true;
                        case 2:
                            lisitiner.Update(position);
                            return true;

                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickLisitiner {
        void OnitemClick(int position);

        void delete(int position);

        void Update(int position);


    }

    public void setOnItemClickLisitiner(ImageAdapter4.OnItemClickLisitiner lisitiner) {
        this.lisitiner = lisitiner;


    }


}