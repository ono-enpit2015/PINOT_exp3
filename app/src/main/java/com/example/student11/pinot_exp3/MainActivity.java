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
import android.view.KeyEvent;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
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
    String SDFILE4 = LOGDIR + "tap_nointerest.txt";
    String SDFILE5 = LOGDIR + "UserProfile.txt";
    File UP = new File(SDFILE5);
    File ALL = new File(LOGDIR1);
    File ANK = new File(LOGDIR2);
    File DATA = new File(LOGDIR);
    File NAME = new File(SDFILE3);
    File HEAR = new File(SDFILE4);
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
    TextView tv17;
    TextView tv18;
    TextView tv19;
    TextView tv20;
    TextView tv21;
    TextView tv22;
    TextView tv23;
    TextView tv24;
    TextView tv25;
    TextView tv26;
    TextView tv27;
    TextView tv28;
    TextView tv29;
    TextView tv30;
    TextView tv31;
    TextView tv32;
    TextView tv33;
    TextView tv34;
    TextView tv35;
    TextView tv36;
    TextView tv37;
    TextView tv38;
    TextView tv39;
    TextView tv40;
    TextView tv41;
    TextView tv42;
    TextView tv43;
    TextView tv44;
    TextView tv45;
    TextView tv46;
    TextView tv47;
    TextView tv48;
    TextView tv49;
    TextView tv50;
    TextView tv51;
    TextView tv52;
    AsyncTask<Void, Void, String> task;
    private ProgressDialog progressDialog;
    static String path;
    String resultFileName;
    String username;
    String button;

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

        Button hearingButton = (Button)findViewById(R.id.hearingButton);
        hearingButton.setOnClickListener(hearingButtonOnClickListener);

        Button UPButton = (Button)findViewById(R.id.UPButton);
        UPButton.setOnClickListener(UPButtonOnClickListener);

        Button FilButton = (Button)findViewById(R.id.filteringButton);
        FilButton.setOnClickListener(FilButtonOnClickListener);

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
        tv17 = (TextView) findViewById(R.id.Text17);
        tv18 = (TextView) findViewById(R.id.Text18);
        tv19 = (TextView) findViewById(R.id.Text19);
        tv20 = (TextView) findViewById(R.id.Text20);
        tv21 = (TextView) findViewById(R.id.Text21);
        tv22 = (TextView) findViewById(R.id.Text22);
        tv23 = (TextView) findViewById(R.id.Text23);
        tv24 = (TextView) findViewById(R.id.Text24);
        tv25 = (TextView) findViewById(R.id.Text25);
        tv26 = (TextView) findViewById(R.id.Text26);
        tv27 = (TextView) findViewById(R.id.Text27);
        tv28 = (TextView) findViewById(R.id.Text28);
        tv29 = (TextView) findViewById(R.id.Text29);
        tv30 = (TextView) findViewById(R.id.Text30);
        tv31 = (TextView) findViewById(R.id.Text31);
        tv32 = (TextView) findViewById(R.id.Text32);
        tv33 = (TextView) findViewById(R.id.Text33);
        tv34 = (TextView) findViewById(R.id.Text34);
        tv35 = (TextView) findViewById(R.id.Text35);
        tv36 = (TextView) findViewById(R.id.Text36);
        tv37 = (TextView) findViewById(R.id.Text37);
        tv38 = (TextView) findViewById(R.id.Text38);
        tv39 = (TextView) findViewById(R.id.Text39);
        tv40 = (TextView) findViewById(R.id.Text40);
        tv41 = (TextView) findViewById(R.id.Text41);
        tv42 = (TextView) findViewById(R.id.Text42);
        tv43 = (TextView) findViewById(R.id.Text43);
        tv44 = (TextView) findViewById(R.id.Text44);
        tv45 = (TextView) findViewById(R.id.Text45);
        tv46 = (TextView) findViewById(R.id.Text46);
        tv47 = (TextView) findViewById(R.id.Text47);
        tv48 = (TextView) findViewById(R.id.Text48);
        tv49 = (TextView) findViewById(R.id.Text49);
        tv50 = (TextView) findViewById(R.id.Text50);
        tv51 = (TextView) findViewById(R.id.Text51);
        tv52 = (TextView) findViewById(R.id.Text52);


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
                int i=0;
                while ((line = br.readLine()) != null) {
                    StringTokenizer token = new StringTokenizer(line, "\t");
                    headline = token.nextToken();
                    list.add(headline);
                    i++;
                    System.out.println(i + "," + headline);
                }
                br.close();
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
                    if(a==0){
                        a=1;
                    }else if(b==0){
                        b=1;
                    }else if(c==0){
                        c=1;
                    }else if(d==0){
                        d=1;
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
                tv16.setText("   " + String.valueOf(p));
                alltv.setText(String.valueOf(q));
                System.out.println("a:"+a);
                System.out.println("b:"+b);
                System.out.println("c:"+c);
                System.out.println("d:"+d);
                System.out.println("e:"+e);
                System.out.println("f:"+f);
                System.out.println("g:"+g);
                System.out.println("h:"+h);
                System.out.println("i:"+i);
                System.out.println("j:"+j);
                System.out.println("k:"+k);
                System.out.println("l:"+l);
                System.out.println("m:"+m);
                System.out.println("n:"+n);
                System.out.println("o:"+o);
                System.out.println("p:"+p);
                System.out.println("合計:"+q);


                double val1 = (double)(a+e)*100/(a+b+c+d+e+f+g+h);
                //元データをBigDecimal型にする
                BigDecimal bd1 = new BigDecimal(val1);
                //四捨五入する
                bd1 = bd1.setScale(1, BigDecimal.ROUND_HALF_UP);  //小数第１位
                double val2 = (double)(b+f)*100/(a+b+c+d+e+f+g+h);
                BigDecimal bd2 = new BigDecimal(val2);
                bd2 = bd2.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val3 = (double)(c+g)*100/(a+b+c+d+e+f+g+h);
                BigDecimal bd3 = new BigDecimal(val3);
                bd3 = bd3.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val4 = (double)(d+h)*100/(a+b+c+d+e+f+g+h);
                BigDecimal bd4 = new BigDecimal(val4);
                bd4 = bd4.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val5 = (double)(i+m)*100/(i+j+k+l+m+n+o+p);
                BigDecimal bd5 = new BigDecimal(val5);
                bd5 = bd5.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val6 = (double)(j+n)*100/(i+j+k+l+m+n+o+p);
                BigDecimal bd6 = new BigDecimal(val6);
                bd6 = bd6.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val7 = (double)(k+o)*100/(i+j+k+l+m+n+o+p);
                BigDecimal bd7 = new BigDecimal(val7);
                bd7 = bd7.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val8 = (double)(l+p)*100/(i+j+k+l+m+n+o+p);
                BigDecimal bd8 = new BigDecimal(val8);
                bd8 = bd8.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val9 = (double)(a+b+c+d)*100/(a+b+c+d+e+f+g+h);
                BigDecimal bd9 = new BigDecimal(val9);
                bd9 = bd9.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val10 = (double)(a)*100/(a+e);
                BigDecimal bd10 = new BigDecimal(val10);
                bd10 = bd10.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val11 = (double)(b)*100/(b+f);
                BigDecimal bd11 = new BigDecimal(val11);
                bd11 = bd11.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val12 = (double)(c)*100/(c+g);
                BigDecimal bd12 = new BigDecimal(val12);
                bd12 = bd12.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val13 = (double)(d)*100/(d+h);
                BigDecimal bd13 = new BigDecimal(val13);
                bd13 = bd13.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val14 = (double)(m+n+o+p)*100/(i+j+k+l+m+n+o+p);
                BigDecimal bd14 = new BigDecimal(val14);
                bd14 = bd14.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val15 = (double)(m)*100/(i+m);
                BigDecimal bd15 = new BigDecimal(val15);
                bd15 = bd15.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val16 = (double)(n)*100/(j+n);
                BigDecimal bd16 = new BigDecimal(val16);
                bd16 = bd16.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val17 = (double)(o)*100/(k+o);
                BigDecimal bd17 = new BigDecimal(val17);
                bd17 = bd17.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val18 = (double)(p)*100/(l+p);
                BigDecimal bd18 = new BigDecimal(val18);
                bd18 = bd18.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val19 = (double)(a+b+c+d+i+j+k+l)*100/(q);
                BigDecimal bd19 = new BigDecimal(val19);
                bd19 = bd19.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val20 = (double)(e+f+g+h+m+n+o+p)*100/(q);
                BigDecimal bd20 = new BigDecimal(val20);
                bd20 = bd20.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val21 = (double)(a+b+c+d+e+f+g+h)*100/(q);
                BigDecimal bd21 = new BigDecimal(val21);
                bd21 = bd21.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val22 = (double)(i+j+k+l+m+n+o+p)*100/(q);
                BigDecimal bd22 = new BigDecimal(val22);
                bd22 = bd22.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val23 = (double)(a+e+i+m)*100/(q);
                BigDecimal bd23 = new BigDecimal(val23);
                bd23 = bd23.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val24 = (double)(b+f+j+n)*100/(q);
                BigDecimal bd24 = new BigDecimal(val24);
                bd24 = bd24.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val25 = (double)(c+g+k+o)*100/(q);
                BigDecimal bd25 = new BigDecimal(val25);
                bd25 = bd25.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val26 = (double)(d+h+l+p)*100/(q);
                BigDecimal bd26 = new BigDecimal(val26);
                bd26 = bd26.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val27 = (double)(a+b+c+d)*100/(a+b+c+d+i+j+k+l);
                BigDecimal bd27 = new BigDecimal(val27);
                bd27 = bd27.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val28 = (double)(a)*100/(a+i);
                BigDecimal bd28 = new BigDecimal(val28);
                bd28 = bd28.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val29 = (double)(b)*100/(b+j);
                BigDecimal bd29 = new BigDecimal(val29);
                bd29 = bd29.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val30 = (double)(c)*100/(c+k);
                BigDecimal bd30 = new BigDecimal(val30);
                bd30 = bd30.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val31 = (double)(d)*100/(d+l);
                BigDecimal bd31 = new BigDecimal(val31);
                bd31 = bd31.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val32 = (double)(m+n+o+p)*100/(e+f+g+h+m+n+o+p);
                BigDecimal bd32 = new BigDecimal(val32);
                bd32 = bd32.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val33 = (double)(m)*100/(e+m);
                BigDecimal bd33 = new BigDecimal(val33);
                bd33 = bd33.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val34 = (double)(n)*100/(f+n);
                BigDecimal bd34 = new BigDecimal(val34);
                bd34 = bd34.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val35 = (double)(o)*100/(g+o);
                BigDecimal bd35 = new BigDecimal(val35);
                bd35 = bd35.setScale(1, BigDecimal.ROUND_HALF_UP);
                double val36 = (double)(p)*100/(h+p);
                BigDecimal bd36 = new BigDecimal(val36);
                bd36 = bd36.setScale(1, BigDecimal.ROUND_HALF_UP);

                tv17.setText("(視認回数0回でタップ)/(タップ):" + bd1.doubleValue());
                tv18.setText("(視認回数1回でタップ)/(タップ):" + bd2.doubleValue());
                tv19.setText("(視認回数2回でタップ)/(タップ):" + bd3.doubleValue());
                tv20.setText("(視認回数3回でタップ)/(タップ):" + bd4.doubleValue());

                tv21.setText("(視認回数0回で未タップ)/(未タップ):" + bd5.doubleValue());
                tv22.setText("(視認回数1回で未タップ)/(未タップ):" + bd6.doubleValue());
                tv23.setText("(視認回数2回で未タップ)/(未タップ):" + bd7.doubleValue());
                tv24.setText("(視認回数3回で未タップ)/(未タップ):" + bd8.doubleValue());

                tv25.setText("(興味あり)/(タップ):" + bd9.doubleValue());
                tv26.setText("(興味あり)/(視認回数0回でタップ):" + bd10.doubleValue());
                tv27.setText("(興味あり)/(視認回数1回でタップ):" + bd11.doubleValue());
                tv28.setText("(興味あり)/(視認回数2回でタップ):" + bd12.doubleValue());
                tv29.setText("(興味あり)/(視認回数3回でタップ):" + bd13.doubleValue());

                tv30.setText("(興味なし)/(未タップ):" + bd14.doubleValue());
                tv31.setText("(興味なし)/(視認回数0回で未タップ):" + bd15.doubleValue());
                tv32.setText("(興味なし)/(視認回数1回で未タップ):" + bd16.doubleValue());
                tv33.setText("(興味なし)/(視認回数2回で未タップ):" + bd17.doubleValue());
                tv34.setText("(興味なし)/(視認回数3回で未タップ):" + bd18.doubleValue());

                tv35.setText("(興味あり)/(配信された見出し):" + bd19.doubleValue());
                tv36.setText("(興味なし)/(配信された見出し):" + bd20.doubleValue());

                tv37.setText("(タップ)/(配信された見出し):" + bd21.doubleValue());
                tv38.setText("(未タップ)/(配信された見出し):" + bd22.doubleValue());

                tv39.setText("(視認回数0回)/(配信された見出し):" + bd23.doubleValue());
                tv40.setText("(視認回数1回)/(配信された見出し):" + bd24.doubleValue());
                tv41.setText("(視認回数2回)/(配信された見出し):" + bd25.doubleValue());
                tv42.setText("(視認回数3回)/(配信された見出し):" + bd26.doubleValue());

                tv43.setText("(タップ)/(興味あり):" + bd27.doubleValue());
                tv44.setText("(タップ)/(視認回数0回で興味あり):" + bd28.doubleValue());
                tv45.setText("(タップ)/(視認回数1回で興味あり):" + bd29.doubleValue());
                tv46.setText("(タップ)/(視認回数2回で興味あり):" + bd30.doubleValue());
                tv47.setText("(タップ)/(視認回数3回で興味あり):" + bd31.doubleValue());

                tv48.setText("(未タップ)/(興味なし):" + bd32.doubleValue());
                tv49.setText("(未タップ)/(視認回数0回で興味なし):" + bd33.doubleValue());
                tv50.setText("(未タップ)/(視認回数1回で興味なし):" + bd34.doubleValue());
                tv51.setText("(未タップ)/(視認回数2回で興味なし):" + bd35.doubleValue());
                tv52.setText("(未タップ)/(視認回数3回で興味なし):" + bd36.doubleValue());

            br.close();
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
                            button="analysis";
                            DataSend();
                            task.execute();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
        }
    };

    private  View.OnClickListener FilButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "download", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,AnketoActivity.class);//this,TestActivity.class
                //intent.setClassName("com.example.student11.pinot_exp3", "com.example.student11.pinot_exp3.AnketoActivity");
                intent.putExtra("LIST", list);
                startActivity(intent);
        }
    };

    private  View.OnClickListener hearingButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(ANK));
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(HEAR));
                    while ((line = br.readLine()) != null) {
                        StringTokenizer token = new StringTokenizer(line, "\t");
                        headline = token.nextToken();
                        displaycount = Integer.valueOf(token.nextToken());
                        viewcount = Integer.valueOf(token.nextToken());
                        tapinfo = Integer.valueOf(token.nextToken());
                        interest = Boolean.valueOf(token.nextToken());
                        if(tapinfo>=0 && !interest) {
                            bw.write(headline + "\t" + tapinfo + "\t" + interest);
                            bw.newLine();
                        }
                    }
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "success!", Toast.LENGTH_SHORT).show();
                br.close();
            }catch (IOException e) {
                e.printStackTrace();
            }

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("ファイルの送信")
                    .setMessage("ヒアリング用ファイルを送信しますか？")
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

                            path = SDFILE4;
                            resultFileName = username + "ヒアリング用.txt";
                            button="hearing";
                            DataSend();
                            task.execute();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
        }
    };

    private  View.OnClickListener UPButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "download", Toast.LENGTH_SHORT).show();
            try {
                UP.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader(ANK));
                PINOT_FILTER P = new PINOT_FILTER();
                while ((line = br.readLine()) != null) {
                    StringTokenizer token = new StringTokenizer(line, "\t");
                    headline = token.nextToken();
                    displaycount = Integer.parseInt(token.nextToken());
                    viewcount = Integer.parseInt(token.nextToken());
                    tapinfo = Integer.parseInt(token.nextToken());
                    interest = Boolean.parseBoolean(token.nextToken());
                    if(tapinfo>=0){
                        P.Pinot_Filter(headline, 3);
                    }else if((tapinfo==-1)&&(viewcount>=1)){
                        P.Pinot_Filter(headline, 2);
                    }
                }
                Toast.makeText(MainActivity.this, "作成完了", Toast.LENGTH_SHORT).show();
                br.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
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
                    if(button.equals("analysis")) {
                        if (!ftp.storeFile("/home/ono/result0615/" + resultFileName, fis)) {

                            return "ファイルの送信に失敗しました";
                        }
                    }else if(button.equals("hearing")){
                        if (!ftp.storeFile("/home/ono/hearing/" + resultFileName, fis)) {

                            return "ファイルの送信に失敗しました";
                        }
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){				// 戻るボタンが押された！
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
