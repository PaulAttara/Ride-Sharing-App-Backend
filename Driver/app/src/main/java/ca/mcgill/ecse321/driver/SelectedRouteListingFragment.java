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

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.protocol.RequestExpectContinue;
import org.json.JSONArray;
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

    public static int routeID;
    private String date = null;
    private String time = null;
    TextView txtselectedstartaddess;
    TextView txtselectedendaddess;
    Button btnBackResults;
    EditText txtnewstartaddess;
    EditText txtnewendaddess;
    TextView error;
    Button btndeleteRoute;
    Button btnmodifyroute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View selectedRouteListingView = inflater.inflate(R.layout.fragment_selected_route_listing, null);
        txtselectedstartaddess = selectedRouteListingView.findViewById(R.id.txtselectedstartaddess);
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
        String pathURL = "api/route/update/";
        RequestParams rp = new RequestParams();

        rp.add("date", date);
        rp.add("time", time);

        HttpUtils.get(pathURL,  rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                //Route was successfully updated
                Toast.makeText(null, response, Toast.LENGTH_LONG).show();
                }


            //This one catches the error, not the one above
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(null, errorResponse.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //refresh route info
        loadRouteInfo();
    }

    private void onDeleteRoute(View v) {
        String pathURL = "api/route/delete/" + routeID + "/";
        RequestParams rp = new RequestParams();
        HttpUtils.get(pathURL,  rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                Toast.makeText(null, response, Toast.LENGTH_LONG).show();

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

        //refresh route info
        loadRouteInfo();
    }

    
    private void loadRouteInfo() {
        String pathURL = "api/route/getRoute/" + routeID + "/";
        RequestParams rp = new RequestParams();
        HttpUtils.get(pathURL,  rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                     int routeId = response.getJSONObject(0).getInt("routeId");
                     String startAddress = response.getJSONObject(0).getString("startLocation");
                     String endAddress = response.getJSONObject(0).getString("startLocation");
                     txtselectedendaddess.setText(endAddress);
                     txtselectedstartaddess.setText(startAddress);
                } catch (Exception e){

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

}
