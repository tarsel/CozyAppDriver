package com.fandataxidriver.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fandataxidriver.R;
import com.fandataxidriver.data.network.model.user_request_delivery_detail;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
   ArrayList<user_request_delivery_detail> arrayList;
    private ClickListener clickListener;

    public ProductAdapter(Context context, ArrayList<user_request_delivery_detail> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    public void setClickListener(ProductAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_product, parent, false);
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
                holder.status.setText(promoList.getStatus()+"");


        }
       holder.txt_phoneNumber.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              try {
                  Intent intent = new Intent(Intent.ACTION_DIAL);
                  intent.setData(Uri.parse("tel:" + promoList.getReceiverMobile()));
                  context.startActivity(intent);
              }catch (Exception e){
                  e.printStackTrace();
              }
           }
       });

    }




    public interface ClickListener {
        void updateStatus(String deliveryStatus ,int  adapterPosition , String deliveryId);

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
        private TextView txt_delivery_instruction;
        Button status;

        MyViewHolder(View view) {
            super(view);

            txt_productName = view.findViewById(R.id.product_name_txt);
            txt_personName = view.findViewById(R.id.reciver_name_txt);
            txt_phoneNumber = view.findViewById(R.id.reciver_number_txt);
            txt_address = view.findViewById(R.id.delivery_adr_txt);
            txt_delivery_instruction = view.findViewById(R.id.instruction_txt);
            status=view.findViewById(R.id.btn_status);
            status.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.updateStatus(arrayList.get(getAdapterPosition()).getStatus(), getAdapterPosition(), arrayList.get(getAdapterPosition()).getId() + "");
                }
            });

        }
    }
}
