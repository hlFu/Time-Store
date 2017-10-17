package com.example.time_store.toolkit;

import org.json.JSONObject;

public class Result {
	JSONObject result;
	String shortResult;
	int status;
	public Result(JSONObject result) {
		this.result = result;
	}
	public Result(String shortResult) {
		this.shortResult = shortResult;
	}
}
