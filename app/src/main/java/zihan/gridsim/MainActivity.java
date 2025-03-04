package zihan.gridsim;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SimGridView facade;

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        facade = SimGridView.getInstance(this);
        facade.attach(findViewById(R.id.gridData), findViewById(R.id.gridview));
        Poller poller = Poller.getInstance(this, facade);
    }

    @Override
    protected void onStop() {
        super.onStop();
        facade = SimGridView.getInstance(this);
        facade.detach();
    }

    public void button1(View v) {
        Toast.makeText(this, "I'm Button 1", Toast.LENGTH_LONG).show();
    }

    public void button2(View v) {
        boolean paused = facade.pause();
        if (paused) {
            Toast.makeText(this, "Pause Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resume Update", Toast.LENGTH_SHORT).show();
        }

    }


}