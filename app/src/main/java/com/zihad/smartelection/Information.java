package com.zihad.smartelection;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Information extends Fragment {
    ImageView toolbar;
    DrawerLayout drawerLayout;
    String[] item ={"12th National Parliament Election","11th National Parliament Election"};
    AutoCompleteTextView autoCompleteTextView;


    ArrayAdapter<String> adapterItems;

    LinearLayout infoabout;
    CardView noticboard,parties,glance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview =inflater.inflate(R.layout.fragment_information, container, false);

        toolbar = myview.findViewById(R.id.toolbar);
        infoabout = myview.findViewById(R.id.infoabout);
        noticboard = myview.findViewById(R.id.noticboard);
        parties = myview.findViewById(R.id.parties);
        glance = myview.findViewById(R.id.glance);



        drawerLayout = myview.findViewById(R.id.drawer_layout);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        //////////////////////////dropdown menu//////////////

autoCompleteTextView = myview.findViewById(R.id.votename);
adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_item,item);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                 //  Toast.makeText(getActivity(),"Item:"+item,Toast.LENGTH_SHORT).show();
            }
        });

///////////////////////////==========================finish ///////////////////////



        //================================Button onclick Lisaner====================

        infoabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        glance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Glance.class));


            }
        });

        parties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Parties.class));

            }
        });

        noticboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),Parties.class));

            }
        });



        return myview;
    }
}