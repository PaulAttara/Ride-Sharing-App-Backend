package ca.mcgill.ecse321.driver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.protocol.RequestExpectContinue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectedRouteListingFragment extends Fragment {


    public SelectedRouteListingFragment() {
        // Required empty public constructor
    }


    //Set this ID field when listing is selected
    //use this ID to know which listing selected
    //populate the fields of the page using it
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    public static int routeID;
    private String date = null;
    private String time = null;
    TextView txtselectedstartaddess;
    TextView txtselectedendaddess;
    TextView txtselecteddate;
    TextView txtselectedtime;
    Button btnBackResults;
    EditText txtnewstartaddess;
    EditText txtnewendaddess;
    EditText txtnewdate;
    EditText txtnewtime;

    TextView error;
    Button btndeleteRoute;
    Button btnmodifyroute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View selectedRouteListingView = inflater.inflate(R.layout.fragment_selected_route_listing, null);
        txtselectedstartaddess = selectedRouteListingView.findViewById(R.id.txtselectedstartaddess);
        txtselecteddate = selectedRouteListingView.findViewById(R.id.txtselecteddate);
        txtselectedtime = selectedRouteListingView.findViewById(R.id.txtselectedtime);
        txtnewdate = selectedRouteListingView.findViewById(R.id.txtnewdate);
        txtnewtime = selectedRouteListingView.findViewById(R.id.txtnewtime);
        btnBackResults = selectedRouteListingView.findViewById(R.id.btnbackresults);
        txtnewstartaddess = selectedRouteListingView.findViewById(R.id.txtnewstartaddess);
        btnmodifyroute = selectedRouteListingView.findViewById(R.id.btnmodifyroute);
        error = selectedRouteListingView.findViewById(R.id.errorselected);
        btndeleteRoute =  selectedRouteListingView.findViewById(R.id.btndeleteroute);

        ///POPULATE PAGE USING ROUTE ID, THERES A CONTROLLER METHOD THAT RETURNS
        //CALL GET ROUTE USING ID, RETURN INTO JSON OBJECT
        //PARSE OBJECT TO GET DATE AND TIME
        //NEED TO CALL THEM HERE

        btnBackResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fMain, new RouteListingsFragment());
                ft.commit();
            }
        });

        btndeleteRoute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onDeleteRoute(v);
            }
        });
        btnmodifyroute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                modifyRoute(v);
            }
        });



        loadRouteInfo();
        return selectedRouteListingView;
    }

    private void modifyRoute(View v) {
        if (!txtnewstartaddess.getText().toString().equals("")) {
            txtselectedstartaddess.setText(txtnewstartaddess.getText().toString());
            txtnewstartaddess.setText("");
        }
        if (!txtnewdate.getText().toString().equals("")){
            txtselecteddate.setText(txtnewdate.getText().toString());
            txtnewdate.setText("");
        }
        if (!txtnewtime.getText().toString().equals("")){
            txtselectedtime.setText(txtnewtime.getText().toString());
            txtnewtime.setText("");
        }
        //refresh route info

    }

    private void onDeleteRoute(View v) {
        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/route/remove/" + SelectedRouteListingFragment.routeID + "/";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.POST, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true")){
                    Toast.makeText(((MainActivity)getActivity()).getApplicationContext(),"Route deleted", Toast.LENGTH_LONG).show();
                    
                    //go back to listings
                    FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fMain, new RouteListingsFragment());
                    ft.commit();
                } else {
                    Toast.makeText(((MainActivity)getActivity()).getApplicationContext(),"Could not delete route. Contact Admin.", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Response error in deleting route.", Toast.LENGTH_LONG).show();;//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);
        //refresh route info
        //loadRouteInfo();
    }

    
    private void loadRouteInfo() {


        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/route/getRoute/" + SelectedRouteListingFragment.routeID + "/";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

        //String Request initialized
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, pathURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int routeId = response.getInt("routeId");
                            String startAddress = response.getString("startLocation");
                            String date = response.getString("date");
                            String thisdate = date.substring(0,10);
                            String thistime = date.substring(11,19);

                            txtselecteddate.setText(thisdate);
                            txtselectedtime.setText(thistime);
                            txtselectedstartaddess.setText(startAddress);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        mRequestQueue.add(jsonObjectRequest);
//        String pathURL = "api/route/getRoute/" + SelectedRouteListingFragment.routeID + "/";
//        RequestParams rp = new RequestParams();
//        HttpUtils.get(pathURL,  rp, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                try {
//                     int routeId = response.getJSONObject(0).getInt("routeId");
//                     String startAddress = response.getJSONObject(0).getString("startLocation");
//                     String endAddress = response.getJSONObject(0).getString("startLocation");
//                     txtselectedendaddess.setText(endAddress);
//                     txtselectedstartaddess.setText(startAddress);
//                } catch (Exception e){
//
//                }
//
//            }
//
//            // For some reason it always fails, but the value we're looking for is stored in errorResponse
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
//
//            }
//
//            //This one catches the error, not the one above
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//
//            }
//        });



    }

}
