package com.qks.beautyexchange.api.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qks.beautyexchange.R;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/3/23.
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);


    }
}
