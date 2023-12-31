package com.example.json

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.json.databinding.ActivityMainBinding
import com.fasterxml.jackson.databind.util.ClassUtil
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.realm.kotlin.Realm
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
    lateinit var dbHelper: DBHelper;
    lateinit var database: SQLiteDatabase;
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        dbHelper = DBHelper(this, "mydb.db", null, 1);
        database = dbHelper.writableDatabase;

        binding.insert.setOnClickListener {
            var query = "INSERT INTO animals('animal') values('${binding.edit.text}');";
            database.execSQL(query);
            Toast.makeText(this, "추가되었습니다.${binding.edit.text}", Toast.LENGTH_SHORT).show();
        };

        binding.select.setOnClickListener {
            var animals = mutableListOf<String>();
            var query = "SELECT * FROM animals;";
            var cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                var id = cursor.getString(cursor.getColumnIndex("id"));
                var animal = cursor.getString(cursor.getColumnIndex("animal"));
                animals.add("${id}/${animal}");
            };
            Toast.makeText(this, animals.toString(), Toast.LENGTH_SHORT).show();
        };

        binding.update.setOnClickListener {
            var query = "UPDATE animals SET animal = 'Cat' WHERE id = 1;";
            database.execSQL(query);
        };

        binding.delete.setOnClickListener {
            var query = "DELETE FROM animals WHERE id = 2;";
            database.execSQL(query);
        };

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

        // val result = gson.fromJson(jsonObject, Array<sampleData>::class.java)
        // for (data in result)


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

    fun test3() {
        val jsonStr = assets.open("data.json").reader().readText()
        //assets폴더에서 data.json이라는 파일을 찾아 일고 jsonStr이라는 변수에 저장

        Log.d("jsonStr", jsonStr)

        //Json
        val jsonArray = JSONArray(jsonStr)
        //data.json 파일에서 읽어온 값을 jasonArray라는 배열 변수에 넣는다.

        Log.d("jsonStr", jsonArray.toString())

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            //jsonObject라는 변수는 데이터 값이 저장되어있는 jasonArray의 각 i번째 값이다.

            // binding.tv4.append("\n--------------------------------\n")

            val id = jsonObject.getString("id") // 데이터 값에서 id값을 불러온다
            val language = jsonObject.getString("language") // 데이터 값에서 language 값을 불러온다

            // binding.tv4.append(
            """
                    $id 
                """.trimIndent()

            //         )
            //textView에 id 값을 출력한다
            // binding.tv4.append(
            """
                    $language
                """.trimIndent()
            //textView에 language 값을 출력한다
            // )

        }
    }

    fun test2() {
        val jsonString = "{\"name\":\"Alice\",\"age\":30}";

        val jsonObject = JSONObject(jsonString);
        val name = jsonObject.getString("name");
        val age = jsonObject.getInt("age");

        println("Name: $name, Age: $age");
    };

    fun test4() {
        val jsonString = "{\"name\":\"Alice\",\"age\":30}";

        val objectMapper = jacksonObjectMapper();
        val user: User = objectMapper.readValue(jsonString, User::class.java);

        println("Name: ${user.name}, Age: ${user.age}");
    };

    fun main() {
        print("Enter the Score");
        val score = readLine()!!.toDouble();
        var grade: Char = if (score >= 90.0) 'A';
        else if (score in 80.0..89.9) 'B';
        else if (score in 70.0..79.9) 'C';
        else 'F';
        println("Score : $score, Grade : $grade");

        var x = 13;

        when(x){
            in 1..10 -> println("x는 1 이상 10 이하입니다.")
            !in 10..20 -> println("x는 10 이상 20 이하의 범위에 포함되지 않습니다.")
        }

        var str = "hello";
        when(str) {
            is String -> println("문자열이다.");
            else -> println("문자열이 아니다.");
        };

        when (x) {
            1 -> println("x == 1");
            2 -> println("x == 2");
            in 5..10 -> println("x in 5..10");
            !in 5..10 -> println("x !in 5..10");
            else -> println("어떠한 범위에도 없다.");
        };
    };

    fun main2() {
        case("hello");
    };

    fun case(obj: Any) {
        when(obj){
            1 -> println(" is one");
            "hello" -> println(" is hello");
            !is String -> println(" is not String");
        };
    };
}


