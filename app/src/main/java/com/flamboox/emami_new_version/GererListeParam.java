package com.flamboox.emami_new_version;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class GererListeParam extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int HAUT_VIEW = 1;
    public static final int ADD_VIEW = 2;
    public static final int TEL_VIEW = 3;
    public static final int RESEAU_VIEW = 4;

    android.content.Context context;
    ArrayList<String[]> reseaux;
    Utilisateur user;

    public GererListeParam(Context context, Utilisateur user){
        this.context=context;
        this.user=user;
        this.reseaux = user.getReseauxTot();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {       //gives the layout for each of our rows
        //This is where you inflate the layout (Giving a look to our rows)

        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == HAUT_VIEW) {
            view = inflater.inflate(R.layout.haut_recyclerview, parent, false);
            return new ViewHolderHaut(view, context);
        } else if (viewType == TEL_VIEW) {
            view = inflater.inflate(R.layout.haut2_recyclerview, parent, false);
            return new ViewHolderTel(view, context);
        } else if (viewType == RESEAU_VIEW) {
            view = inflater.inflate(R.layout.base_recyclerview, parent, false);
            return new ViewHolderReseau(view, context);
        } else if (viewType == ADD_VIEW) {
            view = inflater.inflate(R.layout.bas_recyclerview, parent, false);
            return new ViewHolderAdd(view, context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //assigning values to the views we created in the recylcer_view_row layout file (base)
        //based ont he position of the recylcer view

        if (getItemViewType(position) == HAUT_VIEW) {
            ((ViewHolderHaut)holder).haut.setText(user.getPrenom() +" " + user.getNom());
            int drawableId = context.getResources().getIdentifier(user.getPhoto(), "drawable", context.getPackageName());
            ((ViewHolderHaut)holder).photo.setImageResource(drawableId);
        }if (getItemViewType(position) == TEL_VIEW) {
            ((ViewHolderTel) holder).numTel.setText(user.getNumTel());

            ((ViewHolderTel)holder).checkBox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((ViewHolderTel)holder).checkBox2.isChecked()){
                        user.NePasAfficherTel();
                    }else{
                        user.AfficherTel();
                    }
                }
            });

        }if (getItemViewType(position) == RESEAU_VIEW) {
            ((ViewHolderReseau)holder).nomReseau.setText(reseaux.get(position-2)[1]);
            int drawableId = context.getResources().getIdentifier(reseaux.get(position - 2)[0], "drawable", context.getPackageName());
            ((ViewHolderReseau)holder).imageView.setImageResource(drawableId);

            ((ViewHolderReseau)holder).checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((ViewHolderReseau)holder).checkBox.isChecked()){
                        user.NePasAfficherReseau(reseaux.get(position - 2)[0]);
                    }else{
                        user.AfficherReseau(reseaux.get(position - 2)[0]);
                    }
                }
            });

        }if (getItemViewType(position) == ADD_VIEW) {
            ((ViewHolderAdd) holder).bas.setText("Ajouter un réseau");
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
        }if(position == reseaux.size()+2){
            return ADD_VIEW;
        }else {
            return RESEAU_VIEW;
        }

    }

    @Override
    public int getItemCount() {        //just wants to know how much data we have to display to the user
        //the recycler view just wants to know the number of items you want displayed
        return reseaux.size() + 3;
    }



    public static class ViewHolderHaut extends RecyclerView.ViewHolder{
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method
        android.widget.Button haut;
        ImageButton photo;
        public ViewHolderHaut(@NonNull View itemView, android.content.Context context) {
            super(itemView);

            haut = itemView.findViewById(R.id.textViewNom);
            haut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NomPrenomActivity.class);
                    context.startActivity(intent);
                }
            });

            photo = itemView.findViewById(R.id.imageButtonProfil);
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PhotoActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    public static class ViewHolderTel extends RecyclerView.ViewHolder{
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method
        android.widget.Button numTel;
        CheckBox checkBox2;
        public ViewHolderTel(@NonNull View itemView, android.content.Context context) {
            super(itemView);

            numTel = itemView.findViewById(R.id.textViewNumTel);
            numTel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NumTelActivity.class);
                    context.startActivity(intent);
                }
            });

            checkBox2 = itemView.findViewById(R.id.checkBox);
        }
    }


    public static class ViewHolderReseau extends RecyclerView.ViewHolder{
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method

        android.widget.Button nomReseau;
        ImageView imageView;
        CheckBox checkBox;
        public ViewHolderReseau(@NonNull View itemView, android.content.Context context) {
            super(itemView);

            imageView = itemView.findViewById(R.id.logoReseau);

            nomReseau = itemView.findViewById(R.id.button);
            nomReseau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ResActivity.class);
                    context.startActivity(intent);
                }
            });

            checkBox = itemView.findViewById(R.id.checkBox1);


        }
    }


    public static class ViewHolderAdd extends RecyclerView.ViewHolder{
        //grabbing the views form our recycler_view_rom layout file
        //kinda like in the onCreate method
        android.widget.Button bas;
        public ViewHolderAdd(@NonNull View itemView, android.content.Context context) {
            super(itemView);

            bas = itemView.findViewById(R.id.button1);
            bas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }




}

