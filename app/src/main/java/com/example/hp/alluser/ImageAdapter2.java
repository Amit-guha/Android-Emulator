package com.example.hp.alluser;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hp on 12-Apr-19.
 */

public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.MyviewHolder> {
    private Context context;
    private List<Teacher> uploadList;
    private OnItemClickLisitiner lisitiner;
    public static  final int REQUEST_CALL=1;

      Dialog mydialog;

    public ImageAdapter2(Context context, List<Teacher> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.contact_format_recycyclerview, parent, false);

        //Dialogue initialize
        //int a=myviewHolder.getAdapterPosition();
        mydialog=new Dialog(context);
        mydialog.setContentView(R.layout.dialogue_contact);

        MyviewHolder myviewHolder=new MyviewHolder(v);

         myviewHolder.item_contact.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Toast.makeText(context, "Click "+String .valueOf(myviewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

                   /* TextView dialoge_name_tv=mydialog.findViewById(R.id.dialogue_contact_id);
                    TextView dialoge_phne_tv=mydialog.findViewById(R.id.dialogue_phone_id);
                    ImageView dialoge_img=mydialog.findViewById(R.id.dialoe_imag);
                    Button dialoge_call_id=mydialog.findViewById(R.id.dialogue_call_id);
                    Button dialogue_msg_id=mydialog.findViewById(R.id.dialogue_msg_id);


                   Teacher teacher=uploadList.get(viewType);//i think problm here
                   dialoge_name_tv.setText(teacher.getmName());
                   dialoge_phne_tv.setText(teacher.getmPhoneNo());

                   Toast.makeText(context, ""+dialoge_name_tv, Toast.LENGTH_SHORT).show();
                  // String text=teacher.getmPhoneNo().toString();
                   Picasso.with(context).load(teacher.getmImageUrl()).networkPolicy(NetworkPolicy.OFFLINE).into(dialoge_img, new Callback() {
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
                                   .into(dialoge_img);


                       }
                   });

                   dialoge_call_id.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           String text=teacher.getmPhoneNo();
                           Toast.makeText(context, ""+text, Toast.LENGTH_SHORT).show();

                           Intent callIntent = new Intent(Intent.ACTION_CALL);
                           callIntent.setData(Uri.parse("tel:"+text));

                           if (ActivityCompat.checkSelfPermission(context,
                                   Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                               return;

                           context.startActivity(callIntent);

                           if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                               ActivityCompat.requestPermissions((Activity) context,new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_CALL);
                           }
                           else{

                               Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +text));
                               context.startActivity(intent);

                           }

                           Toast.makeText(context, "call"+text, Toast.LENGTH_SHORT).show();
                       }
                   });*/

                  /* dialogue_msg_id.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String text=teacher.getmPhoneNo().toString();

                           Log.i("Send SMS", "");
                           Intent smsIntent = new Intent(Intent.ACTION_VIEW);

                           smsIntent.setData(Uri.parse("smsto:"));
                           smsIntent.setType("vnd.android-dir/mms-sms");
                           smsIntent.putExtra("address"  , new String (text));
                           smsIntent.putExtra("sms_body"  , "Test ");

                           try {
                               context.startActivity(smsIntent);
                               //finish();
                               Log.i("Finished sending SMS...", "");
                           } catch (android.content.ActivityNotFoundException ex) {
                               Toast.makeText(context,
                                       "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
                           }

                       }
                   });

*/
                  // dialoge_img.setImageResource(Integer.parseInt(teacher.getmImageUrl()));
               //    dialoge_name_tv.setText(uploadList.get(myviewHolder.getAdapterPosition()).getmName());
                  // dialoge_phne_tv.setText(uploadList.get(myviewHolder.getAdapterPosition()).getmPhoneNo());


                 // mydialog.show();
                   //Toast.makeText(context, "Test click "+((myviewHolder.item_contact.getId())), Toast.LENGTH_SHORT).show();
               }
           });

        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
       final Teacher teacher = uploadList.get(position);
        holder.textView1.setText(teacher.getmName());
      //  holder.textView2.setText(teacher.getmPhoneNo());
        holder.textView3.setText(teacher.getmDesignation());



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

      /*  Picasso.with(context)
                .load(teacher.getmImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);*/

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public  class MyviewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView textView1,textView2,textView3;
        CircleImageView imageView;

        private LinearLayout item_contact;


        public MyviewHolder(View itemView) {
            super(itemView);

                 item_contact=itemView.findViewById(R.id.contact_format);


            textView1= (TextView) itemView.findViewById(R.id.textRecycler);
            //textView2= (TextView) itemView.findViewById(R.id.textRecycler2);
            textView3=itemView.findViewById(R.id.textRecycler3);
            imageView = (CircleImageView) itemView.findViewById(R.id.ImageCard);
            //itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            item_contact.setOnClickListener(this);


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

    public void setOnItemClickLisitiner(ImageAdapter2.OnItemClickLisitiner lisitiner) {
        this.lisitiner = lisitiner;


    }


}
