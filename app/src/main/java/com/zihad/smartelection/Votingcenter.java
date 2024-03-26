package com.zihad.smartelection;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class Votingcenter extends Fragment {

    ImageView toolbar;
    DrawerLayout drawerLayout;

    TextView dateformate;

    EditText edidnumber;

    AppCompatButton Verify;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String>hashMap;

    int year;
    int month;
    int day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview =inflater.inflate(R.layout.fragment_votingcenter, container, false);

        toolbar = myview.findViewById(R.id.toolbar);
        drawerLayout = myview.findViewById(R.id.drawer_layout);
        Verify = myview.findViewById(R.id.Verify);
        edidnumber=myview.findViewById(R.id.edidnumber);

      //  dateformate=myview.findViewById(dateformate);


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });





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


        Verify.setOnClickListener(new View.OnClickListener() {

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



                                            arrayList.add(hashMap);


                                            Search.voterId = nid;
                                            Search.centerName = center;
                                            Search.Address = Address;
                                            Search.voterserial = serial;
                                            Search.img1 = image1;
                                            Search.img2 = image2;
                                            Search.img3=image3;




                                            FragmentManager fmanager = getActivity().getSupportFragmentManager();
                                            FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
                                            fragmentTransaction.add(R.id.frame_layout, new Search());
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


        return myview;
    }
}