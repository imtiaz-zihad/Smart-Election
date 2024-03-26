package com.zihad.smartelection;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class HomeFragment extends Fragment {
    ImageView toolbar;
    DrawerLayout drawerLayout;
    TextView dateformate;
    EditText edidnumber;
    AppCompatButton verifybutton;

    int year;
    int month;
    int day;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String>hashMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview =inflater.inflate(R.layout.fragment_home, container, false);



        toolbar = myview.findViewById(R.id.toolbar);
        edidnumber = myview.findViewById(R.id.editformat);
        verifybutton = myview.findViewById(R.id.verifybutton);



        //==============for toolbar========================/////////////
        drawerLayout = myview.findViewById(R.id.drawer_layout);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
         }

        });

        //==============for toolbar========================/////////////


        ///========================date picker====================//



        dateformate = myview.findViewById(R.id.dateformate);
        dateformate.setShowSoftInputOnFocus(false);///for keyboard off
        Calendar calendar = Calendar.getInstance();
        dateformate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year =calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        dateformate.setText(date);
                       // dateformate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));   for current date

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });


        //======================Verify Button For Query=============//


        verifybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {





                String nid = edidnumber.getText().toString();
                String birth = dateformate.getText().toString();


                String url = "https://java-error.com/App/Zihad/Election-Gov/view.php?nid="+nid+"&birth="+birth;


                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("serverRes", String.valueOf(response));

                                try {

                                    if (response.length()>0) {


                                        for (int x = 0; x < response.length(); x++) {
                                            JSONObject jsonResponse = response.getJSONObject(x);
                                            String nid = jsonResponse.getString("nid");
                                            String birth = jsonResponse.getString("birth");
                                            String serial = jsonResponse.getString("serial");
                                            String Address = jsonResponse.getString("address");
                                            String center = jsonResponse.getString("center");
                                            String id = jsonResponse.getString("id");
                                            String image1 = jsonResponse.getString("image1");
                                            String image2 = jsonResponse.getString("image2");
                                            String image3 = jsonResponse.getString("image3");

                                            String candidate1 = jsonResponse.getString("candidate1");
                                            String candidate2 = jsonResponse.getString("candidate2");
                                            String candidate3 = jsonResponse.getString("candidate3");

                                            String markaname1 = jsonResponse.getString("markaname1");
                                            String markaname2 = jsonResponse.getString("markaname2");
                                            String markaname3 = jsonResponse.getString("markaname3");

                                            String marka1 = jsonResponse.getString("marka1");
                                            String marka2 = jsonResponse.getString("marka2");
                                            String marka3 = jsonResponse.getString("marka3");

                                            String ason = jsonResponse.getString("ason");



                                            hashMap = new HashMap<>();
                                            hashMap.put("nid", nid);
                                            hashMap.put("birth", birth);
                                            hashMap.put("serial", serial);
                                            hashMap.put("Address", Address);
                                            hashMap.put("center", center);
                                            hashMap.put("id", id);
                                            hashMap.put("image1", image1);
                                            hashMap.put("image2", image2);
                                            hashMap.put("image3",image3);

                                            hashMap.put("marka1", marka1);
                                            hashMap.put("marka2", marka2);
                                            hashMap.put("marka3", marka3);

                                            hashMap.put("markaname1", markaname1);
                                            hashMap.put("markaname2", markaname2);
                                            hashMap.put("markaname3", markaname3);

                                            hashMap.put("candidate1", candidate1);
                                            hashMap.put("candidate2", candidate2);
                                            hashMap.put("candidate3",candidate3);

                                            hashMap.put("ason",ason);

                                            arrayList.add(hashMap);


                                            AccountFragment.voterId = nid;
                                            AccountFragment.centerName = center;
                                            AccountFragment.Address = Address;
                                            AccountFragment.voterserial = serial;
                                            AccountFragment.img1 = image1;
                                            AccountFragment.img2 = image2;
                                            AccountFragment.img3=image3;

                                            AccountFragment.MARKA1 = marka1;
                                            AccountFragment.MARKA2 = marka2;
                                            AccountFragment.MARKA3 = marka3;
                                            AccountFragment.ASON = ason;
                                            AccountFragment.MARN1 = markaname1;
                                            AccountFragment.MARN2 = markaname2;
                                            AccountFragment.MARN3=markaname3;

                                            AccountFragment.CAN1 = candidate1;
                                            AccountFragment.CAN2 = candidate2;
                                            AccountFragment.CAN3=candidate3;


                                            FragmentManager fmanager = getActivity().getSupportFragmentManager();
                                            FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
                                            fragmentTransaction.add(R.id.frame_layout, new AccountFragment());
                                            fragmentTransaction.commit();
                                           // Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();


                                        }
                                    }else {
                                        edidnumber.setError("user does not exist");
                                        edidnumber.requestFocus();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("sarvarRes", "Error parsing JSON");
                                    Toast.makeText(getActivity(), "Failed to parse JSON.", Toast.LENGTH_SHORT).show();
                                }

                            }

                        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("sarvarRes", error.toString());
                        Toast.makeText(getActivity(), "Failed to fetch data.", Toast.LENGTH_SHORT).show();

                    }

                });



                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(jsonArrayRequest);
            }

        });


        ///========================Query Ses=====================//




        ///========================date picker====================//


        return myview;
    }
}