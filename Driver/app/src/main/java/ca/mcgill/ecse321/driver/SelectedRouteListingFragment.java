package ca.mcgill.ecse321.driver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


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

    public static String ID;
    TextView txtStartAddress;
    TextView txtEndAddress;
    Button btnBackResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View selectedRouteListingView = inflater.inflate(R.layout.fragment_selected_route_listing, null);
        txtStartAddress = selectedRouteListingView.findViewById(R.id.txtselectedstartaddess);
        txtEndAddress = selectedRouteListingView.findViewById(R.id.txtselectedendaddess);
        btnBackResults = selectedRouteListingView.findViewById(R.id.btnbackresults);



        btnBackResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fMain, new RouteListingsFragment());
                ft.commit();
            }
        });

        loadRouteInfo();
        return selectedRouteListingView;
    }

    private void loadRouteInfo() {


    }

}
