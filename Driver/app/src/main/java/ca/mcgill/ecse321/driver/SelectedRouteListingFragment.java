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
        txtselectedendaddess = selectedRouteListingView.findViewById(R.id.txtselectedendaddess);
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
    }

    private void onDeleteRoute(View v) {

        //deleeeete
    }

    
    private void loadRouteInfo() {


    }

}
