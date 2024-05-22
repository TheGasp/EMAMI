package com.flamboox.emami_new_version;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GererListeProfil extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int HAUT_VIEW = 1;
    public static final int TEL_VIEW = 2;
    public static final int RESEAU_VIEW = 3;

    android.content.Context context;
    ArrayList<String[]> reseaux;
    Utilisateur user;

    public GererListeProfil(Context context, Utilisateur user){
        this.context=context;
        this.user=user;
        this.reseaux = user.getReseauxAffiche();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {       //gives the layout for each of our rows
        //This is where you inflate the layout (Giving a look to our rows)

        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == HAUT_VIEW) {
            view = inflater.inflate(R.layout.photoprofil_recyclerview, parent, false);
            return new ViewHolderProfilHaut(view);
        } else if (viewType == TEL_VIEW) {
            view = inflater.inflate(R.layout.numtelprofil_recyclerview, parent, false);
            return new ViewHolderProfilTel(view);
        } else if (viewType == RESEAU_VIEW) {
            view = inflater.inflate(R.layout.reseau_recyclerview, parent, false);
            return new ViewHolderProfilReseau(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //assigning values to the views we created in the recylcer_view_row layout file (base)
        //based ont he position of the recylcer view

        if (getItemViewType(position) == HAUT_VIEW) {
            ((ViewHolderProfilHaut)holder).hautProfil.setText(user.getPrenom() +" " + user.getNom());
            int drawableId = context.getResources().getIdentifier(user.getPhoto(), "drawable", context.getPackageName());
            ((ViewHolderProfilHaut)holder).photoProfil.setImageResource(drawableId);
        }if (getItemViewType(position) == TEL_VIEW) {
            ((ViewHolderProfilTel) holder).numTelProfil.setText(user.getNumTel());
        }if (getItemViewType(position) == RESEAU_VIEW) {
            ((ViewHolderProfilReseau)holder).nomReseauProfil.setText(reseaux.get(position-2)[1]);
            int drawableId = context.getResources().getIdentifier(reseaux.get(position - 2)[0], "drawable", context.getPackageName());
            ((ViewHolderProfilReseau)holder).imageViewProfil.setImageResource(drawableId);
        }
    }








    @Override
    public int  getItemViewType(int position) {  //changes the data based on the recycler view 's position for each of our items
        //assigning values to the views we created in the recylcer_view_row layout file (base)
        //based ont he position of the recylcer view
        if(position == 0){
            return HAUT_VIEW;
        }if(position == 1){
            return TEL_VIEW;
        }else {
            return RESEAU_VIEW;
        }

    }

    @Override
    public int getItemCount() {        //just wants to know how much data we have to display to the user
        //the recycler view just wants to know the number of items you want displayed
        return reseaux.size() + 2;
    }



    public static class ViewHolderProfilHaut extends RecyclerView.ViewHolder{
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method
        TextView hautProfil;
        ImageView photoProfil;
        public ViewHolderProfilHaut(@NonNull View itemView) {
            super(itemView);

            hautProfil = itemView.findViewById(R.id.textViewNomProfil);

            photoProfil = itemView.findViewById(R.id.imageProfil);
        }
    }

    public static class ViewHolderProfilTel extends RecyclerView.ViewHolder{
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method
        TextView numTelProfil;
        public ViewHolderProfilTel(@NonNull View itemView) {
            super(itemView);

            numTelProfil = itemView.findViewById(R.id.textViewNumTelProfil);
        }
    }


    public static class ViewHolderProfilReseau extends RecyclerView.ViewHolder{
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method

        TextView nomReseauProfil;
        ImageView imageViewProfil;
        public ViewHolderProfilReseau(@NonNull View itemView) {
            super(itemView);

            imageViewProfil = itemView.findViewById(R.id.logoReseauProfil);

            nomReseauProfil = itemView.findViewById(R.id.TextViewReseauProfil);


        }
    }






}


