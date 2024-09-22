package zihan.gridsim;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;




public class Poller {
    Runnable task;
    String url;

    private static Poller _instance = null;

    public static Poller getInstance(Context c, SimGridView facade) {
        if (_instance == null) {
            _instance = new Poller(c, facade);
        }
        return _instance;
    }

    protected Poller(Context c, SimGridView facade) {
        url = "http://stman1.cs.unh.edu:6191/games";
        RequestQueue queue = Volley.newRequestQueue(c);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArr = object.getJSONArray("grid");
                            Log.d("HTTP", "Response Received");
                            EventBus.getDefault().post(new PollEvent(jsonArr));
                        } catch (JSONException e) {
                            Log.d("JSON ERR", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP ERR","That didn't work!");
            }
        });


        queue.add(new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("POST", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));




        // Add the request to the RequestQueue.
        task = new Runnable() {
            @Override
            public void run() {
                queue.add(stringRequest);

            }
        };

        ScheduledThreadPoolExecutor sch = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        sch.scheduleWithFixedDelay(task, 500, 500, TimeUnit.MILLISECONDS);

    }


}
