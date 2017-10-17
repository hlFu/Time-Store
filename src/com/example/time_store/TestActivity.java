package com.example.time_store;

import com.example.time_store.toolkit.DirectionClass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;

public class TestActivity extends ActionBarActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String direction = "up";
		DirectionClass postDirection = new DirectionClass(direction);
		postDirection.upload();
	}
}
