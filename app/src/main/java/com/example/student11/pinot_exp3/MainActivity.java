package com.example.student11.pinot_exp3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    //final String LOGDIR = "/sdcard/all.txt";
    //final String LOGDIR2 = "/sdcard/anketo.txt";
    String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/all.txt";
    File ALL = new File(LOGDIR);
    String LOGDIR2 = Environment.getExternalStorageDirectory().getPath()+"/data/anketo.txt";
    File ANK = new File(LOGDIR2);
    ArrayList list = new ArrayList();
    HashMap map = new HashMap();
    String line;
    String headline;
    int displaycount;
    int tapinfo;
    int viewcount;
    boolean interest;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    TextView tv10;
    TextView tv11;
    TextView tv12;
    TextView tv13;
    TextView tv14;
    TextView tv15;
    TextView tv16;
    TextView alltv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // クリックイベントを取得したいボタン
        Button downloadButton = (Button)findViewById(R.id.anketoButton);
        // ボタンに OnClickListener インターフェースを実装する
        downloadButton.setOnClickListener(anketoButtonOnClickListener);

        Button analysisButton = (Button)findViewById(R.id.analysisButton);
        analysisButton.setOnClickListener(analysisButtonOnClickListener);

        tv1 = (TextView) findViewById(R.id.Text1);
        tv2 = (TextView) findViewById(R.id.Text2);
        tv3 = (TextView) findViewById(R.id.Text3);
        tv4 = (TextView) findViewById(R.id.Text4);
        tv5 = (TextView) findViewById(R.id.Text5);
        tv6 = (TextView) findViewById(R.id.Text6);
        tv7 = (TextView) findViewById(R.id.Text7);
        tv8 = (TextView) findViewById(R.id.Text8);
        tv9 = (TextView) findViewById(R.id.Text9);
        tv10 = (TextView) findViewById(R.id.Text10);
        tv11 = (TextView) findViewById(R.id.Text11);
        tv12 = (TextView) findViewById(R.id.Text12);
        tv13 = (TextView) findViewById(R.id.Text13);
        tv14 = (TextView) findViewById(R.id.Text14);
        tv15 = (TextView) findViewById(R.id.Text15);
        tv16 = (TextView) findViewById(R.id.Text16);
        alltv = (TextView) findViewById(R.id.AllText);


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

    @Override
    protected void onResume(){
        super.onResume();
        //Toast.makeText(MainActivity.this, "onResume", Toast.LENGTH_SHORT).show();
        //System.out.println("onResume");
        /*Intent intent = getIntent();
        map = (HashMap<String, String>)intent.getSerializableExtra("MAP");*/
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Toast.makeText(MainActivity.this, "onStart", Toast.LENGTH_SHORT).show();
    }

    private  View.OnClickListener anketoButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "download", Toast.LENGTH_SHORT).show();
            try {
                BufferedReader br = new BufferedReader(new FileReader(ALL));
                while ((line = br.readLine()) != null) {
                    StringTokenizer token = new StringTokenizer(line, "\t");
                    headline = token.nextToken();
                    list.add(headline);
                }
                Intent intent = new Intent(MainActivity.this,AnketoActivity.class);//this,TestActivity.class
                //intent.setClassName("com.example.student11.pinot_exp3", "com.example.student11.pinot_exp3.AnketoActivity");
                intent.putExtra("LIST", list);
                startActivity(intent);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private  View.OnClickListener analysisButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            try {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("処理を実行しています");
                progressDialog.setCancelable(true);
                progressDialog.show();

                BufferedReader br = new BufferedReader(new FileReader(ANK));
                int a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q;
                a=b=c=d=e=f=g=h=i=j=k=l=m=n=o=p=q=0;
                while ((line = br.readLine()) != null) {
                    StringTokenizer token = new StringTokenizer(line, "\t");
                    headline = token.nextToken();
                    displaycount = Integer.valueOf(token.nextToken());
                    viewcount = Integer.valueOf(token.nextToken());
                    tapinfo = Integer.valueOf(token.nextToken());
                    interest = Boolean.valueOf(token.nextToken());
                    if(tapinfo >= 0){
                        if(interest){
                            if(tapinfo==0){
                                a++;
                            }else if(tapinfo==1){
                                b++;
                            }else if(tapinfo==2){
                                c++;
                            }else if(tapinfo>=3){
                                d++;
                            }
                        }else{
                            if(tapinfo==0){
                                e++;
                            }else if(tapinfo==1){
                                f++;
                            }else if(tapinfo==2){
                                g++;
                            }else if(tapinfo>=3){
                                h++;
                            }
                        }
                    }else{
                        if(interest){
                            if(viewcount==0){
                                i++;
                            }else if(viewcount==1){
                                j++;
                            }else if(viewcount==2){
                                k++;
                            }else if(viewcount>=3){
                                l++;
                            }
                        }else{
                            if(viewcount==0){
                                m++;
                            }else if(viewcount==1){
                                n++;
                            }else if(viewcount==2){
                                o++;
                            }else if(viewcount>=3){
                                p++;
                            }
                        }
                    }
                    q=a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p;
                }
                progressDialog.dismiss();
                //結果を画面に表示する
                //tv.setText("タップ：，興味：，視認回数：");
                tv1.setText(String.valueOf(a));
                tv2.setText("   "+String.valueOf(e));
                tv3.setText("   "+String.valueOf(i));
                tv4.setText("   "+String.valueOf(m));
                tv5.setText(String.valueOf(b));
                tv6.setText("   "+String.valueOf(f));
                tv7.setText("   "+String.valueOf(j));
                tv8.setText("   "+String.valueOf(n));
                tv9.setText(String.valueOf(c));
                tv10.setText("   "+String.valueOf(g));
                tv11.setText("   "+String.valueOf(k));
                tv12.setText("   "+String.valueOf(o));
                tv13.setText(String.valueOf(d));
                tv14.setText("   "+String.valueOf(h));
                tv15.setText("   "+String.valueOf(l));
                tv16.setText("   "+String.valueOf(p));
                alltv.setText(String.valueOf(q));
                /*System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                System.out.println(d);
                System.out.println(e);
                System.out.println(f);
                System.out.println(g);
                System.out.println(h);*/

            }catch (IOException e) {
                e.printStackTrace();
            }
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
