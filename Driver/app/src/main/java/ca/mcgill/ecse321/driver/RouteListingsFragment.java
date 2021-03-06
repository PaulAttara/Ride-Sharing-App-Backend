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

    private LinearLayout parentLinearLayout;
    ArrayList<RouteTemplate> dataModels; //for UI when logging in with valid user
    ArrayList<RouteTemplate> dataModelsTEST; //for UI when logging in with admin user(AS A TEST), in case httputil doesnt pass
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
        dataModelsTEST = new ArrayList<RouteTemplate>();

        getRouteListings();


        //Swipe to refresh locations list: works but unable to remove the reloading wheel, so currently disabled

//        SwipeRefreshLayout swipe = routeListingsView.findViewById(R.id.swiperefresh);
//        swipe.setRefreshing(false);
//
//        swipe.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        getRouteListings();
//                        // This method performs the actual data-refresh operation.
//                        // The method calls setRefreshing(false) when it's finished.
//                        //myUpdateOperation();
//                    }
//                }
//        );
//        swipe.setRefreshing(false);

        return routeListingsView; // You need to return the view in which the changed text exists
    }

    public void getRouteListings(){

        //for testing purposes (WHOLE IF STATEMENT)//////////////

        if(username.equals("admin")){

            locationsTEST.add(new LocationTemplate("DDO", "Mozart", "", 0));
            locationsTEST.add(new LocationTemplate("Montreal", "Mansfield", "", 200));

            dataModelsTEST.add(new RouteTemplate(locationsTEST, "2018-11-15",1234, 5, 1234 ));

            adapter = new RouteAdapter(dataModelsTEST, ((MainActivity)getActivity()).getApplicationContext());


            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //dataModel is the selected item
                    RouteTemplate dataModel = dataModelsTEST.get(position);

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



        dataModels = new ArrayList<>();

        String pathUrl = "api/route/getRoutes" + "/" + username + "/";
        //This is where the get method for routes goes for the user.
        HttpUtils.get(pathUrl, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onFinish() {
                System.out.println("FINISHED");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    int len = response.length();

                    ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
                    ArrayList<LocationTemplate> locations = new ArrayList<LocationTemplate>();

                    for (int  i = 0; i < len; i++) {
                        JSONArray route = response.getJSONArray(i);
                        int routeId = (int) route.get(0);
                        String date = (String) route.get(1);
                        int numSeats = (int) route.get(2);
                        int carId = (int) route.get(3);
                        locations = getRouteLocations(routeId);
                        dataModels.add(new RouteTemplate(locations, date, routeId, numSeats, carId));
                    }
                } catch (Exception e) {
                    String message =e.getMessage();
                    String message2 =e.getMessage();
                }


                System.out.print("SUCCESS");
                //refreshErrorMessage();


            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse){
                System.out.print("FAILED");
            }
            // ONSUCCESS: For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                System.out.print("FAILED");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.print("FAILED");
            }
        });

        //dataModels.add(new RouteTemplate("Sauvignon", "McGill"));

        adapter = new RouteAdapter(dataModels, ((MainActivity)getActivity()).getApplicationContext());


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //dataModel is the selected item
                RouteTemplate dataModel = dataModels.get(position);

                //Display message
                Snackbar.make(view, dataModel.getStartLocation().toString()+"\n"+dataModel.getEndLocation().toString(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();

                //set the ID of the selected item so that the fields on the selected listing page can be populated
                //SelectedListingFragment.ID = ...;

                //navigate to selected listing page
                FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fMain, new SelectedRouteListingFragment());
                ft.commit();
            }
        });
    }

    private ArrayList<LocationTemplate> getRouteLocations(int routeId) throws InterruptedException{
        String pathUrl = "api/route/getStops" + "/" + routeId + "/";
        final ArrayList<LocationTemplate> locations = new ArrayList<LocationTemplate>();

        HttpUtils.get(pathUrl, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onFinish() {
                System.out.println("FINISHED");
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, java.lang.String responseString){
                System.out.println("SUCCESS");
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode,headers, response);
                try {
                    int len = response.length();

                    ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();

                    for (int i = 0; i < len; i++) {
                        JSONArray route = response.getJSONArray(i);
                        int locationId = (int) route.get(0);
                        String city = (String) route.get(1);
                        double price = (double) route.get(2);
                        String street = (String) route.get(3);
                        //TODO add passengers to location;
                        String passenger = "";
                        locations.add(new LocationTemplate(city, street, passenger, price));

                    }
                } catch (Exception e) {
                    String message = e.getMessage();
                }


                System.out.print("SUCCESS");
                //refreshErrorMessage();


            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                System.out.print("FAILED");
            }

            // ONSUCCESS: For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                System.out.print("FAILED");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.print("FAILED");
            }
        });

        //FOR SOME REASON, THESE WERE NEEDED HERE TO MAKE THE ABOVE GET REQUEST WORK
        locations.add(new LocationTemplate("DDO", "Mozart", "", 0));
        locations.add(new LocationTemplate("Montreal", "Mansfield", "", 200));
        //FOR SOME REASON, THESE WERE NEEDED HERE TO MAKE THE ABOVE GET REQUEST WORK

        return locations;
    }
}



