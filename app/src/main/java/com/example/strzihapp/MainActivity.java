package com.example.strzihapp;

import static io.reactivex.rxjava3.schedulers.Schedulers.start;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Dao;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements ItemFragment_note.OnRefreshListener {




    private static final String TAG = "StrizhViewModel";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ID = "id_note";
    private static final String TAG_CHECK = "check";
    private static final String TAG_DELETE = "delete";


    private StrizhViewModel strizhViewModel;


    private ArrayList<TaskModel> notes = new ArrayList<TaskModel>();



    public boolean isTabletDevice() {
        boolean tablet;
        int screenLayout = getResources().getConfiguration().screenLayout;
        if ((screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
            tablet = true;
        } else {
            tablet = false;
        }
        return tablet;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle", "onCreate invoked");



        strizhViewModel = new ViewModelProvider(this).get(StrizhViewModel.class);



        //лаб8 чтение
        strizhViewModel.getAllTasks().observe(this, new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
            ArrayList<TaskModel> temp = new ArrayList<>(taskModels);

            strizhViewModel.setNotesBank(temp);
            notes = strizhViewModel.getNotesBank();

            }
        });

    }


    public void getEdit(int id) {
        Intent i = new Intent(MainActivity.this, EditNoteActivity.class);
        i.putExtra(TAG_NAME,notes.get(id).getName()).putExtra(TAG_DESC,notes.get(id).getDescription()).putExtra(TAG_ID,id).putExtra(TAG_CHECK, notes.get(id).isCheck());


        startActivityForResult(i,1);
    }

    public int findUnicId(ArrayList<TaskModel> notes, int id) {
        int UnicId = 0;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                UnicId = id;
            }
        }
        return id;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //edit
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            boolean delete = data.getBooleanExtra(TAG_DELETE, false);
            int id = data.getIntExtra(TAG_ID, 0);
            //int id = findIndexOfNoteById(notes, tempId);
            ArrayList<TaskModel> curNotes = strizhViewModel.getNotesBank();




            if(delete)
            {
                //лаб8 удаление
                strizhViewModel.delete(curNotes.get(id));
            }else{
                String name = data.getStringExtra(TAG_NAME);
                String description = data.getStringExtra(TAG_DESC);

                boolean check = data.getBooleanExtra(TAG_CHECK, false);

                notes.get(id).setName(name);
                notes.get(id).setDescription(description);
                notes.get(id).setCheck(check);

                //лаб8 обновление
                strizhViewModel.update(curNotes.get(id));
            }



        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {


            String name = data.getStringExtra(TAG_NAME);
            String description = data.getStringExtra(TAG_DESC);

            notes.add(new TaskModel(name, description, false));

            //лаб8 добавление
            strizhViewModel.insert(notes.get(notes.size()-1));


        }
    }





    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart invoked");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume invoked");



//лаб7 запуск фрагмнта
        if(isTabletDevice())
        {

            fragmentOneLaptop fop = new fragmentOneLaptop();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_one, fop);
            ft.commit();
        }
        else {
            ItemFragment_note blankFragment = new ItemFragment_note();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_one, blankFragment);
            ft.commit();
        }

        insertData(this);

    }
    public void insertData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean hasRun = prefs.getBoolean("hasRun", false);
        if (!hasRun) {
            for (int i = 0; i<notes.size();i++) {

                strizhViewModel.insert(notes.get(i));
            }
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("hasRun", true);
            editor.apply();
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy invoked");
    }
    //лаб7 для фрагмента

    public ArrayList<TaskModel> getNotes() {

        return notes;
    }



    //лаб7 меню +
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(i,2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRefreshData() {

    }

}