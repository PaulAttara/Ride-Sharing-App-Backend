package ca.mcgill.ecse321.driver;


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

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class RouteListingsFragment extends Fragment {


    public RouteListingsFragment() {
        // Required empty public constructor
    }

    private LinearLayout parentLinearLayout;
    ArrayList<RouteTemplate> dataModels;
    ListView listView;
    private static RouteAdapter adapter;
    private String username = MainActivity.username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setActionBarTitle("Route Listings");

        //get id
        final View routeListingsView = inflater.inflate(R.layout.fragment_listings_route, null);

        listView = (ListView) routeListingsView.findViewById(R.id.routelistingslistview);
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

        dataModels = new ArrayList<>();
        //System.out.println("TEST: " + MainActivity.username );

        //This is where the get method for routes goes for the user.
//        HttpUtils.get("api/user/create", rp, new JsonHttpResponseHandler() {
//            @Override
//            public void onFinish() {
//                //refreshErrorMessage();
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                try {
//                } catch (Exception e) {
//                }
//                //refreshErrorMessage();
//            }
//
//            // ONSUCCESS: For some reason it always fails, but the value we're looking for is stored in errorResponse
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//            }
//        });

        dataModels.add(new RouteTemplate("Sauvignon", "McGill"));
        dataModels.add(new RouteTemplate("Canada", "Egypt"));
        dataModels.add(new RouteTemplate("Toronto", "Ottawa"));
        dataModels.add(new RouteTemplate("Sauvignon", "McGill"));
        dataModels.add(new RouteTemplate("Canada", "Egypt"));
        dataModels.add(new RouteTemplate("Toronto", "Ottawa"));
        dataModels.add(new RouteTemplate("Sauvignon", "McGill"));
        dataModels.add(new RouteTemplate("Canada", "Egypt"));
        dataModels.add(new RouteTemplate("Toronto", "Ottawa"));
        dataModels.add(new RouteTemplate("Sauvignon", "McGill"));
        dataModels.add(new RouteTemplate("Canada", "Egypt"));
        dataModels.add(new RouteTemplate("Toronto", "Ottawa"));

        adapter = new RouteAdapter(dataModels, ((MainActivity)getActivity()).getApplicationContext());


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //dataModel is the selected item
                RouteTemplate dataModel= dataModels.get(position);

                //Display message
                Snackbar.make(view, dataModel.getStartAddress()+"\n"+dataModel.getEndAddress(), Snackbar.LENGTH_LONG)
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


}
