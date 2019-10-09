package com.example.hp.finale;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    static final int NUM_ITEMS = 2;  //Nombre de slides du bloc du haut
    static final int NUM_ITEMS2 = 3; // Nombre de slides du bloc du bas

    ViewPager mPager;
    ViewPager mPager2;

    SlidePagerAdapter mPagerAdapter;
    SlidePagerAdapter2 mPagerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_youtube);

        //Bloc du haut
        mPager = (ViewPager) findViewById(R.id.vp_pager);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        //Bloc du bas
        mPager2 =  (ViewPager)findViewById(R.id.vp_pager2);
        mPagerAdapter2 = new SlidePagerAdapter2(getSupportFragmentManager());
        mPager2.setAdapter(mPagerAdapter2);



    }

    //Adapter permettant de slider
    public class SlidePagerAdapter extends FragmentPagerAdapter {
        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            //Le RootFragment() sert uniquement à peupler la première slide de notre ViewPager
            if (position == 0)
                return new RootFragment();
            else
                return new FragmentNotepad();
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

    public class SlidePagerAdapter2 extends FragmentPagerAdapter {
        public SlidePagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            if (position == 0)
                return new RootFragment2();

            else if (position == 1)
                return new FragmentInternet();
            else
                return new FragmentAgenda();
        }

        @Override
        public int getCount() {
            return NUM_ITEMS2;
        }
    }

}
