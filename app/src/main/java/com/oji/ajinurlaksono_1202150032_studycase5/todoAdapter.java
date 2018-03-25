package com.oji.ajinurlaksono_1202150032_studycase5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by OJI on 23/03/2018.
 */

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    private List<todoList> todoLists;
    DatabaseHelper mDbHelper;
    int id;
    MainActivity m = new MainActivity();
    String warna;

    public todoAdapter(Context context, List<todoList> todoList) {
        mInflater = LayoutInflater.from(context); //inisiasi inflater
        this.todoLists = todoList;} //arraylist
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.todo_list, parent, false);
        //method untuk menginflate dengan class lainnya
        return new MyViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        todoList todoList = this.todoLists.get(position); //get value ke textView
        id= todoList.getId();
        holder.deskripsi.setText(todoList.getDekripsi());            //get value ke textView
        holder.nama.setText(todoList.getNama()); //get value ke textView
        holder.prioritas.setText(todoList.getPrioritas()); //get value ke textView
switch (warna){ //setting warna ketika menu dipilih
    case "Merah":holder.bgColor.setBackgroundResource(R.color.colorAccent);break;
    case "Cyan":holder.bgColor.setBackgroundColor(Color.CYAN);break;
    case "Hijau":holder.bgColor.setBackgroundColor(Color.GREEN);break;
    case "Putih":holder.bgColor.setBackgroundColor(Color.WHITE);break;

}


    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // class MyCiewHolder
        public TextView nama, deskripsi, prioritas;
        ConstraintLayout bgColor;


        public MyViewHolder(View view) {
            super(view);//menginisiasi variable2 attribute
            nama = (TextView) view.findViewById(R.id.nama); //refrencing variable
            deskripsi = (TextView) view.findViewById(R.id.deskripsi); //refrencing variable
            prioritas = (TextView) view.findViewById(R.id.prioritas); //refrencing variable
            bgColor = (ConstraintLayout) view.findViewById(R.id.layout_background); //refrencing variable
            SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(view.getContext()); //set shared preferences
            warna = Preference.getString("chosenColor","-1"); //set shared preferences ke variable warna
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {               // ketika di klik salah satu menu
            int mPosition = getLayoutPosition();
// Use that to access the affected item in mWordList.
            String element = todoLists.get(mPosition).toString();
            Toast.makeText(view.getContext(), nama.getText(), Toast.LENGTH_SHORT).show();
            //Intent i = new Intent(view.getContext(), Details.class);
           // int gambar = menuList.get(mPosition).getGambar();
          //  String komposisi = menuList.get(mPosition).getKomposisi();
           // i.putExtra("gambar", gambar);      // put extra ke intent berikutnya
           // i.putExtra("nama", nama.getText()); // put extra ke intent berikutnya
          //  i.putExtra("details", details.getText()); // put extra ke intent berikutnya
          //  i.putExtra("komposisi", komposisi); // put extra ke intent berikutnya
            //view.getContext().startActivity(i); //jalankan activity berikutnya

        }
    }
}
