package ca.mcgill.ecse321.passenger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

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
    JSONArray user;
    JSONObject hay;
    /*private static final String TAG_PROFILE = "user";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_FIRSTNAME = "first_name";
    private static final String TAG_MIDDLENAME = "middle_initial";
    private static final String TAG_LASTNAME = "last_name";
    public static String ID; */
    TextView txtStartAddress;
    TextView txtEndAddress;
    Button btnBackResults;
    private ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View selectedListingView = inflater.inflate(R.layout.fragment_selected_listing, null);
        txtStartAddress = selectedListingView.findViewById(R.id.txtselectedstartaddess);
        txtEndAddress = selectedListingView.findViewById(R.id.txtselectedendaddess);
        btnBackResults = selectedListingView.findViewById(R.id.btnbackresults);
        //new LoadProfile().execute();
        btnBackResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToSearchResults(v);
            }
        });

        populateSelectedListingPage();
        return selectedListingView;
    }

    /*class LoadProfile extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Loading page ...");
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            // Building Parameters
            String json = null;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("start", "end"));

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(PROFILE_URL);
                httppost.setEntity(new UrlEncodedFormEntity(params));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity resEntity = response.getEntity();
                json = EntityUtils.toString(resEntity);

                Log.i("Profile JSON: ", json.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            String json = null;
         //   super.onPostExecute(json);
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            try
            {
                hay = new JSONObject(json);
                JSONArray start_address = hay.getJSONArray("start address");
                JSONObject jb= user.getJSONObject(0);
                String end_address = jb.getString("end address");

                // displaying all data in textview

                txtStartAddress.setText("Firstname: " + start_address);
                txtEndAddress.setText("Middle Name: " + end_address);
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    } */
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
        String json = null;
        //set the fields with values using ID
        String pathURL = "api/route/getRoutes/" + txtStartAddress ;
        String pathURL = "api/route/getRoutes/" + txtStartAddress ;
        HttpUtils.get(pathURL, new RequestParams(), new JsonHttpResponseHandler() {

        try
            {
                hay = new JSONObject(json);
                JSONArray start_address = hay.getJSONArray("start address");
                JSONObject jb= user.getJSONObject(0);
                String end_address = jb.getString("end address");

                // displaying all data in textview

                txtStartAddress.setText("Start: " + start_address);
                txtEndAddress.setText("End: " + end_address);
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        //example
        //txtStartAddress.setText("CANADA");
        //txtEndAddress.setText("USA");
    }

}
