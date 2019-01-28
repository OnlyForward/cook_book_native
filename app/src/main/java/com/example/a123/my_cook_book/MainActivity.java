package com.example.a123.my_cook_book;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //AppDbHelper appDbHelper = new AppDbHelper(TakeDb.getDbOpenHelper());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateReceipt.class);
                startActivity(intent);
            }
        });

        ReceiptsAdapter adapter = new ReceiptsAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView = findViewById(R.id.recycler_list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private class ReceiptsHolder extends RecyclerView.ViewHolder {

        public ReceiptsHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(int position) {

        }
    }

    private class ReceiptsAdapter extends RecyclerView.Adapter<ReceiptsHolder> {

        @NonNull
        @Override
        public ReceiptsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ReceiptsHolder receiptsHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
