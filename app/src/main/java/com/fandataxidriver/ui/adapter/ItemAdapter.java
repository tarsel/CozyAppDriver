package com.fandataxidriver.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fandataxidriver.R;
import com.fandataxidriver.data.network.model.user_request_delivery_detail;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private Context context;
   ArrayList<user_request_delivery_detail> arrayList;

    public ItemAdapter(Context context, ArrayList<user_request_delivery_detail> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       user_request_delivery_detail  promoList = arrayList.get(position);
        if (promoList != null) {
              holder.txt_productName.setText(promoList.getItemToDeliver()+"");
                holder.txt_phoneNumber.setText(promoList.getReceiverMobile()+"");
                holder.txt_address.setText(promoList.getDeliveryAddress()+"");
                holder.txt_personName.setText(promoList.getReceiverName()+"");
                holder.txt_delivery_instruction.setText(promoList.getAnyInstructions()+"");
                holder.txt_counter.setText(position+1+"");

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_productName;
        private TextView txt_personName;
        private TextView txt_address;
        private TextView txt_phoneNumber;
        private TextView txt_counter;
        private TextView txt_delivery_instruction;

        MyViewHolder(View view) {
            super(view);

            txt_productName = view.findViewById(R.id.product_name_txt);
            txt_counter = view.findViewById(R.id.tv_count);
            txt_personName = view.findViewById(R.id.reciver_name_txt);
            txt_phoneNumber = view.findViewById(R.id.reciver_number_txt);
            txt_address = view.findViewById(R.id.delivery_adr_txt);
            txt_delivery_instruction = view.findViewById(R.id.instruction_txt);

        }
    }
}
