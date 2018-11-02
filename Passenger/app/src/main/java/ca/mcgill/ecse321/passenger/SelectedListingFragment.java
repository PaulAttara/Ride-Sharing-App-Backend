package ca.mcgill.ecse321.passenger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectedListingFragment extends Fragment {


    public SelectedListingFragment() {
        // Required empty public constructor
    }

    //Set this ID field when listing is selected
    //use this ID to know which listing selected
    //populate the fields of the page using it


    //ArrayList<RouteTemplate> dataModels;
    public static String routeID;
    ArrayList<SearchTemplate> dataModels;
    //ListView listView;
    private static SearchAdapter adapter;
    String error = "";
    //String passedVar = null;
    TextView txtselectedstartaddess;
    TextView txtselectedendaddess;
    TextView txtStartAddress;
    TextView txtEndAddress;
    Button btnBackResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View selectedListingView = inflater.inflate(R.layout.fragment_selected_listing, null);
        txtStartAddress = selectedListingView.findViewById(R.id.txtselectedstartaddess);
        txtEndAddress = selectedListingView.findViewById(R.id.txtselectedendaddess);
        btnBackResults = selectedListingView.findViewById(R.id.btnbackresults);

        btnBackResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToSearchResults(v);
            }
        });

        TextView textview = (TextView) selectedListingView.findViewById(R.id.txtselectedstartaddess);

        populateSelectedListingPage();
        return selectedListingView;
    }


    //need this method to return back to the specific search
    //if return back by reselecting menu from nav drawer, the search needs to be made again
    //to fix this, we get the string in the search bar and run it again
    private void backToSearchResults(View v) {
        //need to modify the getSearchResults method in SearchListingsFragment.java to
        // take in parameters String location and String sorBy to redo the search
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fMain, new SearchListingsFragment());
        ft.commit();
        //ft.add(R.id.fMain, );
        //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

    }

    private void populateSelectedListingPage() {
        dataModels = new ArrayList<>();

        //String pathUrl = "api/route/getStops" + "/" + ID;
        //This is where the get method for routes goes for the user.
        // HttpUtils.get(pathUrl, new RequestParams(), new JsonHttpResponseHandler() {
        //final String ID = txtStartAddress.getText().toString();
        String pathURL = "api/location/getLocationsForPassenger/" + routeID + "/";
        RequestParams rp = new RequestParams();
        //HttpUtils.get(pathURL, new RequestParams(), new JsonHttpResponseHandler() {
        //HttpUtils.get(pathURL, rp, new JsonHttpResponseHandler() {

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

        //set the fields with values using ID
        //passedVar=getActivity().getIntent().getStringExtra(SearchListingsFragment.ID_EXTRA);
        //example
        //TextView txtStartAddress = (TextView)getView().findViewById(R.id.txtselectedstartaddess);
        //TextView txtEndAddress = (TextView)getView().findViewById(R.id.txtselectedendaddess);
        //txtStartAddress=(TextView)findViewById(R.id.txtselectedstartaddess);
        //txtEndAddress=(TextView)findViewById(R.id.txtselectedendaddess);
        //txtStartAddress.setText("CANADA");
        //txtEndAddress.setText("USA");
        txtStartAddress.setText("You selected start address ID" +routeID);
        txtEndAddress.setText("You selected start address ID" +routeID);
    }

}