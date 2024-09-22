package zihan.gridsim;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;


import zihan.gridsim.Model.ImageAdapter;
import zihan.gridsim.Model.SimulationGrid;


public class SimGridView {
    private SimulationGrid gridData;
    private ImageAdapter adapter;
    private TextView textView;
    private GridView gridView;
    private int currentPosition;
    private boolean paused = false;

    @SuppressLint("StaticFieldLeak")
    public static SimGridView _instance = null;

    public static SimGridView getInstance(Context c) {
        if (_instance == null) {
            _instance = new SimGridView(c);
        }
        return _instance;
    }

    protected SimGridView(Context c) {
        this.gridData = new SimulationGrid(16, 16);
        this.adapter = new ImageAdapter(c, gridData);
    }

    public void attach(TextView tview, GridView gview) {
        this.textView = tview;
        this.gridView = gview;
        this.gridView.setAdapter(adapter);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Log.d("gridView", Integer.toString(position));
                Log.d("gridView", gridData.getCell(position).getCellInfo());
                currentPosition = position;
                updateText(position);
            }
        });
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    public void detach() {
        return;
    }

    public boolean pause() {
        if (!paused) {
            paused = true;
            return true;
        } else {
            paused = false;
            return false;
        }
    }

    @Subscribe
    public void onPoll(PollEvent event) {
        if (!paused) {
            setUsingJSON(event.arr);
            updateText(currentPosition);
        }

    }

    public void updateText(int position) {
        String displayText = gridData.getCell(position).getCellType() + "\n" + gridData.getCell(position).getCellInfo();
        textView.setText(displayText);
    }

    public void setUsingJSON(JSONArray arr) {

        try {
            gridData.setUsingJSON(arr);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.d("JSON ERR", e.toString());
        }

    }
}
