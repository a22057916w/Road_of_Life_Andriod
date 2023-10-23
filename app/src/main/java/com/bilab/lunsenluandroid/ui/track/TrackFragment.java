package com.bilab.lunsenluandroid.ui.track;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bilab.lunsenluandroid.Adapter.CourseAdapter;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.CourseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

public class TrackFragment extends Fragment {

    private TrackViewModel pathViewModel;
    private WebView webView;
    TableRow tablerow_error;
    TextView choose;

    private static RequestQueue objpostqueue;
    private static StringRequest postRequest;
    private final static String strposturl_check_health_passport = "https://lunsenlu.cf/health_passport/check_health_passport";//"https://140.124.183.197:8000/health_passport/check_health_passport";
    String request_result,target_email,target_password;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pathViewModel =
                ViewModelProviders.of(this).get(TrackViewModel.class);
        View root = inflater.inflate(R.layout.fragment_track, container, false);

        RecyclerView courseRV = root.findViewById(R.id.idRVCourse);

        // Here, we have created new array list and added data to it
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();
        courseModelArrayList.add(new CourseModel("子宮癌", "根據統計顯示，子宮頸癌發生率的排名為女性癌症第一位","- -", R.drawable.heart));
        courseModelArrayList.add(new CourseModel("卵巢癌", "部份的卵巢癌在早期都沒有明顯的症狀，所以死亡率佔婦科癌症中相當高的比例", "- -", R.drawable.pregnant));
        courseModelArrayList.add(new CourseModel("膀胱癌", "膀胱癌是一種生長於膀胱的惡性腫瘤，好發於五十至七十歲之年齡層，百分之九十八膀胱癌屬上皮細胞來源", "- -", R.drawable.bladder));
        courseModelArrayList.add(new CourseModel("大腸癌", "根據衛福部的統計，在臺灣每31分14秒就有1人被診斷為大腸癌。", "- -", R.drawable.rectum));

        // we are initializing our adapter class and passing our arraylist to it.
        CourseAdapter courseAdapter = new CourseAdapter(this.getActivity(), courseModelArrayList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

//        webView = root.findViewById(R.id.fragment_path_webView);
//        tablerow_error = root.findViewById(R.id.fragment_path_error);
//        choose = root.findViewById(R.id.track_choose);
//
//        //objpostqueue = Volley.newRequestQueue(root.getContext());
//        //HTTPSTrustManager.allowAllSSL();
//
//        //生成SSLSocketFactory
//        SSLSocketFactory sslSocketFactory = HTTPSTrustManager.initSSLSocketFactory(getActivity());
//        //HurlStack两个参数默认都是null,如果传入SSLSocketFactory，那么会以Https的方式来请求网络
//        HurlStack stack = new HurlStack(null, sslSocketFactory);
//        //传入处理Https的HurlStack
//        objpostqueue = Volley.newRequestQueue(this.getActivity(),stack);
//        initial();
//        objpostqueue.add(postRequest);
//
//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDomStorageEnabled(true);
//        webView.setWebViewClient(
//                new SSLTolerentWebViewClient()
//        );
//
//        //webView.loadUrl("https://www.bbc.com");
//        //webView.loadUrl("https://140.124.183.188:2222/LSLDoctor/login/")
//        //webView.loadUrl("https://1qigvox2btm9q6ppyry9wq-on.drv.tw/MyWebsite/temp/");
//        //webView.loadUrl("https://1qigvox2btm9q6ppyry9wq-on.drv.tw/MyWebsite/kidding/mobile.html");
//        webView.loadUrl("https://www.google.com.tw/");
//        webView.flingScroll(0,500);
//        tablerow_error.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), HealthPassActivity.class);
//                intent.putExtra("activity", "main");
//                startActivity(intent);
//            }
//        });
//        choose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), HealthPassActivity.class);
//                intent.putExtra("activity", "main");
//                startActivity(intent);
//            }
//        });
//        /*final TextView textView = root.findViewById(R.id.text_home);
//        pathViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });*/


        return root;
    }


    public void initial(){
        String masterKeyAlias = null;
            try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    "RoadOfLifeAccount", masterKeyAlias, getActivity(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            target_email = sharedPreferences.getString("email", "");
            target_password = sharedPreferences.getString("password", "");
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        /*SharedPreferences sh = this.getActivity().getSharedPreferences("RoadOfLifeAccount", Context.MODE_PRIVATE);
        target_email = sh.getString("email","");
        target_password = sh.getString("password","");
        Log.v("bbb",target_password);*/
        postRequest = new StringRequest(Request.Method.POST, strposturl_check_health_passport,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response，表示是回傳值，就是API要回傳的字串，也可以是JSON字串。
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            request_result = jsonObject.getString("result");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(request_result.equals("fail")) {
                            Log.v("error account checkid ", "account checkid  error");
                            tablerow_error.setVisibility(View.VISIBLE);
                        }
                        else{
                            Log.v("success account checkid", "account checkid  success");
                            tablerow_error.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //如果發生錯誤，就是回傳VolleyError，可以顯示是什麼錯誤。
                Log.v("error",error.getMessage());
            }
        }) {
            @Override
            protected HashMap<String,String> getParams()
                    throws AuthFailureError {

                //跟GET模式不同的是，後續要加入傳入參數的函式。
                HashMap<String,String> hashMap = new HashMap<String,String>();

                hashMap.put("email",target_email);
                hashMap.put("password",target_password);
                return hashMap;
            }
        };
    }
}