package com.example.pg;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class adapter extends RecyclerView.Adapter <adapter.mViewHolder>{

    private ArrayList<DataModule> dataModuleList;
    Context context;

    public adapter(Context cont,ArrayList<DataModule> dataModuleList) {
        this.dataModuleList = dataModuleList;
        this.context = cont;
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {
        TextView name_textview ,phone_text_view;
        ImageView imageView;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textview =itemView.findViewById(R.id.textView1);
            phone_text_view =itemView.findViewById(R.id.textView2);
            imageView =itemView.findViewById(R.id.image_view_show);

        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull final  ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item,viewGroup,false);
        mViewHolder s =new mViewHolder(view);
        return s;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder mViewHolder, int i) {
        DataModule module = dataModuleList.get(i);
        mViewHolder.name_textview.setText(dataModuleList.get(i).getName());
        mViewHolder.phone_text_view.setText(dataModuleList.get(i).getPhone());



        //   Picasso.with(context).load(dataModuleList.get(i).getUri()).fit().centerCrop().into(mViewHolder.imageView);
        Picasso.with(context)
                .load(module.getUri())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(mViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataModuleList.size();
    }


}

