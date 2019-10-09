package com.example.hp.finale;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class FragmentContacts extends Fragment {

    public static ContentResolver contentResolver;
    public static ListView listViewContacts;
    public static ArrayList<String> listeNomsContacts = new ArrayList<String>();
    public static ArrayList<String> listeNumerosContacts = new ArrayList<String>();

    String[] liste;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_contacts, container, false);


        listViewContacts = (ListView) v.findViewById(R.id.lvContacts);
        listeNomsContacts = new ArrayList<String>();
        listeNumerosContacts = new ArrayList<String>();

        getAllContacts();


        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listeNomsContacts);

        listViewContacts.setAdapter(arrayAdapter);


        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                String nomContact = listeNomsContacts.get(position);
                String numeroContact = listeNumerosContacts.get(position);
                Toast.makeText(getContext(), "Nom sélectionné : " + nomContact + numeroContact, Toast.LENGTH_LONG).show();

                Log.d("appuiContact", "Vous avez appuyé sur le contact dont le nom est " + nomContact + "  et le numéro est "+ numeroContact);
                //On remplace le fragment au clic par le nouveau fragment
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                trans.replace(R.id.root_frame2, FragmentMessagerie.newInstance(nomContact, numeroContact));

                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        return v;
    }





    private void getAllContacts() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id},
                            null);
                    if (phoneCursor != null) {
                        if (phoneCursor.moveToNext()) {
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Log.d("listeContacts", name + " : " + phoneNumber
                            );
                            //At here You can add phoneNUmber and Name to you listView ,ModelClass,Recyclerview
                            listeNomsContacts.add(name);
                            listeNumerosContacts.add(phoneNumber);
                            phoneCursor.close();
                        }


                    }
                }
            }
        }


    }
}
