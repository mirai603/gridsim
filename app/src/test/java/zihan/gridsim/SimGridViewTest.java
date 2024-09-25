package zihan.gridsim;

import android.content.Context;
import android.widget.GridView;
import android.widget.TextView;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import androidx.appcompat.app.AppCompatActivity;


import junit.framework.TestCase;



public class SimGridViewTest extends TestCase {

    @Mock
    Context mockContext;

    public void testGetGridViewFromSimGridView() {
        SimGridView facade = SimGridView.getInstance(mockContext);
        // then what?
    }

}