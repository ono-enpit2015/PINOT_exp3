package com.example.student11.pinot_exp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by student11 on 2016/06/24.
 */
public class AnketoActivity extends Activity {
    ArrayList list;
    HashMap map = new HashMap();;
    SparseBooleanArray checkedItemPositions;
    ListView listView;
    //final String LOGDIR = "/sdcard/all.txt";
    //final String LOGDIR2 = "/sdcard/anketo.txt";
    String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/all.txt";
    String LOGDIR2 = Environment.getExternalStorageDirectory().getPath()+"/data/anketo.txt";
    File ALL = new File(LOGDIR);
    File ANK = new File(LOGDIR2);
    String line;
    String headline;
    String displaycount;
    String viewcount;
    String touch;
    Boolean interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anketo);

        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("LIST");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice, list);
        ListView lv = (ListView)findViewById(R.id.listView);
        //ListViewにアダプタ登録
        lv.setAdapter(adapter);

        if(ANK.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(ANK));
                int k = 0;
                while ((line = br.readLine()) != null) {
                    StringTokenizer token = new StringTokenizer(line, "\t");
                    headline = token.nextToken();
                    displaycount = token.nextToken();
                    viewcount = token.nextToken();
                    touch = token.nextToken();
                    interest = Boolean.valueOf(token.nextToken());
                    map.put(k, interest);
                    lv.setItemChecked(k, interest);
                    k++;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            for (int i = 0; i < list.size(); i++) {        //記事数は動的に変更できるようにする
                map.put(i, false);
            }
            try {
                if (ANK.createNewFile()) {
                    //Toast.makeText(MainActivity.this, "ファイルの作成に成功", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(MainActivity.this, "ファイルの作成に失敗", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                //Toast.makeText(MainActivity.this, "例外が発生", Toast.LENGTH_SHORT).show();
                System.out.println(e);
            }
        }

        //アイテムがクリックされたときの処理
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //クリックされたものを取得
                /*String get_parent = (String) parent.getClass().getSimpleName();
                String get_position = String.valueOf(position);
                String get_id = String.valueOf(id);*/
                listView = (ListView)parent;
                checkedItemPositions = listView.getCheckedItemPositions();
                //Log.d("", String.format("position:%d checked:%b", position, checkedItemPositions.get(position)));
                map.put(position,checkedItemPositions.get(position));

            }
        });

        // クリックイベントを取得したいボタン
        Button finishButton = (Button)findViewById(R.id.finishButton);
        // ボタンに OnClickListener インターフェースを実装する
        finishButton.setOnClickListener(finishButtonOnClickListener);

    }

    private  View.OnClickListener finishButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "download", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent();//this,TestActivity.class
                intent.setClassName("com.example.student11.pinot_exp3", "com.example.student11.pinot_exp3.MainActivity");
                intent.putExtra("MAP", map);
                startActivity(intent);*/
            try {
                BufferedReader br = new BufferedReader(new FileReader(ALL));
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(ANK));
                    int i = 0;
                    while((line = br.readLine()) != null){
                        bw.write(line + "\t" + map.get(i));
                        bw.newLine();
                        i++;
                    }
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(AnketoActivity.this,MainActivity.class);
            startActivity(intent);
        }
    };

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
