package setc.com.learning1;

/**
 * Created by sathvi on 3/31/15.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    private String[] itemsData;
    private static Context sContext;
    private TextView mfocused;
    public MyAdapter(String[] itemsData, Context context, TextView focused) {
        this.itemsData = itemsData;
        this.sContext = context;
        this.mfocused = focused;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
// create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, null);
// create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        viewHolder.txtViewTitle.setOnClickListener(MyAdapter.this);
        viewHolder.txtViewTitle.setTag(viewHolder);
        return viewHolder;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
// - get data from your itemsData at this position
// - replace the contents of the view with that itemsData
        viewHolder.txtViewTitle.setText(itemsData[position]);

    }
    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtViewTitle;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);

        }
    }
    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {

        int len = (null == itemsData) ? 0 : itemsData.length;
        return len;
    }

    @Override
    public void onClick(View view){
            ViewHolder viewHolder = (ViewHolder) view.getTag();
        if(view.getId() == viewHolder.txtViewTitle.getId()){
            this.mfocused.setText(viewHolder.txtViewTitle.getText());
            System.out.println(viewHolder.txtViewTitle.getText());

        }
    }

}
