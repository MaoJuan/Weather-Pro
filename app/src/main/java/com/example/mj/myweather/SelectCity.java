package com.example.mj.myweather;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

import cn.edu.pku.mj.app.MyApplication;
import cn.edu.pku.mj.bean.City;

/**
 * Created by MJ on 2017/10/23.
 */

public class SelectCity extends Activity implements View.OnClickListener {
    private ImageView mBackBtn;
    private ListView cityListLv;
    private EditText searchEt;
    private ImageView searchBtn;

    private List<City> mCityList;
    private MyApplication mApplication;
    private ArrayList<String> mArrayList;

    private String updateCityCode="-1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

       //mBackBtn=(ImageView)findViewById(R.id.title_back);
        // mBackBtn.setOnClickListener(this);

        searchEt=(EditText)findViewById(R.id.selectcity_search);
        searchBtn=(ImageView)findViewById(R.id.selectcity_search_button);
        searchBtn.setOnClickListener(this);

        mApplication=(MyApplication)getApplication();
        mCityList=mApplication.getCityList();
        mArrayList=new ArrayList<String>();
        for(int i=0;i<mCityList.size();i++){
            String No_=Integer.toString(i+1);
            String number=mCityList.get(i).getNumber();
            String provinceName=mCityList.get(i).getProvince();
            String cityName=mCityList.get(i).getCity();
            mArrayList.add("No."+No_+":"+number+"-"+provinceName+"-"+cityName);
        }
        cityListLv=(ListView)findViewById(R.id.selectcity_lv);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        cityListLv.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int updateCityCode1=Integer.parseInt(mCityList.get(position).getNumber());
                Log.d("update city code",Integer.toString(updateCityCode1));
                updateCityCode = Integer.toString(updateCityCode1);
            }
        };

        cityListLv.setOnItemClickListener(itemClickListener);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.selectcity_search_button:
                String citycode1=searchEt.getText().toString();
                Log.d("Search",citycode1);
                Intent i=new Intent(this,MainActivity.class);
                i.putExtra("cityCode",citycode1);
                startActivity(i);
                default:
                break;
        }
    }
}
