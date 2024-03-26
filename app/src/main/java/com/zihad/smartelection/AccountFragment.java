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



public class AccountFragment extends Fragment {

    ImageView toolbar,image1,image2,image3,marka1,marka2,marka3;
    DrawerLayout drawerLayout;
    TextView nid,serial,center,address,candidate1,candidate2,candidate3,markaname1,markaname2,markaname3,ason;
    public static String voterId,voterserial,Address,centerName,img1,img2,img3,MARKA1,MARKA2,MARKA3,
            CAN1,CAN2,CAN3,MARN1,MARN2,MARN3,ASON;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview =inflater.inflate(R.layout.fragment_account, container, false);

        toolbar = myview.findViewById(R.id.toolbar);
        drawerLayout = myview.findViewById(R.id.accountlay);

        nid = myview.findViewById(R.id.nid);
        serial = myview.findViewById(R.id.serial);
        center = myview.findViewById(R.id.center);
        address = myview.findViewById(R.id.address);
        ason = myview.findViewById(R.id.ason);
        marka1 = myview.findViewById(R.id.marka1);
        marka2 = myview.findViewById(R.id.marka2);
        marka3 = myview.findViewById(R.id.marka3);

        candidate1 = myview.findViewById(R.id.candidate1);
        candidate2 = myview.findViewById(R.id.candidate2);
        candidate3 = myview.findViewById(R.id.candidate3);

        markaname1 = myview.findViewById(R.id.markaname1);
        markaname2 = myview.findViewById(R.id.markaname2);
        markaname3 = myview.findViewById(R.id.markaname3);

        image1 = myview.findViewById(R.id.image1);
        image2 = myview.findViewById(R.id.image2);
        image3 = myview.findViewById(R.id.image3);

        ason.setText(ASON);

        nid.setText(voterId);
        serial.setText(voterserial);
        center.setText(centerName);
        address.setText(Address);

        candidate1.setText(CAN1);
        candidate2.setText(CAN2);
        candidate3.setText(CAN3);

        markaname1.setText(MARN1);
        markaname2.setText(MARN2);
        markaname3.setText(MARN3);



        Picasso.get().load(img1).placeholder(R.drawable.ecb).into(image1);
        Picasso.get().load(img2).placeholder(R.drawable.ecb).into(image2);
        Picasso.get().load(img3).placeholder(R.drawable.ecb).into(image3);

        Picasso.get().load(MARKA1).placeholder(R.drawable.ecb).into(marka1);
        Picasso.get().load(MARKA2).placeholder(R.drawable.ecb).into(marka2);
        Picasso.get().load(MARKA3).placeholder(R.drawable.ecb).into(marka3);



        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        return myview;
}
}