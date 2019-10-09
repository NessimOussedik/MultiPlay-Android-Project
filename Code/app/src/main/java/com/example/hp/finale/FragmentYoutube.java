package com.example.hp.finale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FragmentYoutube extends Fragment {



    ImageButton click;
    public static TextView data;
    public static EditText text;
    public static ListView listView;

    private static final String TAG = "FirstFragment";

    public static String[] listeIds= {"","","","","","","","","","","","","","","","","","","","","","","","",""};


    public static String[] tableauVideos = {"","","","","","","","","","","","","","","","","","","","","","","","",""};



    public FragmentYoutube() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View v = inflater.inflate(R.layout.fragment_youtube, container, false);


        listView = (ListView)v. findViewById(R.id.listeVideos);



        click = (ImageButton)v. findViewById(R.id.button);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData process = new fetchData();

               process.execute();


            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Log.i("appui", "Vous avez appuye sur l'item n° " + position+ "dont l'id est " + listeIds[position]);

                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                trans.replace(R.id.root_frame,  FragmentYoutubeVideo.newInstance(listeIds[position]));


                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();


            }
        });




        text = (EditText)v. findViewById(R.id.barre);


        return v;
    }



    public class fetchData extends AsyncTask<Void,Void,Void>
    {
        String data="";
        String dataParsed = "";
        String singleParsed="";


        // public static ListView listView;



        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q="+ FragmentYoutube.text.getText().toString().replaceAll(" ","+")+"&type=video&key=AIzaSyDgQlm8l3-MuteNnUP_g3iBsKvRD3ys26k") ;


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";



                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }


                JSONObject responseJSON= null;
                responseJSON = new JSONObject(data);

                JSONArray items = responseJSON.getJSONArray("items");


                for (int i = 0; i< 25; i++)
                {

                    tableauVideos[i]= (String)items.getJSONObject(i).getJSONObject("snippet").get("title");
                    Log.d("listeTitres", tableauVideos[i]);

                    if (i==24)
                    {
                        Log.d("resultatTitres", "La liste des titres a été affichée avec succès");
                    }

                }


                for (int i = 0; i< 25; i++)
                {
                    listeIds[i]= (String)items.getJSONObject(i).getJSONObject("id").get("videoId");
                    Log.d("lesids", listeIds[i]);
                    Log.d("listeIds", listeIds[i]);
                    if (i==24)
                    {
                        Log.d("resultatIds", "La liste des titres a été affichée avec succès");
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_expandable_list_item_1,
                    tableauVideos);

            listView.setAdapter(listViewAdapter);




        }

    }




}



