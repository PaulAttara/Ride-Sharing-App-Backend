package ca.mcgill.ecse321.driver;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;


public class RouteListingsFragment extends Fragment {


    public RouteListingsFragment() {
        // Required empty public constructor
    }

    private LinearLayout parentLinearLayout;
    ArrayList<RouteTemplate> dataModels;
    ListView listView;
    private static RouteAdapter adapter;


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

                RouteTemplate dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getStartAddress()+"\n"+dataModel.getEndAddress(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }


}
