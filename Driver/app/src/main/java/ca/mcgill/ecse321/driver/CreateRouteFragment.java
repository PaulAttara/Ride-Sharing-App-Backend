package ca.mcgill.ecse321.driver;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


public class CreateRouteFragment extends Fragment {

    public CreateRouteFragment() {
        // Required empty public constructor
    }

    private LinearLayout parentLinearLayout;
    Button addRowBtn;
    Button removeRowBtn;


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

        return createRouteView;
    }


    public void onAddNewLocationRow(View v) {
        LayoutInflater inflater = (LayoutInflater) ((MainActivity)getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.addlocationrow, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        Button remove = rowView.findViewById(R.id.newbtnremovelocation);
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

    public void setActionBarTitle(String title){
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(title);
    }


}
