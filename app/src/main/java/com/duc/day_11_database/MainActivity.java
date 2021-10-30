package com.duc.day_11_database;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.duc.day_11_database.daos.StudentAdapter;
import com.duc.day_11_database.daos.StudentDAO;
import com.duc.day_11_database.databinding.ActivityMainBinding;
import com.duc.day_11_database.dtos.StudentDTO;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitle;
    private ListView listViewStudent;
    private StudentAdapter adapter;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        R.id.txt
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void clickToLoadFromRaw(MenuItem item) {

        txtTitle = findViewById(R.id.txtTitle);

        listViewStudent = findViewById(R.id.listViewStudent);
        adapter = new StudentAdapter();
        try (InputStream is = getResources().openRawResource(R.raw.data)) {
            txtTitle.setText("List Student From Raw");
            StudentDAO dao = new StudentDAO();
            List<StudentDTO> listStudent = dao.loadDataFromRaw(is);
            adapter.setStudents(listStudent);
            listViewStudent.setAdapter(adapter);
            listViewStudent.setOnItemClickListener((parent, view, position, id) -> {
                StudentDTO dto = (StudentDTO) listViewStudent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, dto.toString(), Toast.LENGTH_SHORT).show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}