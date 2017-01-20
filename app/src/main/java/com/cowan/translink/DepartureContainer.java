package com.cowan.translink;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cowan on 1/19/2017.
 */

public class DepartureContainer extends ArrayAdapter<Departure> {
    ArrayList<DataSetObserver> dataSetObservers;

    ListView departureListView;

    public DepartureContainer(Context context, int resource) {
        super(context, resource);

    }
}

