package pe.com.ham.dtogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.ham.dtogo.dao.Todo;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Todo> mData = null;
    private Date today = new Date();
    int [] ImageId = {R.drawable.baseline_crop_square_24, R.drawable.baseline_check_24, R.drawable.baseline_close_24};

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView state_icon;
        TextView text1;
        TextView dday;

        int imageNumber = 0;

        ViewHolder(View itemView){
            super(itemView);

            state_icon = itemView.findViewById(R.id.fragment2_state_icon);
            state_icon.setOnClickListener(v -> {
                state_icon.setImageResource(ImageId[imageNumber]);
                imageNumber += 1;
                if(imageNumber == ImageId.length) imageNumber = 0;
            });

            text1 = itemView.findViewById(R.id.fragment2_text1);
            dday = itemView.findViewById(R.id.fragment2_D_Day);
        }
    }

    Adapter2(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item2,  parent, false);
        Adapter2.ViewHolder vh = new Adapter2.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.ViewHolder holder, int position) {
        if(mData !=null) {
            Todo current = mData.get(position);
            holder.text1.setText(current.getMemo());
            holder.state_icon.setImageResource(ImageId[current.getState()]);
            holder.dday.setText(getDoday(today, current.getDate()));
        }
    }

    void setmData(List<Todo> todo){
        mData = todo;
        notifyDataSetChanged();
    }

    String getDoday(Date today, String start){
        SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
        String formatedNow = format.format(today);
        int tmp = Integer.parseInt(formatedNow) - Integer.parseInt(start);
        if(tmp<0){ return "";}
        else { return "+" + tmp; }
    }

    @Override
    public int getItemCount() {
        if(mData != null) {
            return mData.size();
        }else{
            return 0 ;
        }
    }
}
