package com.example.chavda.weatherapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chavda on 6/3/15.
 */
public class WeatherDataAdapter extends CursorAdapter {

    public WeatherDataAdapter(Context c, Cursor cursor){
        super(c, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.text, parent, false);
    }
static int i =0;
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView title = (TextView) view.findViewById(R.id.Pressure);
        TextView description = (TextView) view.findViewById(R.id.Description);
        TextView cloudyness = (TextView) view.findViewById(R.id.Cloudyness);
        TextView humidity = (TextView) view.findViewById(R.id.Humidity);
        TextView currenttemp = (TextView) view.findViewById(R.id.CurrentTemp);
        TextView day = (TextView) view.findViewById(R.id.Day);

        ImageView imageView = (ImageView)view.findViewById(R.id.icon);
        //imageView.setImageResource(R.drawable.cloudy);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.NAME));

        String titleText = "Pressure: "+cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.PRESSURE)) +" hpa";
        String strdescription ="Description: "+cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.DESCRIPTION));
        String strcloudyness ="Cloudyness: "+cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.CLOUDS)) + "%";
        String strhumidity ="Humidity: "+ cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.HUMIDITY)) + "%";
        String strCurrentTemp ="Day : "+ (cursor.getDouble(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.DAY)) - 273) ;

        title.setText(titleText);
        description.setText(strdescription);
        cloudyness.setText(strcloudyness);
        humidity.setText(strhumidity);
        currenttemp.setText(strCurrentTemp.toString().substring(0,8)+ " °C");
        String strMain =cursor.getString(cursor.getColumnIndexOrThrow(Contract.WeatherEntry.MAIN));


        Calendar calendar = Calendar.getInstance();
        int daye = calendar.get(Calendar.DAY_OF_MONTH);
        Date today = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd");
        if(i==0)
          day.setText(df.format(today));
        else {

         calendar.add(Calendar.DATE, i);
            Date next = calendar.getTime();
            day.setText(df.format(next));
        }
        switch (strMain.toLowerCase())
        {
            case "clouds":
                imageView.setImageResource(R.drawable.cloudy);
                break;
            case "rain":
                imageView.setImageResource(R.drawable.rain);
            case "clear":
                imageView.setImageResource(R.drawable.clear);
                break;
        }

        i++;
    }
}

