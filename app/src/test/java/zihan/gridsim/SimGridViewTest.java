package zihan.gridsim;

import static org.mockito.Mockito.verify;

import android.content.Context;
import android.media.Image;
import android.media.metrics.Event;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import androidx.appcompat.app.AppCompatActivity;


import junit.framework.TestCase;

import zihan.gridsim.Model.ImageAdapter;


@RunWith(MockitoJUnitRunner.class)
public class SimGridViewTest extends TestCase {

    @Mock
    Context mockContext;
    @Mock
    GridView mockGView;
    @Mock
    TextView mockTView;
    @Mock
    EventBus mockBus;
    @Mock
    ImageAdapter mockAdapter;

    @Test
    public void SimGridViewCallsSetAdapter() {
        SimGridView facade = SimGridView.getInstance(mockContext);
        facade.attach(mockTView, mockGView);
        ImageAdapter adapter = facade.getAdapter();
        verify(mockGView).setAdapter(adapter);
    }

    @Test
    public void SimGridViewRegisterEventBus() {
        SimGridView facade = SimGridView.getInstance(mockContext);
        facade.setEventBus(mockBus);
        facade.attach(mockTView, mockGView);
        verify(mockBus).register(facade);
    }

    @Test
    public void SimGridViewUnRegisterEventBus() {
        SimGridView facade = SimGridView.getInstance(mockContext);
        facade.setEventBus(mockBus);
        facade.attach(mockTView, mockGView);
        facade.detach();
        verify(mockBus).unregister(facade);
    }

    @Test
    public void SimGridViewSetUsingJSONUpdatesGrid() {
        SimGridView facade = SimGridView.getInstance(mockContext);
        facade.setAdapter(mockAdapter);
        facade.attach(mockTView, mockGView);
        String jsonStr = "{\"grid\":[[0,1000,1000,1000,2002,1000,3000,1000,1000,1000,0,0,0,1001466,0,0],[0,1000,3000,2002,0,1000,0,1000,1000,1000,0,0,0,0,0,0],[0,0,1000,1000,0,1000,0,1000,1000,1000,0,0,0,999274,0,0],[0,0,1000,1000,0,1000,0,3000,0,1000,0,0,0,0,9999600,0],[0,0,1500,2002,0,0,0,2002,0,1000,10020310,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,2003,2002,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,3000,0,0,0,0,0,999640,0,0],[0,0,0,0,2002,0,0,0,0,0,0,0,0,0,0,0],[10010022,0,0,0,0,2003,0,0,0,0,0,0,3000,0,0,0],[0,0,2002,0,0,0,0,0,0,0,0,2003,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,2002,0,0,0,0,0,0,0,0,0,0],[0,3000,0,2003,0,0,0,0,2002,0,0,0,3000,0,0,0]],\"tankFeatures\":[{\"tankID\":0,\"features\":[]},{\"tankID\":1,\"features\":[]},{\"tankID\":2,\"features\":[]}],\"timeStamp\":1727386722067}";
        try {
            JSONObject object = new JSONObject(jsonStr);
            JSONArray jsonArr = object.getJSONArray("grid");
            facade.setUsingJSON(jsonArr);
            verify(mockAdapter).notifyDataSetChanged();
        } catch (JSONException e) {
            // json exception
        }

    }

}