package com.akh.votingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.Model.CECRepo;
import com.akh.votingapp.R;

import java.util.ArrayList;

public class CECAdapter extends RecyclerView.Adapter<CECAdapter.ViewHolder> {
    private ArrayList<CEC> cecList;
    public CECAdapter(ArrayList<CEC> cecList){
        this.cecList = cecList;
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

    }

    @Override
    public int getItemCount() {
        return cecList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name,txt_department,txt_position;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cec_item);
            txt_name = itemView.findViewById(R.id.txt_item_cec_name);
            txt_department = itemView.findViewById(R.id.txt_item_cec_dept);
            txt_position = itemView.findViewById(R.id.txt_item_cec_position);


        }
    }
}
