package edu.student.android.chatappvolley.features;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.student.android.chatappvolley.R;

import static android.R.attr.resource;

/**
 * Created by Gaurav on 07-04-2017.
 */

class CarpoolDetailsAdapter extends ArrayAdapter<Carpool> {
    public CarpoolDetailsAdapter(Context context, ArrayList<Carpool> resource) {
        super(context,0,resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_view_your_carpool, parent, false);
        }
        Carpool carpool = getItem(position);
        TextView carpoolDate=(TextView) listItemView.findViewById(R.id.date);
        carpoolDate.setText(carpool.getDate());
        TextView sloc=(TextView) listItemView.findViewById(R.id.sourceLocation);
        sloc.setText(carpool.getSlocation());
        TextView dloc=(TextView) listItemView.findViewById(R.id.destinationLocation);
        dloc.setText(carpool.getDlocation());


        return listItemView;
    }
}
