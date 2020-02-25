package com.app.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        WebView w = (WebView) findViewById(R.id.webView);
        String u = (String) getIntent().getSerializableExtra("url");
        if(u.equals(""))
            w.loadUrl("http://tutorialspoint.com/android/sampleXML.xml");
        else
            w.loadUrl(u);
    }
}