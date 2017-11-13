package com.apps.rshankar.nakshatra_porutham;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamResult;

public class Porutham_Results extends AppCompatActivity {

    ArrayList<Integer> allId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int red = Color.parseColor("#FF0000");
        int green = Color.parseColor("#00FF00");
        int blue = Color.parseColor("#0000FF");
        int yellow = Color.parseColor("#FFFF00");
        int pink = Color.parseColor("#904691");
        int orange = Color.parseColor("#FFC300");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        String allN[] = intent.getStringArrayExtra(Nakshatra_Porutham.All_NAKSHATRAM);

        allId = intent.getIntegerArrayListExtra(Nakshatra_Porutham.ID_NAKSHATRAM);


        Porutham porutham = doTheyMatch(allId);

        String[] analyzedResults = {"Not Applicable","Not Applicable","Not Applicable","Not Applicable","Not Applicable","Not Applicable","Not Applicable","Not Applicable","Not Applicable","Not Applicable"};

        if(porutham.starPorutham.equals("80")||porutham.starPorutham.equals("88")||porutham.starPorutham.equals("EE2")||porutham.starPorutham.equals("EE3")) {
            System.out.println("Porutham is either 80, 88, EE2 or EE3");

        }
        else {
            analyzedResults = analyzePorutham(porutham.starPorutham);

        }
        /*    TableLayout tableview = findViewById(R.id.StarPorutham);
            TableRow tr = tableview.findViewById(R.id.girlStar);
            TextView txtView = tr.findViewById(R.id.textView34);

            txtView.setText(allN[0]);

            tr = tableview.findViewById(R.id.boyStar);
       //     txtView = tr.findViewById(R.id.textView36);

//            txtView.setText(allN[1]);*/


            TextView txtView = findViewById(R.id.textView5);
            txtView.setText(allN[0]);

         txtView = findViewById(R.id.textView6);
        txtView.setText(allN[1]);

            txtView = findViewById(R.id.textView37);


        if (porutham.starTotalPorutham.equals("88")||porutham.starTotalPorutham.equals("80")||porutham.starTotalPorutham.equals("EE2")||porutham.starTotalPorutham.equals("EE3")){
            txtView.setText("-");
        }
        else {

            ImageView iv = findViewById(R.id.imageView3);

            switch (porutham.matchStatus) {

                case "Excellent to proceed":
                    txtView.setTextColor(pink);
                    iv.setImageResource(R.drawable.thumbsup);
                    txtView.setText((porutham.starTotalPorutham) + " out of 10 match\n"+porutham.matchStatus+"\nDetails below:");
                    break;

                case "OK to proceed":
                    txtView.setTextColor(blue);
                    iv.setImageResource(R.drawable.okthumbs);
                    txtView.setText((porutham.starTotalPorutham) + " out of 10 match\n"+porutham.matchStatus+"\nDetails below:");
                    break;

                case "Does not match\nCheck Another Prospect":

                    txtView.setTextColor(red);
                    iv.setImageResource(R.drawable.thumbsdown);
                    txtView.setText(("Only "+porutham.starTotalPorutham) + " out of 10 match"+"\nCheck Another Prospect"+"\nDetails below:");
                    break;

                case "Same stars will not match\nCheck another prospect":
                    txtView.setTextColor(red);
                    iv.setImageResource(R.drawable.thumbsdown);
                    txtView.setText(porutham.matchStatus+"\nDetails below:");
                    break;
                case "Rajju Porutham Fails.\nCheck another prospect":
                    txtView.setTextColor(red);
                    iv.setImageResource(R.drawable.thumbsdown);
                    txtView.setText(porutham.matchStatus+"\nDetails below:");
                    break;

                default:
                    txtView.setTextColor(red);
                    iv.setImageResource(R.drawable.thumbsdown);
                    txtView.setText(porutham.matchStatus);

            }
        }


        TableLayout tableview  = findViewById(R.id.viewDetails);
        TableRow tr;

        System.out.println("starTotalPorutham ****************:" + porutham.starPorutham);

