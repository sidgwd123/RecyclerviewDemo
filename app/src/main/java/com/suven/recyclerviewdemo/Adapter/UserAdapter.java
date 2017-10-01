package com.suven.recyclerviewdemo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.suven.recyclerviewdemo.R;
import com.suven.recyclerviewdemo.Model.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<UserDetails> userList;
    Context context;
    int resId;



    public UserAdapter(Context context,int resId) {
        this.context = context;
        this.resId=resId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(resId, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvMobile, tvAge, tvPremium;
        public ImageView imgvProfilePic;
        public LinearLayout ll_row;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvMobile = (TextView) view.findViewById(R.id.tvMobile);
            tvAge = (TextView) view.findViewById(R.id.tvAge);
            tvPremium = (TextView) view.findViewById(R.id.tvPremium);
            imgvProfilePic = (ImageView) view.findViewById(R.id.imgvProfilePic);
            ll_row = (LinearLayout) view.findViewById(R.id.ll_row);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final UserDetails userDetails = userList.get(position);
        holder.tvName.setText(userDetails.getName());
        holder.tvMobile.setText(userDetails.getMobileNo());
        holder.tvAge.setText("Age: "+userDetails.getAge());
        if (userDetails.isPremiumUser()) {
            holder.tvPremium.setVisibility(View.VISIBLE);
        } else {
            holder.tvPremium.setVisibility(View.GONE);
        }

        if (!userDetails.getImageUrl().isEmpty() &&holder.imgvProfilePic!=null) {
            Picasso.with(context)
                    .load(userDetails.getImageUrl())

                    .error(R.drawable.no_image)
                    .placeholder(R.drawable.loading).centerCrop().resize(100, 100)
                    .into(holder.imgvProfilePic);
        } else {

            Picasso.with(context)
                    .load(R.drawable.no_image).centerCrop().resize(100, 100)
                    .into(holder.imgvProfilePic);

        }

        holder.ll_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(view, "Send message to " + userDetails.getMobileNo(), Snackbar.LENGTH_INDEFINITE)
                        .setAction("Send", new View.OnClickListener() {
                            @Override
                            public void onClick(final View view) {
                                Snackbar.make(view, "Sending Please wait..!", Snackbar.LENGTH_LONG).show();
                            }
                        });

                // Changing message text color
                snackbar.setActionTextColor(Color.GREEN);
                // Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(context.getResources().getColor(R.color.white));
                snackbar.show();
            }
        });

        //show animations here
        Animation animation = AnimationUtils.loadAnimation(context,
                R.anim.up_from_bottom);
        holder.itemView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setData(ArrayList<UserDetails> lstData) {
        this.userList = lstData;
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}