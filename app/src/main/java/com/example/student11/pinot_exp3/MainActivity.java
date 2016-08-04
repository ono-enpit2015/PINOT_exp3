package com.example.student11.pinot_exp3;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    //final String LOGDIR = "/sdcard/all.txt";
    //final String LOGDIR2 = "/sdcard/anketo.txt";
    String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/";
    String LOGDIR1 = Environment.getExternalStorageDirectory().getPath()+"/data/all.txt";
    String LOGDIR2 = Environment.getExternalStorageDirectory().getPath()+"/data/anketo.txt";
    final String SDFILE3 = LOGDIR + "username.txt";
    File ALL = new File(LOGDIR1);
    File ANK = new File(LOGDIR2);
    File DATA = new File(LOGDIR);
    File NAME = new File(SDFILE3);
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
    AsyncTask<Void, Void, String> task;
    private ProgressDialog progressDialog;
    static String path;
    String resultFileName;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!DATA.exists()) {
            DATA.mkdir();
        }

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


        /*try {
            if (ANK.createNewFile()) {
                //Toast.makeText(MainActivity.this, "ファイルの作成に成功", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(MainActivity.this, "ファイルの作成に失敗", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            //Toast.makeText(MainActivity.this, "例外が発生", Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }*/
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

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("結果の送信")
                    .setMessage("結果を送信しますか？")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // OK button pressed
                            try {
                                NAME.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                BufferedReader name = new BufferedReader(new FileReader(NAME));
                                username = name.readLine();
                                name.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            path = LOGDIR + "/anketo.txt";
                            resultFileName = username + "興味の有無.txt";
                            DataSend();
                            task.execute();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
        }
    };

    private void DataSend(){
        // タスク
        task = new AsyncTask<Void, Void, String>() {

            /**
             * 準備
             */
            @Override
            protected void onPreExecute() {

                // 進捗ダイアログを開始
                MainActivity.this.progressDialog = new ProgressDialog(MainActivity.this);
                MainActivity.this.progressDialog.setMessage("Now Loading ...");
                MainActivity.this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                MainActivity.this.progressDialog.setCancelable(true);
                MainActivity.this.progressDialog.show();
            }

            /**
             * 実行
             */
            @Override
            protected String doInBackground(Void... params) {

                FTPClient ftp = null;
                FileInputStream fis = null;
                FileOutputStream fos = null;

                try {

                    ftp = new FTPClient();

                    // エンコーディング
                    ftp.setControlEncoding("SJIS");     //コネクトの前に設定     Windowsサーバー:"Windows-31J"or"SJIS"

                    // 接続前タイムアウト：15秒
                    ftp.setDefaultTimeout(15000);
                    Log.e("デバック", "0");
                    // 接続
                    ftp.connect("133.71.201.164", 21);       //ホスト名「koblab.cs.ehime-u.ac.jp」に対して、ポート「21」に接続する   133.71.201.142
                    Log.e("デバック", "1");
                    // 接続エラーの場合
                    if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {

                        return "サーバーに接続できません";
                    }
                    // 接続後タイムアウト：10秒
                    ftp.setSoTimeout(15000);

                    // ログイン
                    if (!ftp.login("ono", "qt7gn089ebdonx2")) {

                        return "サーバーの認証に失敗しました";
                    }
                    Log.e("デバック", "2");
                    // ファイル種別：アスキーモード
                    ftp.setFileType(FTP.ASCII_FILE_TYPE);//BINARY_FILE_TYPE
                    // PASVモード
                    ftp.enterLocalPassiveMode();
                    // タイムアウト：10秒
                    ftp.setDataTimeout(20000);
                    Log.e("デバック", "3");

                    // 受信元のディレクトリを作成
                    //String path = Environment.getExternalStorageDirectory().getPath() + "/SAMPLE/";
                    //new File(path).mkdir();

                    // 受信   サーバーから「hoge1.txt」を、Android端末に「hoge2.txt」としてダウンロードする
                    /*fos = new FileOutputStream(path + "hoge2.txt");
                    if (!ftp.retrieveFile("/TEST/hoge1.txt", fos)) {
                        return "ファイルの受信に失敗しました";
                    }*/
                    //Log.e("デバック", "3");
                    // 送信   Android端末から「hoge2.txt」を、サーバーに「hoge3.txt」としてアップロードする
                    fis = new FileInputStream(path);
                    //Log.e("デバック", ""+path);
                    //Log.e("デバック", ""+resultFileName);
                    if (!ftp.storeFile("/home/ono/result0615/"+resultFileName, fis)) {

                        return "ファイルの送信に失敗しました";
                    }
                    //Log.e("デバック", "4");
                } catch (SocketException e) {

                    return "FTP通信に失敗しました（１）";

                } catch (IOException e) {

                    return "FTP通信に失敗しました（２）";

                } finally {

                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                        }
                    }

                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                        }
                    }

                    if (ftp != null) {
                        try {
                            // ログアウト
                            ftp.logout();
                        } catch (IOException e) {
                        }
                        try {
                            // 切断
                            ftp.disconnect();
                        } catch (IOException e) {
                        }
                    }
                }

                return "送受信に成功しました";
            }

            /**
             * 完了
             */
            @Override
            protected void onPostExecute(String param) {

                if (MainActivity.this.progressDialog.isShowing()) {

                    // 進捗ダイアログを終了
                    MainActivity.this.progressDialog.dismiss();
                }

                Toast.makeText(MainActivity.this, param, Toast.LENGTH_LONG).show();
            }
        };
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
