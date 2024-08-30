package zihan.gridsim;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //test toast
                //Toast.makeText(v.getContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
                Log.d("gridView", Integer.toString(position));
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