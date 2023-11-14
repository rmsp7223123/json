package com.example.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.json.databinding.ActivityMainBinding
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Modifier

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

//        val json = """{
//            "name": "John Doe",
//            "age": 30,
//            "city": "New York",
//            "email": "john.doe@example.com"
//        }""";
//
//
//        val person: Person = Json.decodeFromString(json)// JSON 문자열을 객체로 변환
//
//        val jsonString: String = Json.encodeToString(person); // 객체를 JSON 문자열로 변환
//
//        binding.tv1.text = "${person.age}"
//        binding.tv2.text = "$jsonString"

//        // 1. JSON 파일 열어서 String으로 취득
//        val jsonString = assets.open("data.json").reader().readText()
//        Log.d("jsonString", jsonString);
//
//
//        // 2. JSONArray 로 파싱
//        val jsonArray = JSONArray(jsonString);
//        Log.d("jsonArray", jsonArray.toString());
//
//
//        // 3. JSONArray 순회: 인덱스별 JsonObject 취득후, key에 해당하는 value 확인
//        for (index in 0 until jsonArray.length()){
//            val jsonObject = jsonArray.getJSONObject(index);
//
//            val id = jsonObject.getString("id");
//            val language = jsonObject.getString("language");
//
//            Log.d("jsonObject", jsonObject.toString());
//            Log.d("json_id_language", "$id $language");
//        };

        val jsonObject =
            JSONObject("{\"name\":\"Douglas Crockford\",\"age\": 66,\"from\" : \"USA\",\"education\" : [\"San Francisco State University\", \"Newport Harbor High School\"] }");

//각각의 키를 직접 가져와서 객체에 매핑

//        val data = PersonData(
//            jsonObject.getString("name"),
//            jsonObject.getInt("age"),
//            jsonObject.getString("from"),
//            jsonObject.getJSONArray("education")
//        );

        val gson = GsonBuilder().create();

        val data =
            "[{\"age\":27,\"name\":\"John\"},{\"age\":21,\"name\":\"Ava\"},{\"age\":38,\"name\":\"Emma\"}]";

        val result = gson.fromJson(jsonObject, Array<sampleData>::class.java)
        for (data in result)


    }

    fun test() {
        val jsonString = """
        {
        "person": [
                  {
                    "id": 0,
                    "name": "Mathews Parker",
                    "email": "mathewsparker@franscene.com"
                  },
                  {
                    "id": 1,
                    "name": "Dickson Clements",
                    "email": "dicksonclements@franscene.com"
                  },
                  {
                    "id": 2,
                    "name": "Pat Blair",
                    "email": "patblair@franscene.com"
                  },
                  {
                    "id": 3,
                    "name": "Estela Mckinney",
                    "email": "estelamckinney@franscene.com"
                  },
                  {
                    "id": 4,
                    "name": "Rivera Mcclain",
                    "email": "riveramcclain@franscene.com"
                  }
                ]
        }
    """.trimIndent();

        val jsonObject = JSONObject(jsonString);
        val jsonArray = jsonObject.getJSONArray("person");

        for (i in 0 until jsonArray.length()) {
            val iObject = jsonArray.getJSONObject(i);
            val id = iObject.getInt("id");
            val name = iObject.getString("name");
            val email = iObject.getString("email");

            println("================== ${i + 1} 번째==================");
            println("${i + 1}번 id : $id");
            println("${i + 1}번 name : $name");
            println("${i + 1}번 email : $email");
        }
    }

}


