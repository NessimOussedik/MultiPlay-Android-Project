package com.example.hp.finale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class FragmentMessagerie extends Fragment {

    public static String numero;
    public TextView texte;
    public Button bouton;
    public  EditText champMessage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_messagerie, container, false);

        texte = (TextView) v.findViewById(R.id.idTexte);
        bouton = (Button) v.findViewById(R.id.bouton);
        champMessage = (EditText) v.findViewById(R.id.champMessage);



        Log.d("destinataire", "Vous ecrivez à " + getArguments().getString("nom") + "de nom " + getArguments().getString("number"));
        texte.setText("Vous écrivez à " + getArguments().getString("nom"));
        numero = getArguments().getString("number");


        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(getArguments().getString("number"), null, champMessage.getText().toString(), null, null);
                champMessage.setText("");

            }
        });


        return v;


    }


    public static FragmentMessagerie newInstance(String nom, String number) {
        FragmentMessagerie fragMessagerie = new FragmentMessagerie();




        Bundle args = new Bundle();

        args.putString("number", number);
        args.putString("nom", nom);
        fragMessagerie.setArguments(args);

        return fragMessagerie;
    }


}