package ca.mcgill.ecse321.passenger;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<SearchTemplate> implements View.OnClickListener {
    private ArrayList<SearchTemplate> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtDate;
        TextView txtRouteId;
        TextView txtNumSeats;
        //Button modifyRoute;
        //Button deleteRoute;
        ImageView info;
    }



    public SearchAdapter(ArrayList<SearchTemplate> data, Context context) {
        super(context, R.layout.searchlistingrow, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        SearchTemplate dataModel=(SearchTemplate)object;





//        switch (v.getId())
//        {
//
//            case R.id.item_info:
//
//                Snackbar.make(v, "Start Address " +dataModel.getStartAddress(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//
//                break;
//
//
//        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchTemplate SearchTemplate = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.searchlistingrow, parent, false);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            viewHolder.txtRouteId = (TextView) convertView.findViewById(R.id.txtRouteId);
            viewHolder.txtNumSeats = (TextView) convertView.findViewById(R.id.txtNumSeats);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.txtDate.setText(SearchTemplate.getDate());
        viewHolder.txtRouteId.setText(SearchTemplate.getRouteID());
        viewHolder.txtNumSeats.setText(SearchTemplate.getNumSeats());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

}
