package com.baez.places;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private List<PlacesModel.Results> stLstStores;
    private List<StoreModel> models;
    private PlacesInterface listener;


    public RecyclerViewAdapter(List<PlacesModel.Results> stores,PlacesInterface listener) {
        this.listener = listener;
        stLstStores = stores;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.setData(stLstStores.get(holder.getAdapterPosition()), holder);
    }


    @Override
    public int getItemCount() {
        return  stLstStores.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView txtStoreName;
        TextView txtStoreAddr;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtStoreName = itemView.findViewById(R.id.txtStoreName);
            this.txtStoreAddr = itemView.findViewById(R.id.txtStoreAddr);
        }


        public void setData(final PlacesModel.Results info, MyViewHolder holder) {
            holder.txtStoreName.setText(info.name);
            holder.txtStoreAddr.setText(info.vicinity);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(info.place_id);
                }
            });
        }

    }

    public interface PlacesInterface{
        void onItemClicked(String placeID);
    }
}


