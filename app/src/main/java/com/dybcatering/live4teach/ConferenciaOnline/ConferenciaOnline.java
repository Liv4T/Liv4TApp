package com.dybcatering.live4teach.ConferenciaOnline;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosFragment;
import com.dybcatering.live4teach.R;

public class ConferenciaOnline extends AppCompatActivity implements MyWebChromeClient.ProgressListener {
	private ProgressBar chromeProgressBar;
	WebView myWebView;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conferencia_online);
		myWebView = findViewById(R.id.simpleWebView);
		WebSettings webSettings = myWebView.getSettings();

		myWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setAllowUniversalAccessFromFileURLs(true);
		webSettings.setAllowFileAccessFromFileURLs(true);

		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setAllowFileAccess(true);

		//webSettings.setLoadsImagesAutomatically(true);
		//inizialize client

		//load website by URL
		myWebView.loadUrl("https://clases.liv4t.com/sala-grupos-10/instant158393860641136?invite=true");
		chromeProgressBar = findViewById(R.id.progressBar);
		myWebView.setWebChromeClient(new WebChromeClient() {
			// Grant permissions for cam

			@Override
			public void onPermissionRequest(final PermissionRequest request) {

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					request.grant(request.getResources());
				}
			}
		});

		myWebView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				chromeProgressBar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
					chromeProgressBar.setVisibility(View.GONE);

			}
		});
	}

	@Override
	public void onUpdateProgress(int progressValue) {
		chromeProgressBar.setProgress(progressValue);
		if (progressValue == 100) {
			chromeProgressBar.setVisibility(View.INVISIBLE);
		}
	}

	public class WebViewController extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public void onBackPressed()
	{
		if(myWebView.canGoBack())
			myWebView.goBack();
		else
			super.onBackPressed();
	}

}



