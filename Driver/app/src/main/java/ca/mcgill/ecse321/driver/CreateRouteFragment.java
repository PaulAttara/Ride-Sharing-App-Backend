package ca.mcgill.ecse321.driver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class CreateRouteFragment extends Fragment {

    private EditText numSeats;
    private EditText routeDate;
    private EditText routeTime;
    private String username = MainActivity.username;

    public CreateRouteFragment() {
        // Required empty public constructor
    }

    private LinearLayout parentLinearLayout;
    Button addRowBtn;
    Button removeRowBtn;
    Button createRouteBtn;
    private EditText newLocationCity;
    private EditText newLocationPrice;
    private EditText newLocationStreet;

    private int carId = -1;
    private int routeId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initialization
        ((MainActivity)getActivity()).setActionBarTitle("Create Route");

        //get id
        final View createRouteView = inflater.inflate(R.layout.fragment_create_route, null);
        addRowBtn = (Button) createRouteView.findViewById(R.id.btnaddlocation);
        removeRowBtn = (Button) createRouteView.findViewById(R.id.btnremovelocation);
        parentLinearLayout = (LinearLayout) createRouteView.findViewById(R.id.parent_linear_layout);
        createRouteBtn = (Button) createRouteView.findViewById(R.id.btncreateroute);
        numSeats = (EditText) createRouteView.findViewById(R.id.txtnumberofseats);
        routeDate = (EditText) createRouteView.findViewById(R.id.txtroutedate);
        routeTime = (EditText) createRouteView.findViewById(R.id.txtroutetime);

        //button listeners
        addRowBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onAddNewLocationRow(v);
            }
        });
        removeRowBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onNewLocationDelete(v);
            }
        });

        createRouteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onCreateRoute(v);

            }
        });

        return createRouteView;
    }


    public void onAddNewLocationRow(View v) {
        LayoutInflater inflater = (LayoutInflater) ((MainActivity)getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.addlocationrow, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        Button remove = rowView.findViewById(R.id.btnremovelocation);
        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onNewLocationDelete(v);
            }
        });

    }

    public void onNewLocationDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }

    public void onCreateRoute(View v) {

        RequestParams rp = new RequestParams();
        String seats = numSeats.getText().toString();
        String date = numSeats.getText().toString();
        String time = numSeats.getText().toString();

        // send post to get car id
        String pathURL = "api/vehicle/getIdFromUsername/" + username + "/";

        HttpUtils.get(pathURL,  new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                //Route was successfully created
                Toast.makeText(null, response, Toast.LENGTH_LONG).show();
                carId = Integer.parseInt(response);
                if (carId == -1) {
                    Toast.makeText(null, "The current driver doesn't have a car!", Toast.LENGTH_LONG).show();
                    return;
                }

            }

            // For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {

            }

            //This one catches the error, not the one above
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });

        rp.add("seats", seats);
        rp.add("role", date);
        rp.add("time", time);
        rp.add("car" , "" + carId);


        //send post to create route
        pathURL = "api/route/create";

        HttpUtils.get(pathURL, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                //Route was successfully created
                Toast.makeText(null, response, Toast.LENGTH_LONG).show();
                routeId = Integer.parseInt(response);
                if (routeId != -1) {
                    Toast.makeText(null, "Route was successfully created", Toast.LENGTH_LONG).show();
                    createLocations();
                }

            }

            // For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {

            }

            //This one catches the error, not the one above
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });


    }

    private void createLocations() {
        int count = parentLinearLayout.getChildCount();
        RequestParams rp = new RequestParams();

        for (int i = 4; i < count-1; i++) {
            View view = parentLinearLayout.getChildAt(i);

            newLocationCity = (EditText) view.findViewById(R.id.txtnewlocation);
            newLocationStreet = (EditText) view.findViewById(R.id.txtlocationstreet);
            newLocationPrice = (EditText) view.findViewById(R.id.txtlocationprice);
            if (newLocationCity != null && newLocationStreet != null && newLocationPrice != null) {
                String city = newLocationCity.getText().toString();
                String street = newLocationCity.getText().toString();
                String price = newLocationCity.getText().toString();
                rp.add("city", city);
                rp.add("street", street);
                rp.add("price", price);
                rp.add("routeId", "" + routeId);
            }

            //send post to create locations

            String pathUrl = "api/location/create";

            HttpUtils.get(pathUrl, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    //Route was successfully created
                    Toast.makeText(null, response, Toast.LENGTH_LONG).show();
                    int locationId = Integer.parseInt(response);
                    if (locationId != -1) {
                        Toast.makeText(null, "Route was successfully created", Toast.LENGTH_LONG).show();
                        addLocationToRoute(locationId);
                    }


                }

                //This one catches the error, not the one above
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Toast.makeText(null, "There was an error creating the location", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private void addLocationToRoute(int locationId) {
        String pathUrl = "api/location/assignLocations/" + locationId + "/" + routeId + "/";

        HttpUtils.get(pathUrl, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                //Location was successfully added to route
                Toast.makeText(null, "Location was successfully added to route", Toast.LENGTH_LONG).show();

            }

            //This one catches the error, not the one above
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(null, "There was an error linking location to route", Toast.LENGTH_LONG).show();
            }
        });
    }


}