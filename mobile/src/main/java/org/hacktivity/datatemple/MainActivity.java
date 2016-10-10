package org.hacktivity.datatemple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView dirListView;
    EditText et;
    ArrayAdapter adapter;
    ArrayList<String> dirList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dirListView = (ListView) findViewById(R.id.dirListView);
        et = (EditText) findViewById(R.id.dirEditText);

        dirList = new ArrayList<String>();


        dirListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // getAbsPath()
                et.setText( et.getText().toString() + "/" + dirList.get(position) );

                populateDirList(view);
            }

        });

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, dirList);

        dirListView.setAdapter(adapter);

    }


    public void populateDirList (View view) {

        File f;

        // NO INPUT.
        if (et.getText().toString().equals("")) {
            return;
        }

        f = new File(et.getText().toString());
        if (f == null) { return; }

        String dirContents[] = f.list();
        if (dirContents == null) {
            return;
        }

        adapter.clear();

        int i;
        for (i=0;i<dirContents.length;i++) {
            dirList.add(dirContents[i]);
            //Toast.makeText(this, dirList.get(i), Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();

    }

}
