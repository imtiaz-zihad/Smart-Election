package com.zihad.smartelection;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;



public class Search extends Fragment {

    ImageView toolbar,image1,image2,image3;
    DrawerLayout drawerLayout;
    TextView nid,serial,center,address;
    public static String voterId,voterserial,Address,centerName,img1,img2,img3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview =inflater.inflate(R.layout.fragment_search, container, false);

        toolbar = myview.findViewById(R.id.toolbar);
        drawerLayout = myview.findViewById(R.id.accountlay);

        nid = myview.findViewById(R.id.nid);
        serial = myview.findViewById(R.id.serial);
        center = myview.findViewById(R.id.center);
        address = myview.findViewById(R.id.address);




        image1 = myview.findViewById(R.id.image1);
        image2 = myview.findViewById(R.id.image2);
        image3 = myview.findViewById(R.id.image3);



        nid.setText(voterId);
        serial.setText(voterserial);
        center.setText(centerName);
        address.setText(Address);





        Picasso.get().load(img1).placeholder(R.drawable.ecb).into(image1);
        Picasso.get().load(img2).placeholder(R.drawable.ecb).into(image2);
        Picasso.get().load(img3).placeholder(R.drawable.ecb).into(image3);




        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        return myview;
    }
}