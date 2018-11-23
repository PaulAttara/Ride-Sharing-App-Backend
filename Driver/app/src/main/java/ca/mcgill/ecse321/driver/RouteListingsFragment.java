package ca.mcgill.ecse321.driver;


import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class RouteListingsFragment extends Fragment {


    public RouteListingsFragment() {
        // Required empty public constructor
    }

    // Http request
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private LinearLayout parentLinearLayout;
    ArrayList<RouteTemplate> routes; //for UI when logging in with valid user
    ArrayList<RouteTemplate> routesTEST; //for UI when logging in with admin user(AS A TEST), in case httputil doesnt pass
    ArrayList<LocationTemplate> locationsTEST;


    ListView listView;
    private static RouteAdapter adapter;
    private String username = MainActivity.username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setActionBarTitle("Route Listings");

        //get id
        final View routeListingsView = inflater.inflate(R.layout.fragment_listings_route, null);

        listView = (ListView) routeListingsView.findViewById(R.id.routelistingslistview);
        locationsTEST = new ArrayList<LocationTemplate>();
        routesTEST = new ArrayList<RouteTemplate>();

        getRouteListings();

        return routeListingsView; // You need to return the view in which the changed text exists
    }

    public void getRouteListings(){

        //for testing purposes (WHOLE IF STATEMENT)//////////////

        if(username.equals("admin")){

            locationsTEST.add(new LocationTemplate("DDO", "Mozart", "", 0));
            locationsTEST.add(new LocationTemplate("Montreal", "Mansfield", "", 200));

            routesTEST.add(new RouteTemplate(locationsTEST, "2018-11-15",1234, 5, 1234 ));
            routesTEST.add(new RouteTemplate(locationsTEST, "2018-11-15",1234, 5, 1234 ));

            adapter = new RouteAdapter(routesTEST, ((MainActivity)getActivity()).getApplicationContext());


            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //dataModel is the selected item
                    RouteTemplate dataModel = routesTEST.get(position);

                    //Display message
                    Snackbar.make(view, dataModel.getStartLocation().toString()+"\n"+dataModel.getEndLocation().toString(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();

                    //set the ID of the selected item so that the fields on the selected listing page can be populated
                    //SelectedListingFragment.ID = ...;

                    //navigate to selected listing page
                    FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fMain, new SelectedRouteListingFragment());
                    ft.commit();

                    //set routeID in selected route page
                    SelectedRouteListingFragment.routeID = 1;
                }
            });
            return;
        }

        routes = new ArrayList<RouteTemplate>();

        mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/route/getRoutes" + "/" + username + "/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, pathURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int  i = 0; i < response.length(); i++) {

                                JSONObject route = response.getJSONObject(i);
                                int routeId = route.getInt("routeId");
                                String date = route.getString("date");
                                int numSeats = route.getInt("seatsAvailable");
                                int carId = route.getJSONObject("car").getInt("vehicleId");
                                getRouteLocations(routeId, date, numSeats,carId);

                                //delay to allow server response
                                Thread.sleep(150);

                            }
                    } catch (Exception e) {
                            Toast.makeText(((MainActivity)getActivity()).getApplicationContext(),"Error: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(((MainActivity)getActivity()).getApplicationContext(),"Request Error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        mRequestQueue.add(jsonArrayRequest);

        adapter = new RouteAdapter(routes, ((MainActivity)getActivity()).getApplicationContext());


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //dataModel is the selected item
                RouteTemplate route = routes.get(position);

                //Display message
                Snackbar.make(view, route.getStartLocation().toString()+"\n"+route.getEndLocation().toString(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();

                //set the ID of the selected item so that the fields on the selected listing page can be populated
                //SelectedListingFragment.ID = ...;

                //navigate to selected listing page
                FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fMain, new SelectedRouteListingFragment());
                ft.commit();

                //set routeID in selected route page
                //SelectedRouteListingFragment.routeID = 1;
            }
        });
    }

    private void getRouteLocations(final int routeId, final String date, final int numSeats, final int carId) throws InterruptedException{

        mRequestQueue = Volley.newRequestQueue(((MainActivity)getActivity()).getApplicationContext());

        //Http GET request to login starts here
        String baseURL = "http://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/route/getStops" + "/" + routeId + "/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, pathURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            ArrayList<LocationTemplate> locations = new ArrayList<LocationTemplate>();
                            for (int  i = 0; i < response.length(); i++) {

                                JSONObject location = response.getJSONObject(i);
                                int locationId  = location.getInt("locationId");
                                String city = location.getString("city");
                                double price = location.getInt("price");
                                String street = location.getString("street");
                                //TODO add passengers to location;
                                String passenger = "";

                                locations.add(new LocationTemplate(city, street, passenger, price));


                            }

                            routes.add(new RouteTemplate(locations, date, routeId, numSeats, carId));

                        } catch (Exception e) {
                            Toast.makeText(((MainActivity)getActivity()).getApplicationContext(),"Error: " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(((MainActivity)getActivity()).getApplicationContext(),"Request Error:" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        mRequestQueue.add(jsonArrayRequest);
    }
}



