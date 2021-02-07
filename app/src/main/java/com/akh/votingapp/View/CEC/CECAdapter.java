package com.akh.votingapp.View.CEC;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CECAdapter extends RecyclerView.Adapter<CECAdapter.ViewHolder> {
    private ArrayList<CEC> cecList;
    public Context mContext;

    public CECAdapter(ArrayList<CEC> cecList, Context context){
        this.cecList = cecList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cec_items,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(cecList.get(position));
        holder.txt_name.setText(cecList.get(position).getName());
        holder.txt_department.setText(cecList.get(position).getDepartment());
        holder.txt_position.setText(cecList.get(position).getPosition());
        Glide.with(mContext).load(cecList.get(position).getImgUrl()).into(holder.img);
        holder.cec_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO adapter onclick
            }
        });
    }

    @Override
    public int getItemCount() {
        return cecList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cec_card;
        TextView txt_name,txt_department,txt_position;
        ImageView img;
        Button btn_vote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cec_item);
            txt_name = itemView.findViewById(R.id.txt_item_cec_name);
            txt_department = itemView.findViewById(R.id.txt_item_cec_dept);
            txt_position = itemView.findViewById(R.id.txt_item_cec_position);
            btn_vote = itemView.findViewById(R.id.btn_vote);
            cec_card = itemView.findViewById(R.id.cec_card_view);
        }
    }
}
