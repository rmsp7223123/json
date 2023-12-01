package com.example.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.json.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding;
    private lateinit var newsNavHostFragment : NavHostFragment;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater);
        setContentView(binding.root);
        newsNavHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment;

        binding.bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController());

        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(RetrofitService::class.java);

        service.getUser()?.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful){
                    var result: News? = response.body();
                    Log.d("YMC", "onResponse 성공: " + result?.toString());
                }else{
                    Log.d("YMC", "onResponse 실패");
                };
            };

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            };
        });
    };
}