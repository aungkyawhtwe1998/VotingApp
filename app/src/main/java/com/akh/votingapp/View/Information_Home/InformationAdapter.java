package com.akh.votingapp.View.Information_Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.akh.votingapp.Model.Information;
import com.akh.votingapp.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    private ArrayList<Information> infoList;
    public Context mContext;

    public InformationAdapter(ArrayList<Information> infoList, Context context){
        this.infoList = infoList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.information_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_info_title.setText(infoList.get(position).getTitle());
        holder.txt_info_date_time.setText(infoList.get(position).getDateTime());
        holder.txt_info_dec.setText(infoList.get(position).getDescription());
        Glide.with(mContext).load(infoList.get(position).getImage()).into(holder.img_info);

    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_info;
        TextView txt_info_title,txt_info_dec, txt_info_date_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_info=itemView.findViewById(R.id.img_information);
            txt_info_dec=itemView.findViewById(R.id.txt_info_description);
            txt_info_title = itemView.findViewById(R.id.txt_info_title);
            txt_info_date_time = itemView.findViewById(R.id.txt_info_date_time);
        }
    }
}
