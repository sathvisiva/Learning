package setc.com.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;
import java.util.ArrayList;


public class CardActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CardViewDataAdapter(context,createList(3));
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<BusDetails> createList(int size) {
        List<BusDetails> result = new ArrayList<BusDetails>();

            BusDetails ci = new BusDetails();
            ci.startTime = "05:00 am"; ci.departTime = "12:00 pm (09 hrs)"; ci.seats = 35; ci.busNo = "908c"; ci.corporation = "Thanjavur corp."; ci.fare = 550; ci.viaroute =  "salem"; ci.serviceClass = "ultra Deluxe";
            result.add(ci);
            BusDetails ci1 = new BusDetails();
            ci1.startTime = "05:00 am"; ci1.departTime = "12:00 pm (09 hrs)"; ci1.seats = 35; ci1.busNo = "908c"; ci1.corporation = "Thanjavur corp."; ci1.fare = 550; ci1.viaroute =  "salem"; ci1.serviceClass = "ultra Deluxe";
            result.add(ci1);
             BusDetails ci2 = new BusDetails();
            ci2.startTime = "05:00 am"; ci2.departTime = "12:00 pm (09 hrs)"; ci2.seats = 20; ci2.busNo = "908c"; ci2.corporation = "Thanjavur corp."; ci2.fare = 550; ci2.viaroute =  "salem"; ci2.serviceClass = "ultra Deluxe";
            result.add(ci2);
            BusDetails ci3 = new BusDetails();
            ci3.startTime = "05:00 am"; ci3.departTime = "12:00 pm (09 hrs)"; ci3.seats = 0; ci3.busNo = "908c"; ci3.corporation = "Thanjavur corp."; ci3.fare = 550; ci3.viaroute =  "salem"; ci3.serviceClass = "ultra Deluxe";
            result.add(ci3);

        return result;
    }
}
