package com.example.david.example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.parse.Parse;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //es parecido a un ListView porque necesita de un Adapter
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Retorna el fragment que queramos
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new NewsFragment();
            }else{
                return new MyMapFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "NEWS";
            }else{
                return "MAP";
            }
        }

        /**
         * Numero de paginas del Adapter
         * @return
         */
        @Override
        public int getCount() {
            return 2;
        }


    }
}