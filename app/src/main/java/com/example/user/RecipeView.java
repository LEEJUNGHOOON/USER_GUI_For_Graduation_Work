package com.example.user;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class RecipeView extends Fragment {


    MainActivity mainActivity;
    String result;
    recipe_data[] recipeList;
    int index;

    // 메인 액티비티 위에 올린다.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    // 메인 액티비티에서 내려온다.
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 이 하단 블럭은 테스트위해 임시로 제작한 레시피 데이터셋 더미


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recipe_view, container, false);

        Button button = rootView.findViewById(R.id.tobc);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.fragmentChange(2);
            }

        });

        Button button2 = rootView.findViewById(R.id.strt);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Httpjson httpjson;

                //이부분에 spring에서 레시피 받아오는 로직추가

                httpjson = new Httpjson();
                try {

                    String[] tmp_recipe ;
                    String[] tmp_item = {"1","2","3"};
                    int tmp_maxRecipe = 5;
                    int tmp_maxItem = 3;
                    JSONObject jsonhttpjson = new Httpjson().execute("").get();
                    Log.d("TAG", String.valueOf(jsonhttpjson));
                    tmp_recipe = getStringvalues(jsonhttpjson);

                    recipe_data tmp = new recipe_data(tmp_item,tmp_recipe,tmp_maxRecipe, tmp_maxItem);
                    mainActivity.frameLayout6.setData(tmp);
                    mainActivity.fragmentChange(6);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });


        return rootView;

    }

    public void send_result (String result){
        this.result = result;
    }

    public int howmanypages(JSONObject jsonObject){
        int i=0;
        try {
            JSONArray arr = (JSONArray)jsonObject.get("recipe");
            for(i=0;i<arr.length();i++){
                Log.d("AG", String.valueOf(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return i;
    }

    public String[] getStringvalues(JSONObject jsonObject){
        Log.d("AG", "함수호출됨");
        List<String> recipeString = new ArrayList<String>();
        try {
            JSONArray arr = (JSONArray)jsonObject.get("recipe");
            List<JSONObject> copyList = new ArrayList<JSONObject>();

            for (int i=0; i<arr.length(); i++){
                copyList.add((JSONObject) arr.get(i)); // list 에 삽입 실시
            }

            for (JSONObject item:copyList) {
                recipeString.add(String.valueOf(item.get("COOKING_DC")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] recipespringarray = recipeString.toArray(new String[0]);
        Log.d("s", Arrays.toString(recipespringarray));
        return recipespringarray;
    }

}

