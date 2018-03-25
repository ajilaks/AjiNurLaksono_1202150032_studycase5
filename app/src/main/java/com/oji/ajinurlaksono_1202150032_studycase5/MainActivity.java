package com.oji.ajinurlaksono_1202150032_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDbHelper;
    private List<todoList> todoLists = new ArrayList<>();
    private RecyclerView recyclerView;
    private todoAdapter mAdapter;
    int i=0;
    int[] id  = new int[20]; ;
    String[] nama = new String[20];
    String[] deskripsi;
    String[] prioritas;
    String warna;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new todoAdapter(this, todoLists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());       //menginisiasi adapter untuk recycleView
        recyclerView.setLayoutManager(mLayoutManager);      //menghubungkan adapter dan layout
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mDbHelper = new DatabaseHelper(this);
        prepareData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            /**
             * Method that defines the drag and drop functionality
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The SportsViewHolder that is being moved
             * @param target The SportsViewHolder that you are switching the original one with.
             * @return returns true if the item was moved, false otherwise
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                //Get the from and to position
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                //Swap the items and notify the adapter
                Collections.swap(todoLists, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Method that defines the swipe to dismiss functionality
             * @param viewHolder The viewholder being swiped
             * @param direction The direction it is swiped in
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                todoList todoList = todoLists.get(viewHolder.getAdapterPosition());
                Log.d("msg",todoList.getNama());
                String id = String.valueOf(todoList.getId());
                //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                //Remove the item from the dataset
                todoLists.remove(viewHolder.getAdapterPosition());
                mDbHelper.deleteData(todoList.getId());
                //Notify the adapter
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        //Attach the helper to the RecyclerView
        helper.attachToRecyclerView(recyclerView);

        //-------



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), tambahTodo.class);
                startActivity(i);
            }
        });

        SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(this);

        warna = Preference.getString("chosenColor","-1");

       // Toast.makeText(this, warna, Toast.LENGTH_SHORT).show();



    }


    private void prepareData() {
        Cursor data = mDbHelper.getData();
        todoLists.clear();
        while (data.moveToNext()){
           // id[0]=data.getInt(0);
            todoLists.add(new todoList(data.getInt(0),data.getString(1),data.getString(2),data.getString(3)));
        }
        //memasukkan beberapa menu ke dalam array objek


        //Clear the existing data (to avoid duplication)
        //Create the ArrayList of Sports objects containing the titles,
        // images and information about each sport
       // for(int o=0; i<nama.length; i++){
       //     todoLists.add(new todoList(nama[o],deskripsi[o], prioritas[o]));
       // }

        mAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
