////sandy goreraza zimbabwe

package com.example.sandygorerazagad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sandygorerazagad.adapter.ViewPagerAdapter;
import com.example.sandygorerazagad.fragment.Fragment1;
import com.example.sandygorerazagad.fragment.Fragment2;
import com.example.sandygorerazagad.interfaces.NetworkResponseListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements NetworkResponseListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private MenuItem prevItem;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LoadDataTopLearnersTask loadDataTask=new LoadDataTopLearnersTask(MainActivity.this);
//        loadDataTask.execute();

        final BottomNavigationView navigationMenu=findViewById(R.id.navigation);
        navigationMenu.setOnNavigationItemSelectedListener(MainActivity.this);
        viewPager=findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevItem!=null) {
                    prevItem.setChecked(false);
                }
                navigationMenu.getMenu().getItem(position).setChecked(true);
                prevItem=navigationMenu.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Fragment1());
        viewPagerAdapter.addTitle("Learning Leaders");
        viewPagerAdapter.addFragment(new Fragment2());
        viewPagerAdapter.addTitle("Skills IQ Leaders");
        viewPager.setAdapter(viewPagerAdapter);


    }

    @Override
    public void SuccessData(String data) {
        Log.d("Response : ",data);
    }

    @Override
    public void FailedData() {
        Toast.makeText(MainActivity.this, "Failed Data", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.home){
            viewPager.setCurrentItem(0);
        }
        else {
            viewPager.setCurrentItem(1);
        }
        return false;

    }

    public void ProjectSubmit(View view) {

        Intent intent = new Intent(MainActivity.this, Google_Form_Activity.class);
        startActivity(intent);



    }
}