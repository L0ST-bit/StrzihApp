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


public class MainActivity extends AppCompatActivity implements ItemFragment_note.OnRefreshListener {




    private static final String TAG = "StrizhViewModel";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ID = "id_note";
    private static final String TAG_CHECK = "check";
    private static final String TAG_DELETE = "delete";

    private EditText note_name, note_description;
    private Button add_button, edit_button, show_last_button;
    private ImageButton next, previous;
    private int idNote;
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
        strizhViewModel.getData().observe(this, data -> {
             notes = data;
        });
//        strizhViewModel.getDataInt().observe(this, index -> {
//            idNote = index;
//        });
//        Log.d(TAG, "Модель получена");


//        strizhViewModel.getAllTasksAsync().thenAcceptAsync(tasks -> {
//
//
//            ArrayList<TaskModel> temp = new ArrayList<>(tasks);
//
//            strizhViewModel.setNotesBank(temp);
//            runOnUiThread(() -> {
//                // Обновление адаптера RecyclerView здесь
//            });
//        }, ContextCompat.getMainExecutor(this));

        strizhViewModel.getAllTasks().observe(this, new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
            ArrayList<TaskModel> temp = new ArrayList<>(taskModels);

            strizhViewModel.setNotesBank(temp);
            //notes = new ArrayList<TaskModel>(taskModels);
            }
        });












        note_name = (EditText) findViewById(R.id.note_name_edit);
        note_description = (EditText)
                findViewById(R.id.note_description_edit);
        add_button = (Button) findViewById(R.id.add_button);
        edit_button = (Button) findViewById(R.id.edit_button);
        show_last_button = (Button) findViewById(R.id.show_last_button);
        previous = (ImageButton) findViewById(R.id.previous);
        next = (ImageButton) findViewById(R.id.next);


//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
//                startActivityForResult(i,2);
//            }
//        });
//        show_last_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                idNote = notes.size()-1;
//                note_name.setText(notes.get(idNote).getName());
//                note_description.setText(notes.get(idNote).getDescription());
//                strizhViewModel.showLast();
//
//            }
//        });

//        edit_button.setOnClickListener(editButton);

//        edit_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent i = new Intent(MainActivity.this, EditNoteActivity.class);
//                i.putExtra(TAG_NAME,notes.get(idNote).getName()).putExtra(TAG_DESC,notes.get(idNote).getDescription()).putExtra(TAG_ID,idNote).putExtra(TAG_CHECK, notes.get(idNote).isCheck());
//
//
//                startActivityForResult(i,1);
//
//
//
//            }
//        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int idTemp = idNote;
//                if(idTemp++< notes.size()-1)
//                {
//                    strizhViewModel.moveToNext();
//                    //idNote++;
//                    note_name.setText(notes.get(idNote).getName());
//                    note_description.setText(notes.get(idNote).getDescription());
//                }
//            }
//        });

//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int idTemp = idNote;
//                if(idTemp-- > 0)
//                {
//                    strizhViewModel.moveToPrevious();
//                    //idNote--;
//                    note_name.setText(notes.get(idNote).getName());
//                    note_description.setText(notes.get(idNote).getDescription());
//                }
//            }
//        });





    }

//    private View.OnClickListener editButton = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v){
//            getEdit(idNote);
//        }
//
//    };

    public void getEdit(int id) {
        Intent i = new Intent(MainActivity.this, EditNoteActivity.class);
        i.putExtra(TAG_NAME,notes.get(id).getName()).putExtra(TAG_DESC,notes.get(id).getDescription()).putExtra(TAG_ID,id).putExtra(TAG_CHECK, notes.get(id).isCheck());


        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //edit
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            boolean delete = data.getBooleanExtra(TAG_DELETE, false);
            int id = data.getIntExtra(TAG_ID, 1)-1;


            if(delete)
            {
                //strizhViewModel.delete(notes.get(id));
            }else{
                String name = data.getStringExtra(TAG_NAME);
                String description = data.getStringExtra(TAG_DESC);

                boolean check = data.getBooleanExtra(TAG_CHECK, false);

                notes.get(id).setName(name);
                notes.get(id).setDescription(description);
                notes.get(id).setCheck(check);
                note_name.setText(name);
                note_description.setText(description);

                strizhViewModel.update(notes.get(id));
            }



        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {


            String name = data.getStringExtra(TAG_NAME);
            String description = data.getStringExtra(TAG_DESC);

            notes.add(new TaskModel(notes.size()+1, name, description, false));
//            strizhViewModel.showLast();
//            note_name.setText(notes.get(notes.size()-1).getName());
//            note_description.setText(notes.get(notes.size()-1).getDescription());

            //лаб8
//            Dao_DB noteDao = DB_notes.getDatabase(this).notesDao();
//            strizhViewModel.insertNote(notes.get(notes.size()-1), noteDao);
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
        //insertData(this);

        note_name.setText(notes.get(0).getName());
        note_description.setText(notes.get(0).getDescription());

        strizhViewModel.getData().observe(this, data -> {
            notes = data;
        });
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
                //Dao_DB noteDao = DB_notes.getDatabase(this).notesDao();
                //strizhViewModel.insertNote(notes.get(i), noteDao);
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

//    public void startFragment(int id){
//        Bundle args = new Bundle();
//        args.putString(TAG_NAME, notes.get(id).getName());
//        args.putString(TAG_DESC, notes.get(id).getDescription());
//        args.putBoolean(TAG_CHECK, notes.get(id).isCheck());
//
//        BlankFragment_frame_two blankFragment1 = new BlankFragment_frame_two();
//        blankFragment1.setArguments(args);
//
//        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//        ft2.replace(R.id.frame_two, blankFragment1);
//        ft2.commit();
//    }

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