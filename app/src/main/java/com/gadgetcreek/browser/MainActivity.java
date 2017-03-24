package com.gadgetcreek.browser;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity  {
    WebView webview;
    EditText enter_url;
    String url;
    Button go;
    TextView textview;
    String current_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter_url = (EditText) findViewById(R.id.enter_url);
        go = (Button) findViewById(R.id.go);
        webview = (WebView) findViewById(R.id.webview);
        textview = (TextView)findViewById(R.id.loading);
    }
    public void click(View view){
        url=enter_url.getText().toString();

        webview.setWebViewClient(new myWebClient());
        webview.getSettings().setJavaScriptEnabled(true); //Enabling JavaScript

        webview.loadUrl("http://" + url);

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);  // Hiding the soft Android keyboard on click Button
    }
    public class myWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            current_url= url;
            view.loadUrl(url);
            enter_url.setText(current_url); // Set current url in enter_url EditText.
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            textview.setText("Loading....");
            textview.setVisibility(View.VISIBLE);  // Loading text will be visible
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            textview.setVisibility(View.GONE);  // Loading text will be disappear
        }
    }
}