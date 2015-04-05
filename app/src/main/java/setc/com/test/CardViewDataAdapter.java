package setc.com.test;

/**
 * Created by sathvi on 4/4/15.
 */
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import java.util.List;

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.BusViewHolder> {
    // Provide a suitable constructor (depends on the kind of dataset)
    private List<BusDetails> busDetailses;
    private Context context;
    public CardViewDataAdapter(Context context,List<BusDetails> busDetailses) {
            this.busDetailses = busDetailses;
            this.context = context;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return busDetailses.size();
    }

    @Override
    public void onBindViewHolder(BusViewHolder busViewHolder, int i) {
       
        
        BusDetails ci = busDetailses.get(i);
        if(ci.seats <= 20 && ci.seats != 0)
        {
            busViewHolder.seats.setTextColor(Color.parseColor("#FFc200"));
        }else if(ci.seats == 0){
            busViewHolder.seats.setTextColor(Color.parseColor("#9A9A8C"));
        }else{
            busViewHolder.seats.setTextColor(Color.parseColor("#00897B"));
        }

        busViewHolder.startTime.setText(ci.startTime);
        busViewHolder.departTime.setText(ci.departTime);
        busViewHolder.seats.setText(String.valueOf(ci.seats));
        busViewHolder.busNo.setText(ci.busNo);
        busViewHolder.corp.setText(ci.corporation);
        busViewHolder.fare.setText(String.valueOf(ci.fare));
        busViewHolder.via.setText(ci.viaroute);
        busViewHolder.sercviceClass.setText(ci.serviceClass);
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_row, viewGroup, false);
        return new BusViewHolder(itemView);
    }

    public static class BusViewHolder extends RecyclerView.ViewHolder {
        protected TextView startTime;
        protected TextView departTime;
        protected TextView seats ;
        protected TextView busNo;
        protected TextView corp;
        protected TextView fare;
        protected TextView via;
        protected TextView sercviceClass;
        public BusViewHolder(View v) {
            super(v);
            startTime = (TextView) v.findViewById(R.id.startTime);
            departTime = (TextView) v.findViewById(R.id.departTime);
            seats = (TextView) v.findViewById(R.id.seats);
            busNo = (TextView) v.findViewById(R.id.busNo);
            corp = (TextView) v.findViewById(R.id.corporation);
            fare = (TextView) v.findViewById(R.id.fare);
            via = (TextView) v.findViewById(R.id.via);
            sercviceClass = (TextView) v.findViewById(R.id.serviceClass);
        }
    }
}