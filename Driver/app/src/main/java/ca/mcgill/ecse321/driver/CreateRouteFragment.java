package ca.mcgill.ecse321.driver;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class CreateRouteFragment extends Fragment {

    // Http request
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private EditText numSeats;
    private EditText routeDate;
    private EditText routeTime;
    private EditText startCity;
    private EditText startAddress;
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
        startCity = (EditText) createRouteView.findViewById(R.id.txtstartcity);
        startAddress = (EditText) createRouteView.findViewById(R.id.txtstartstreet);
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
        //validate date
        int month = Integer.parseInt(routeDate.getText().toString().substring(5,7));
        int day = Integer.parseInt(routeDate.getText().toString().substring(5,7));

        if (month >12 || month < 0 || day > 31 || day < 0) {
            Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Please enter a valid date.", Toast.LENGTH_LONG).show();
            return;
        }

        // send post to get car id

        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/vehicle/getIdFromUsername/" + username + "/";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), response, Toast.LENGTH_LONG).show();
                int carId = Integer.parseInt(response);
                if (carId == -1) {
                    Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "The current driver doesn't have a car!", Toast.LENGTH_LONG).show();

                }
                else {
                    createRouteForCarId(carId);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Response error in getting car Id", Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(mStringRequest);

    }
    private void createRouteForCarId (final int carId) {
        // Building url parameters
        String requestParams = "?";
        requestParams += "seats=" + numSeats.getText().toString();
        requestParams += "&car=" + carId;
        requestParams += "&date=" + routeDate.getText().toString();
        requestParams += "&startLocation=" + startCity.getText().toString() + ", " + startAddress.getText().toString();
        requestParams += "&time=" + routeTime.getText().toString();

        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/route/create" + requestParams;

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.POST, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Route was successfully created
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), response, Toast.LENGTH_LONG).show();
                int routeId = Integer.parseInt(response);
                if (routeId != -1) {
                    Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Route was successfully created", Toast.LENGTH_LONG).show();
                    createLocations(routeId, carId);
                } else {
                    Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Error in creating route, make sure all fields valid.", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Response error in creating the route", Toast.LENGTH_LONG).show();;//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    private void createLocations(final int routeId, int carId) {
        int count = parentLinearLayout.getChildCount();
        RequestParams rp = new RequestParams();

        for (int i = 4; i < count-1; i++) {
            View view = parentLinearLayout.getChildAt(i);

            newLocationCity = (EditText) view.findViewById(R.id.txtnewlocation);
            newLocationStreet = (EditText) view.findViewById(R.id.txtlocationstreet);
            newLocationPrice = (EditText) view.findViewById(R.id.txtlocationprice);

            String city = newLocationCity.getText().toString();
            String street = newLocationStreet.getText().toString();
            String price = newLocationPrice.getText().toString();

            if (city.equals("") || street.equals("") || price.equals("")) {
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Location " + (i-3) + " incomplete, not added.", Toast.LENGTH_LONG).show();
            }


            String requestParams = "?";
            requestParams += "city=" + city;
            requestParams += "&street=" + street;
            requestParams += "&price=" + price;
            requestParams += "&routeId=" + routeId;

            //Http GET request to login starts here
            String baseURL = "https://sharefare.herokuapp.com/api";
            String pathURL = baseURL + "/location/create" + requestParams;

            //RequestQueue initialized
            mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

            //String Request initialized
            mStringRequest = new StringRequest(Request.Method.POST, pathURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Route was successfully created
                    Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    int locationId = Integer.parseInt(response);
                    if (locationId != -1) {
                        Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Route was successfully created", Toast.LENGTH_LONG).show();
                        //addLocationToRoute(locationId, routeId);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Response error in creating location.", Toast.LENGTH_LONG).show();;//display the response on screen
            }
            });

            mRequestQueue.add(mStringRequest);

            }
    }

    private void addLocationToRoute(int locationId, int routeId) {
        String pathUrl = "api/location/assignLocations/" + locationId + "/" + routeId + "/";

        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/location/assignLocations/" + locationId + "/" + routeId + "/";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Response error in creating location.", Toast.LENGTH_LONG).show();;//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);
    }


}