package com.example.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.json.databinding.ActivityMainBinding
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val json = """{
            "name": "John Doe",
            "age": 30,
            "city": "New York",
            "email": "john.doe@example.com"
        }""";


        val person: Person = Json.decodeFromString(json)// JSON 문자열을 객체로 변환

        val jsonString: String = Json.encodeToString(person); // 객체를 JSON 문자열로 변환

        binding.tv1.text = "${person.age}"
        binding.tv2.text = "$jsonString"

    }
}