        if (porutham.starPorutham.equals("88")||porutham.starPorutham.equals("80")||porutham.starPorutham.equals("EE2")||porutham.starPorutham.equals("EE3")){
            tableview.setVisibility(View.INVISIBLE);
            tableview.setVisibility(View.GONE);
        }
        else {

            tr = tableview.findViewById(R.id.dhina);
            txtView = tr.findViewById(R.id.textView14);
            txtView.setText(analyzedResults[0]);
            if (analyzedResults[0].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.Gana);
            txtView = tr.findViewById(R.id.textView16);
            txtView.setText(analyzedResults[1]);
            if (analyzedResults[1].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.mahendra);
            txtView = tr.findViewById(R.id.textView18);
            txtView.setText(analyzedResults[2]);
            if (analyzedResults[2].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.sthree);
            txtView = tr.findViewById(R.id.textView19);
            txtView.setText(analyzedResults[3]);
            if (analyzedResults[3].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.yoni);
            txtView = tr.findViewById(R.id.textView20);
            txtView.setText(analyzedResults[4]);
            if (analyzedResults[4].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.rasi);
            txtView = tr.findViewById(R.id.textView21);
            txtView.setText(analyzedResults[5]);
            if (analyzedResults[5].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.rasiAdhipathi);
            txtView = tr.findViewById(R.id.textView22);
            txtView.setText(analyzedResults[6]);
            if (analyzedResults[6].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.vasiya);
            txtView = tr.findViewById(R.id.textView23);
            txtView.setText(analyzedResults[7]);
            if (analyzedResults[7].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.rajju);
            txtView = tr.findViewById(R.id.textView24);
            txtView.setText(analyzedResults[8]);
            if (analyzedResults[8].equals("✓")) {
                txtView.setTextColor(pink);
            }

            tr = tableview.findViewById(R.id.vedhai);
            txtView = tr.findViewById(R.id.textView26);
            txtView.setText(analyzedResults[9]);
            if (analyzedResults[9].equals("✓")) {
                txtView.setTextColor(pink);
            }
        }



    }

    public Porutham doTheyMatch(ArrayList<?> idxNakshatram) {

        try {
          //  String starCombination = idxNakshatram.get(0).toString();

            int bIdx = (Integer) idxNakshatram.get(1);
            int gIdx = (Integer) idxNakshatram.get(0);

            return PoruthamForTheSelectedStars(gIdx, bIdx);

        } catch (XmlPullParserException xpe) {
            System.out.println("XMLPullParser Exception at doTheyMatch "+xpe);
            return null;
        } catch (IOException ie) {
            System.out.println("IO Exception at doTheyMatch "+ie);
            return null;
        }


    }

    public static class Porutham {
        public final String starId;
        public final String starName;
        public final String starPorutham;
        public final String starTotalPorutham;
        public final String matchStatus;

        private Porutham(String starId, String starName, String starPorutham, String starTotalPorutham, String matchStatus) {
            this.starId = starId;
            this.starName = starName;
            this.starPorutham = starPorutham;
            this.starTotalPorutham = starTotalPorutham;
            this.matchStatus = matchStatus;
        }

    }



    public Porutham PoruthamForTheSelectedStars(int gId, int bId) throws XmlPullParserException, IOException {


        String starId = null;
        String starName = null;
        String starPorutham = null;
        String starTotalPorutham = null;
        String matchStatus = null;
        String matchStatusDet = null;



        XmlPullParser parser;
        try {


            parser = getResources().getXml(R.xml.porutham0);
            parser = getParser(gId,parser);


            while (parser.next() != XmlPullParser.END_DOCUMENT) {

                if (parser.getEventType() != XmlPullParser.START_TAG) {

                    continue;

                } else {
                    starId = null;
                    String name = parser.getName();
                    if (name.equals("id")) {
                        starId = readText(parser);
                    } else {

                        continue;
                    }

                    if (starId != null && Integer.parseInt(starId) == bId) {
                        parser.next();
                        starName = readText(parser);
                        parser.next();
                        starPorutham = readText(parser);
                        parser.next();
                        starTotalPorutham = readText(parser);
                        parser.next();

                        matchStatusDet = readText(parser);

                        System.out.println("match status " + matchStatusDet);

                        switch (matchStatusDet) {
                            case "1":
                                matchStatus = "Excellent to proceed";
                                break;
                            case "2":
                                matchStatus = "OK to proceed";
                                break;
                            case "3":
                                matchStatus = "Does not match\nCheck Another Prospect";
                                break;

                            case "80":
                                matchStatus = "Same stars will not match\nCheck Another Prospect";
                                starTotalPorutham = "-";
                                break;

                            case "88" :
                                matchStatus = "Rajju Porutham Fails.\nCheck another prospect";
                                starTotalPorutham = "-";
                                break;
                            case "EE2":
                                matchStatus = "Same stars will not match\nCheck another prospect";
                                starTotalPorutham = "-";
                                break;
                            case "EE3":
                                matchStatus = "Same stars will not match\nCheck another prospect";
                                starTotalPorutham = "-";
                                break;
                            default :
                                matchStatus = "No Valid Status";
                                starTotalPorutham = "-";
                        }



                        System.out.println("Retrieved Values are " + starId + " : " + starName + " : " + starPorutham + " : " + starTotalPorutham + " : " + matchStatus);
                        return new Porutham(starId, starName, starPorutham, starTotalPorutham, matchStatus);
                    } else {
                        System.out.println("id Not Matching");
                        continue;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Exception while accessing xml " + e);

        }
        return new Porutham(starId, starName, starPorutham, starTotalPorutham, matchStatus);
    }

    // For the tags value for each tag
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String tagValue = "";
        if (parser.next() == XmlPullParser.TEXT) {
            tagValue = parser.getText();
            parser.nextTag();
        }
        return tagValue;
    }

    public String[] analyzePorutham(String result) {

        String[] analyzedResults = {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"};


        try {

            String[] resList = result.split(",");

            for (int i = 0; i < resList.length + 1; i++) {

                System.out.println("Results List " + Integer.parseInt(resList[i]));

                analyzedResults[Integer.parseInt(resList[i])-1] = "✓";
            }



        }
        catch (Exception e)

        {
           System.out.println("Exception at analyzePorutham "+e);
        }

        return analyzedResults;
    }

    /** Called when the user taps the Exit button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, ThankYou_Activity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Exit button */
    public void startAgain(View view) {
        Intent intent = new Intent(this, Nakshatra_Porutham.class);
        startActivity(intent);
    }

    public XmlPullParser getParser(int gId, XmlPullParser parser) throws XmlPullParserException{

        switch (gId) {
            case 0:
                parser = getResources().getXml(R.xml.porutham0);
                break;
            case 1:
                parser = getResources().getXml(R.xml.porutham1);
                break;
               case 2:
                    parser = getResources().getXml(R.xml.porutham2);
                    break;
                case 3:
                    parser = getResources().getXml(R.xml.porutham3);
                    break;
                case 4:
                    parser = getResources().getXml(R.xml.porutham4);
                    break;
                case 5:
                    parser = getResources().getXml(R.xml.porutham5);
                    break;
                case 6:
                    parser = getResources().getXml(R.xml.porutham6);
                    break;
                case 7:
                    parser = getResources().getXml(R.xml.porutham7);
                    break;
                case 8:
                    parser = getResources().getXml(R.xml.porutham8);
                    break;
                case 9:
                    parser = getResources().getXml(R.xml.porutham9);
                    break;
                case 10:
                    parser = getResources().getXml(R.xml.porutham10);
                    break;
                case 11:
                    parser = getResources().getXml(R.xml.porutham11_1);
                    break;
                case 12:
                    parser = getResources().getXml(R.xml.porutham12);
                    break;
                case 13:
                    parser = getResources().getXml(R.xml.porutham13);
                    break;
                case 14:
                    parser = getResources().getXml(R.xml.porutham14);
                    break;
                case 15:
                    parser = getResources().getXml(R.xml.porutham15);
                    break;
                case 16:
                    parser = getResources().getXml(R.xml.porutham16);
                    break;
                case 17:
                    parser = getResources().getXml(R.xml.porutham17);
                case 18:
                    parser = getResources().getXml(R.xml.porutham18);
                    break;
                case 19:
                    parser = getResources().getXml(R.xml.porutham19);
                    break;
                case 20:
                    parser = getResources().getXml(R.xml.porutham20);
                    break;
                case 21:
                    parser = getResources().getXml(R.xml.porutham21);
                    break;
                case 22:
                    parser = getResources().getXml(R.xml.porutham22);
                    break;
                case 23:
                    parser = getResources().getXml(R.xml.porutham23);
                    break;
                case 24:
                    parser = getResources().getXml(R.xml.porutham24);
                    break;

                case 25:
                    parser = getResources().getXml(R.xml.porutham25);
                    break;

                case 26:
                    parser = getResources().getXml(R.xml.porutham26);
                    break;
                case 27:
                    parser = getResources().getXml(R.xml.porutham27);
                    break;
                case 28:
                    parser = getResources().getXml(R.xml.porutham28);
                    break;
                case 29:
                    parser = getResources().getXml(R.xml.porutham29);
                    break;
                case 30:
                    parser = getResources().getXml(R.xml.porutham30);
                    break;
                case 31:
                    parser = getResources().getXml(R.xml.porutham31);
                    break;
                case 32:
                    parser = getResources().getXml(R.xml.porutham32);
                    break;
                case 33:
                    parser = getResources().getXml(R.xml.porutham33);
                    break;
                case 34:
                    parser = getResources().getXml(R.xml.porutham34);
                    break;
                case 35:
                    parser = getResources().getXml(R.xml.porutham35);
                    break;
            default:
        }
        return parser;
    }
}
