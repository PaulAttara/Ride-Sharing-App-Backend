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

    Button btnchangestartaddress;
    Button btnchangeendaddress;
    Button deleteRoute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View selectedRouteListingView = inflater.inflate(R.layout.fragment_selected_route_listing, null);
        txtselectedstartaddess = selectedRouteListingView.findViewById(R.id.txtselectedstartaddess);
        txtselectedendaddess = selectedRouteListingView.findViewById(R.id.txtselectedendaddess);
        btnBackResults = selectedRouteListingView.findViewById(R.id.btnbackresults);
        txtnewstartaddess = selectedRouteListingView.findViewById(R.id.txtnewstartaddess);
        txtnewendaddess = selectedRouteListingView.findViewById(R.id.txtnewendaddess);
        btnchangestartaddress = selectedRouteListingView.findViewById(R.id.btnchangestartaddress);
        btnchangeendaddress = selectedRouteListingView.findViewById(R.id.btnchangeendaddress);
        error = selectedRouteListingView.findViewById(R.id.errorselected);
        deleteRoute =  selectedRouteListingView.findViewById(R.id.btndeleteroute);

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
        btnchangestartaddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onModifyStartAddress(v);
            }
        });

        btnchangeendaddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onModifyEndAddress(v);
            }
        });
        deleteRoute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onDeleteRoute(v);
            }
        });


        loadRouteInfo();
        return selectedRouteListingView;
    }

    private void onDeleteRoute(View v) {

        //deleeeete
    }


    private void onModifyStartAddress(View v) {
        if ("".equals(txtnewstartaddess) ){
            error.setText("Please enter New Start Address");
            error.setVisibility(View.VISIBLE);
            Toast.makeText(((MainActivity)getActivity()), "Please enter New Start Address", Toast.LENGTH_LONG).show();

        }
    }
    private void onModifyEndAddress(View v) {
        if ("".equals(txtnewendaddess) ){
            error.setText("Please enter New End Address");
            error.setVisibility(View.VISIBLE);
            Toast.makeText(((MainActivity)getActivity()), "Please enter New End Address", Toast.LENGTH_LONG).show();

        }
    }


    private void loadRouteInfo() {


    }

}
