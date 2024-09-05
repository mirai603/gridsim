package zihan.gridsim;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zihan.gridsim.Model.GridCell;
import zihan.gridsim.Model.ImageAdapter;
import zihan.gridsim.Model.SimulationGrid;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SimulationGrid gridData = new SimulationGrid(16, 16);

        ImageAdapter adapter = new ImageAdapter(this, gridData);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://stman1.cs.unh.edu:6191/games";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("HTTP",  response);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArr = object.getJSONArray("grid");

                            gridData.setUsingJSON(jsonArr);
                            adapter.notifyDataSetChanged();
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

        // Add the request to the RequestQueue.
        queue.add(stringRequest);



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //test toast
                //Toast.makeText(v.getContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
                Log.d("gridView", Integer.toString(position));
                Log.d("gridView", gridData.getCell(position).toString());
            }
        });
    }


    public void button1(View v) {
        Toast.makeText(this, "I'm Button 1", Toast.LENGTH_LONG).show();
    }

    public void button2(View v) {
        Toast.makeText(this, "I'm Button 2", Toast.LENGTH_LONG).show();
    }

    public void logtest(View v, int pos) {
        Toast.makeText(this, pos, Toast.LENGTH_LONG).show();
    }
}