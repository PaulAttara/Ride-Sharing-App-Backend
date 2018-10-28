package ca.mcgill.ecse321.driver;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RouteAdapter extends ArrayAdapter<RouteTemplate> implements View.OnClickListener {
    private ArrayList<RouteTemplate> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtStartAddress;
        TextView txtEndAddress;
        //Button modifyRoute;
        //Button deleteRoute;
        ImageView info;
    }



    public RouteAdapter(ArrayList<RouteTemplate> data, Context context) {
        super(context, R.layout.routelistingrow, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        RouteTemplate dataModel=(RouteTemplate)object;




        switch (v.getId())
        {

            case R.id.item_info:

                Snackbar.make(v, "Start Address " +dataModel.getStartAddress(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();

                break;


        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RouteTemplate routeTemplate = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.routelistingrow, parent, false);
            viewHolder.txtStartAddress = (TextView) convertView.findViewById(R.id.txtstartaddress);
            viewHolder.txtEndAddress = (TextView) convertView.findViewById(R.id.txtendaddress);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;


        viewHolder.txtStartAddress.setText(routeTemplate.getStartAddress());
        viewHolder.txtEndAddress.setText(routeTemplate.getEndAddress());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

}
