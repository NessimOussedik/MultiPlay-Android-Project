package com.example.hp.finale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


public class FragmentAgenda extends Fragment {

    CalendarView calendrier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_static_agenda, container, false);

        calendrier = (CalendarView)view .findViewById(R.id.idCalendrier);
        return view;
    }

}
