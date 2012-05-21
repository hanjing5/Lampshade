package com.syn3rgy.lampshade.fragments;

import android.net.Uri;
import android.os.AsyncTask;

import com.syn3rgy.lampshade.fragments.listeners.OnArticleLoadListener;
import com.syn3rgy.lampshade.fragments.listeners.OnInteractionListener;
import com.syn3rgy.tropeswrapper.TropesArticle;
import com.syn3rgy.tropeswrapper.TropesArticleInfo;

/** Loads an article in a different thread */
public class LoadTropesTask extends AsyncTask<Uri, Integer, Object> {
	OnArticleLoadListener tLoadListener;
	OnInteractionListener tInteractionListener;
	
	public LoadTropesTask(OnArticleLoadListener tLoadListener, OnInteractionListener tInteractionListener) {
		this.tLoadListener = tLoadListener;
		this.tInteractionListener = tInteractionListener;
	}
	
	@Override
	protected void onPreExecute() {
		this.tLoadListener.onLoadStart();
	}
	
	@Override
	protected Object doInBackground(Uri... params) {
		try {
			Uri url = params[0];
			TropesArticle article = new TropesArticle(url);
			return article;
		} catch (Exception e) {
			return e;
		}
	}
	
	@Override
	protected void onPostExecute(Object result) {
		if(result.getClass() == TropesArticle.class) {
			TropesArticle article = (TropesArticle) result;
			TropesArticleInfo articleInfo = new TropesArticleInfo(article.title, article.url, article.subpages);
			this.tLoadListener.onLoadFinish(articleInfo);
		}
		else {
			Exception e = (Exception) result;
			this.tLoadListener.onLoadError(e);
		}
	}
}