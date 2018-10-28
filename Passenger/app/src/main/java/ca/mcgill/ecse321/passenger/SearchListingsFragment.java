package ca.mcgill.ecse321.passenger;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListingsFragment extends Fragment {


    public SearchListingsFragment() {
        // Required empty public constructor
    }

    Spinner searchTypeSpinner;
    EditText searchLocation;
    ImageButton btnSearch;

    ArrayList<SearchTemplate> dataModels;
    ListView listView;
    private static SearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Search Listings");
        final View searchListingsView = inflater.inflate(R.layout.fragment_search_listings, null);
        searchTypeSpinner = searchListingsView.findViewById(R.id.searchtypespinner);
        searchLocation = searchListingsView.findViewById(R.id.txtsearch);
        btnSearch = searchListingsView.findViewById(R.id.btnsearchlocation);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLocation(v);
            }
        });
        listView = (ListView) searchListingsView.findViewById(R.id.searchlistingslistview);

        loadSpinner();



        return searchListingsView;
    }

    private void getSearchListings() {
        dataModels = new ArrayList<>();

        dataModels.add(new SearchTemplate("Sauvignon", "McGill"));
        dataModels.add(new SearchTemplate("Canada", "Egypt"));
        dataModels.add(new SearchTemplate("Toronto", "Ottawa"));
        dataModels.add(new SearchTemplate("Sauvignon", "McGill"));
        dataModels.add(new SearchTemplate("Canada", "Egypt"));
        dataModels.add(new SearchTemplate("Toronto", "Ottawa"));
        dataModels.add(new SearchTemplate("Sauvignon", "McGill"));
        dataModels.add(new SearchTemplate("Canada", "Egypt"));
        dataModels.add(new SearchTemplate("Toronto", "Ottawa"));
        dataModels.add(new SearchTemplate("Sauvignon", "McGill"));
        dataModels.add(new SearchTemplate("Canada", "Egypt"));
        dataModels.add(new SearchTemplate("Toronto", "Ottawa"));

        //as a test to test the search, delete all non occurences of the text in the search bar once search button is clicked
//        for(SearchTemplate location : dataModels){
//
//            if(location.endAddress.equals(searchLocation)){
//                continue;
//            }
//            else{
//                dataModels.remove(location);
//            }
//        }
        //dataModels.removeAll(Collections.singleton(searchLocation));

        adapter = new SearchAdapter(dataModels, ((MainActivity)getActivity()).getApplicationContext());


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SearchTemplate dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getStartAddress()+"\n"+dataModel.getEndAddress(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    private void searchLocation(View v) {
        getSearchListings();
    }

    private void loadSpinner() {
        final List<String> list = new ArrayList<String>();
        list.add("Price");
        list.add("Location");
        list.add("Car Type");
        list.add("Date");
        list.add("Time");

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(listAdapter);
        searchTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText( ((MainActivity) getActivity()), list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

    }

}
