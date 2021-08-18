package com.example.visacharity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        User currUser = (User) getApplication();
        TextView pts = (TextView) findViewById(R.id.currPoints);

        if (currUser.getTotalPoints() == null){
            Integer random = (int)(Math.random()*1000);
            currUser.setTotalPoints(random);
        }

        pts.setText(currUser.getTotalPoints().toString());



        ListView charityList = (ListView) findViewById(R.id.charityList);
        ArrayList<String> charities = new ArrayList<>();
        for (int i = 1; i < 6; i++){
            charities.add("Charity " + i);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, (List) charities);

        charityList.setAdapter(arrayAdapter);

        charityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                androidx.fragment.app.Fragment thisCharity = new FirstFragment();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Charity", i);
//                thisCharity.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragme, new FirstFragment()).commit();
                launchActivity(i+1);


            }
        });


    }

    private void launchActivity(int pos){
        Intent intent = new Intent(this, Donate.class);
        intent.putExtra("Charity Num", pos);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}