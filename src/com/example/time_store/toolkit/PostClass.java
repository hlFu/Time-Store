package com.example.time_store.toolkit;

import org.json.JSONObject;

public class PostClass {
	String url;
	JSONObject data;
	public PostClass(String url, JSONObject data) {
		this.url = url;
		this.data = data;
	}
}