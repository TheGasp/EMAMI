package com.flamboox.emami_new_version;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GererListeParam extends RecyclerView.Adapter<GererListeParam.MyViewHolder>{

    android.content.Context context;
    String[]reseaux;

    public GererListeParam(android.content.Context context, String [] reseaux){
        this.context=context;
        this.reseaux = reseaux;
    }

    @NonNull
    @Override
    public GererListeParam.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {       //gives the layout for each of our rows
        //This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.base_recyclerview, parent, attachToRoot: false);

        return new GererListeParam.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GererListeParam.MyViewHolder holder, int position) {  //changes the data based on the recycler view 's position for each of our items
        //assigning values to the views we created in the recylcer_view_row layout file (base)
        //based ont he position of the recylcer view

        holder.nomReseau.setText(reseaux.get(position).getreseauname());

    }

    @Override
    public int getItemCount() {        //just wants to know how much data we have to display to the user
        //the recycler view just wants to know the number of items you want displayed
        return reseaux.length;
    }





    public static class MyViewHolder extends RecyclerView.ViewHolder{       //pretty much like the oncreate
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method

        android.widget.Button nomReseau;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomReseau = itemView.findViewById(R.id.button);



        }
    }
}

