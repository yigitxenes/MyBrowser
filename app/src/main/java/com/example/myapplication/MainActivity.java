package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText txtAddress;
    private Button btnGo;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAddress = findViewById(R.id.txtUrl);
        btnGo = findViewById(R.id.go);
        webView = findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = txtAddress.getText().toString();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                webView.loadUrl(url);
            }
        });
        if (getIntent() != null && getIntent().getData() != null){
            txtAddress.setText(getIntent().getData().toString());
            webView.loadUrl(getIntent().getData().toString());
        }
    }

    // Menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return true;
    }

    // Menu button actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_back) {
            if (webView.canGoBack()) webView.goBack();
            return true;
        } else if (id == R.id.item_forward) {
            if (webView.canGoForward()) webView.goForward();
            return true;
        } else if (id == R.id.item_home) {
            webView.loadUrl("http://www.google.com");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
