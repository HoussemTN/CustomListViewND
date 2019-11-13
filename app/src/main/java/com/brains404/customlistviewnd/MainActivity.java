package com.brains404.customlistviewnd;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Drawer Navigation
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // Custom List
        ListView listViewProducts = findViewById(R.id.listView);
        final List<HashMap<String,String>> productList = new ArrayList<>();
        // prod 1
        HashMap<String,String> prod1 = new HashMap<String,String>() ;
        prod1.put("titre","Word");
        prod1.put("description","Traitement de texte");
        prod1.put("image",String.valueOf(R.drawable.word));
        productList.add(prod1);
        //prod 2
        HashMap<String,String> prod2 = new HashMap<String,String>() ;
        prod2.put("titre","Excel");
        prod2.put("description","Tableur");
        prod2.put("image",String.valueOf(R.drawable.excel));
        productList.add(prod2);
        //prod 3
        HashMap<String,String> prod3 = new HashMap<String,String>() ;
        prod3.put("titre","PowerPoint");
        prod3.put("description","Logiciel de présentation");
        prod3.put("image",String.valueOf(R.drawable.powerpoint));
        productList.add(prod3);
        //prod 4
        HashMap<String,String> prod4 = new HashMap<String,String>() ;
        prod4.put("titre","Outlook");
        prod4.put("description","Client de courrier électronique");
        prod4.put("image",String.valueOf(R.drawable.outlook));
        productList.add(prod4);
        // my Adapter
        SimpleAdapter myAdapter = new SimpleAdapter(this.getBaseContext(),productList,R.layout.list_products,new String[]  {"titre","description","image"},new int [] {R.id.textView3,R.id.textView2,R.id.imageView2});
        listViewProducts.setAdapter(myAdapter);
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(),productList.get(position).get("titre"),Toast.LENGTH_LONG).show();
            }
        });
        listViewProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Sélection Item")
                        .setMessage("Votre choix : "+productList.get(position).get("titre"))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
