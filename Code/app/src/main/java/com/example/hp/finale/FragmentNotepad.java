package com.example.hp.finale;

import android.graphics.Typeface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;




public class FragmentNotepad extends Fragment {

    MultiAutoCompleteTextView texte;
    Button button_bold;
    Button button_normal;
    Button button_new;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_notepad, container, false);

        button_bold = (Button) v.findViewById(R.id.idBold);
        button_bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBold(v);
            }
        });
        button_normal = (Button) v.findViewById(R.id.idNormal);
        button_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNormal(v);
            }
        });
        button_new = (Button) v.findViewById(R.id.idNouveau);
        button_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNew(v);
            }
        });

        texte = (MultiAutoCompleteTextView) v.findViewById(R.id.idCorps);
        return v;

    }

    public void onClickBold(View view) {
        Typeface setfontstyle = Typeface.defaultFromStyle(Typeface.BOLD);
        texte.setTypeface(setfontstyle);
        Log.d("bold", "Vous avez mis le texte en gras");

    }

    public void onClickNormal(View view) {
        Typeface setfontstyle = Typeface.defaultFromStyle(Typeface.NORMAL);
        texte.setTypeface(setfontstyle);
        Log.d("bold", "Vous avez mis le texte en normal");

    }

    public void onClickNew(View view) {
        {
            texte.setText("");


            texte.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            Log.d("bold", "Vous avez créé un nouveau sticker");

        }

    }
}
