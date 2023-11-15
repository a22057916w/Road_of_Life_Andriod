package com.bilab.lunsenluandroid.ui.home;

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
import com.bilab.lunsenluandroid.Adapter.RecycleViewHome;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.HomeModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private HomeViewModel pathViewModel;
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
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_track, container, false);

        RecyclerView courseRV = root.findViewById(R.id.idRVCourse);

        // Here, we have created new array list and added data to it
        ArrayList<HomeModel> homeModelArrayList = new ArrayList<HomeModel>();
        homeModelArrayList.add(new HomeModel("肺癌", "近數十年來臺灣地區肺癌病人有顯著增加的趨勢，而目前肺癌已是國人因為癌症死亡最常見的原因之一","80%", R.drawable.lungs));
        homeModelArrayList.add(new HomeModel("肝癌", "每年約13,000人死於慢性肝病、肝硬化及肝癌，慢性肝病及肝硬化為全國主要死因的第9位，肝癌則為全國主要癌症死因的第2位", "20%", R.drawable.liver));
        homeModelArrayList.add(new HomeModel("前列腺癌", "前列腺癌是好發於老年男性的惡性腫瘤，而其死亡率在65歲以後也有陡然上升的趨勢", "8%", R.drawable.prostate));
        homeModelArrayList.add(new HomeModel("直腸癌", "目前大腸直腸癌為台灣所有癌症死亡率第三名，更為癌症發生率第一位", "5%", R.drawable.rectum));
        homeModelArrayList.add(new HomeModel("乳癌", "目前乳癌發生率為國內女性好發癌症的第一位，死亡率則為第四位", "60%", R.drawable.breast));

        // we are initializing our adapter class and passing our arraylist to it.
        RecycleViewHome recycleViewHome = new RecycleViewHome(this.getActivity(), homeModelArrayList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(recycleViewHome);

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