package com.example.chavda.weatherapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Chavda on 6/3/15.
 */
public class WeatherFragment extends Fragment implements AdapterView.OnItemClickListener {

    String[] mTitles;
    ArrayList<list> photos;

    Cursor cursor;
    ListView lv;
    WeatherDataAdapter adapter;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_layout, container, false);
//        MainActivity activity = (MainActivity)this.getActivity();
//        photos = activity.getmPhotos();
//        mTitles = new String[photos.size()];
//
//        for(int i = 0; i< mTitles.length; i++){
//            mTitles[i] = "Min: " + photos.get(i).temp.get(0).min + "\n"
//          + "Max: " + photos.get(i).temp.get(0).max + "\n";
//        }
//
//        ListView lv =(ListView)view.findViewById(R.id.listView);
//        lv.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, mTitles));
//        lv.setOnItemClickListener(this);
//
//        TextView tv = (TextView)view.findViewById(R.id.City);
//        tv.setText(photos.get(1).city +"  "+ photos.get(1).country);
//
//        return view;

        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        MainActivity activity = (MainActivity)this.getActivity();

        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        cursor = dbHelper.getAllRows();

        //will change this later when we use a CursorLoader
        getActivity().startManagingCursor(cursor);

        adapter = new WeatherDataAdapter(getActivity(), cursor);

        lv =(ListView)view.findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        textView= (TextView)view.findViewById(R.id.txtName);
        cursor.moveToFirst();
                textView.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.NAME)) +
                        " " + cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.COUNTRY)));

        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getActivity(), mTitles[i], Toast.LENGTH_LONG).show();
//        String photoURL = photos.get(i).getPhotoURL(true);
//        PhotoFragment pf = new PhotoFragment();
//        Bundle args = new Bundle();
//        args.putString("URL", photoURL);
//        pf.setArguments(args);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.container, pf);
//        ft.addToBackStack("Image");
//        ft.commit();
    }


}
