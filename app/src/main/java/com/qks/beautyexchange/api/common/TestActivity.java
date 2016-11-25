package com.qks.beautyexchange.api.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.api.service.ServiceUtils;
import com.qks.beautyexchange.api.service.UserService;

/**
 * Created by admin on 2016/3/23.
 */
public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        ServiceUtils.extractData(RetrofitTools.getInstance().createApiService(UserService.class).login("","",""))
        .subscribe(user -> {
            System.out.println(user.getUser_id());
            System.out.println(user.getWeixin_uid());
        },throwable -> {
            System.out.println(throwable.getCause());
            System.out.println(throwable.getMessage());
        });



    }
}
