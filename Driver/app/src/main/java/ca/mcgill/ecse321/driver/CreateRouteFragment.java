package ca.mcgill.ecse321.driver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class CreateRouteFragment extends Fragment {

    public CreateRouteFragment() {
        // Required empty public constructor
    }

    private LinearLayout parentLinearLayout;
    Button addRowBtn;
    Button removeRowBtn;
    Button createRouteBtn;
    private EditText newLocationCity;
    private EditText newLocationStreet;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mFirstName;
    private EditText mLastName;

    private int routeId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initialization
        ((MainActivity)getActivity()).setActionBarTitle("Create Route");

        //get id
        final View createRouteView = inflater.inflate(R.layout.fragment_create_route, null);
        addRowBtn = (Button) createRouteView.findViewById(R.id.btnaddlocation);
        removeRowBtn = (Button) createRouteView.findViewById(R.id.btnremovelocation);
        parentLinearLayout = (LinearLayout) createRouteView.findViewById(R.id.parent_linear_layout);
        createRouteBtn = (Button) createRouteView.findViewById(R.id.btncreateroute);

//        mUsername = (EditText) findViewById(R.id.txtusername);
//        mUsername = (EditText) findViewById(R.id.txtusername);
//        mUsername = (EditText) findViewById(R.id.txtusername);
//        mUsername = (EditText) findViewById(R.id.txtusername);
//        mUsername = (EditText) findViewById(R.id.txtusername);

        //button listeners
        addRowBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onAddNewLocationRow(v);
            }
        });
        removeRowBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onNewLocationDelete(v);
            }
        });

        createRouteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onCreateRoute(v);
                onCreateLocations(v);
            }
        });

        return createRouteView;
    }


    public void onAddNewLocationRow(View v) {
        LayoutInflater inflater = (LayoutInflater) ((MainActivity)getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.addlocationrow, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        Button remove = rowView.findViewById(R.id.btnremovelocation);
        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onNewLocationDelete(v);
            }
        });

    }

    public void onNewLocationDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }

    public void onCreateRoute(View v) {

//        //this create the starting location address
//        View view = parentLinearLayout.getChildAt(4);
//
//        String city = null;
//        String street = null;
//        newLocationCity = (EditText) view.findViewById(R.id.txtnewlocation);
//        newLocationStreet = (EditText) view.findViewById(R.id.txtlocationstreet);
//
//        if (newLocationCity != null && newLocationStreet != null) {
//            city = newLocationCity.getText().toString();
//            street = newLocationStreet.getText().toString();
//        }
//
//        String locationPathurl = "api/user/login/";
//
//        HttpUtils.get(pathURL, new RequestParams(), new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//
//            }
//
//            // For some reason it always fails, but the value we're looking for is stored in errorResponse
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
//
//            }
//
//            //This one catches the error, not the one above
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//
//            }
//        });
        RequestParams rp = new RequestParams();
        mUsername = (EditText) v.findViewById(R.id.txtnumberofseats);
        mFirstName = (EditText) v.findViewById(R.id.txtroutedate);
        mLastName = (EditText) v.findViewById(R.id.txtroutetime);

        // send post to get car id

        rp.add("role", "Driver");
        //send post to create route
        String pathURL = "api/user/login/";

        HttpUtils.get(pathURL, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

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

        //send post to create location

    }

    private void onCreateLocations(View v) {
        int count = parentLinearLayout.getChildCount();

        for (int i = 4; i < count-1; i++) {
            View view = parentLinearLayout.getChildAt(i);

            newLocationCity = (EditText) view.findViewById(R.id.txtnewlocation);
            newLocationCity = (EditText) view.findViewById(R.id.txtnewlocation);
            if (newLocationCity != null) {
                String location = newLocationCity.getText().toString();
            }
            //send post to create locations
        }
    }


}