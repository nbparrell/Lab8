package edu.temple.lab8;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

import static java.sql.Types.NULL;


public class MainActivity extends FragmentActivity implements URLBar.mActivityWebView{
    ArrayList list = new ArrayList();
    int currentIndex = 0;
    ViewPager pager;
    FragmentStatePagerAdapter adapter;
    int count = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL = getIntent().getData().toString();

        pager=(ViewPager)findViewById(R.id.fragmentWebViewPager);

        adapter=new FragmentStatePagerAdapter(
                getSupportFragmentManager()
        ){

            private Fragment mCurrentPrimaryItem = null;

            @Override
            public int getCount() {
                // This makes sure getItem doesn't use a position
                // that is out of bounds of our array of URLs
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                // Here is where all the magic of the adapter happens
                // As you can see, this is really simple.
                return webSiteFrag.newInstance(list.get(position).toString());
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                Fragment fragment = (Fragment)object;
                if (fragment != mCurrentPrimaryItem) {
                    if (mCurrentPrimaryItem != null) {
                        mCurrentPrimaryItem.setMenuVisibility(false);
                        mCurrentPrimaryItem.setUserVisibleHint(false);
                    }
                    if (fragment != null) {
                        fragment.setMenuVisibility(true);
                        fragment.setUserVisibleHint(true);
                    }
                    mCurrentPrimaryItem = fragment;
                    if(list.size() != 0) {
                        URLBar urlBar = URLBar.newInstance(list.get(position).toString());
                        getFragmentManager().beginTransaction().replace(R.id.fragmentURL, urlBar).commit();
                        currentIndex = position;
                    }
                    count++;
                }

            }
        };

        //Let the pager know which adapter it is supposed to use
        pager.setAdapter(adapter);

        if(URL.equals(NULL)) {
            URLBar urlBar = URLBar.newInstance("empty");
            getFragmentManager().beginTransaction().add(R.id.fragmentURL, urlBar).commit();
        }else{
            list.add(currentIndex, URL);
            pager.getAdapter().notifyDataSetChanged();
            URLBar urlBar = URLBar.newInstance(URL);
            getFragmentManager().beginTransaction().add(R.id.fragmentURL, urlBar).commit();
            webSiteFrag wSiteFrag = webSiteFrag.newInstance(URL);
            pager.setCurrentItem(currentIndex);
        }



        final Button btnBack = findViewById(R.id.btn_Back);
        final Button btnNew = findViewById(R.id.btn_New);
        final Button btnNext = findViewById(R.id.btn_Next);

        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex == 0){
                    Toast.makeText(view.getContext(),"No previous pages to view",Toast.LENGTH_LONG).show();
                }else{
                    currentIndex=currentIndex - 2;
                    pager.setCurrentItem(currentIndex);
                }

            }
        });

        btnNew.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = list.size();
                URLBar urlBar = URLBar.newInstance("empty");
                getFragmentManager().beginTransaction().replace(R.id.fragmentURL, urlBar).commit();
                webSiteFrag wSiteFrag = webSiteFrag.newInstance("empty");

            }
        });

        btnNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex == list.size()){
                    Toast.makeText(view.getContext(),"No more pages to view",Toast.LENGTH_LONG).show();
                }else{
                    currentIndex++;
                    pager.setCurrentItem(currentIndex);
                }

            }
        });

    }

    public void goClick(String URL){
        webSiteFrag webView = webSiteFrag.newInstance(URL);
        currentIndex = list.size();
        list.add(currentIndex, URL);
        pager.getAdapter().notifyDataSetChanged();
        //Toast.makeText(this,"CurrentIndex = "+Integer.toString(currentIndex),Toast.LENGTH_LONG).show();
        pager.setCurrentItem(currentIndex);

    }
}
