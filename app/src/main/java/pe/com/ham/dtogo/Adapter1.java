package pe.com.ham.dtogo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import pe.com.ham.dtogo.dao.Dday;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Dday> mData = null;
    private Date today = new Date();

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;
        TextView start_date;
        TextView now_date;

        ViewHolder(View itemView){
            super(itemView);

            icon = itemView.findViewById(R.id.item1_icon);
            title = itemView.findViewById(R.id.item1_title);
            start_date = itemView.findViewById(R.id.item1_start_date);
            now_date = itemView.findViewById(R.id.item1_now_date);
        }
    }

    Adapter1(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Adapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item1, parent, false) ;
        Adapter1.ViewHolder vh = new Adapter1.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1.ViewHolder holder, int position) {
        if(mData != null){
            Dday current = mData.get(position);
            holder.title.setText(current.getTitle());
            holder.icon.setImageResource(current.getIcon());
            holder.start_date.setText(current.getDate());
            holder.now_date.setText(getDoDay(today,current.getDate()));
        }
    }

    void setmData(List<Dday> dday){
        mData = dday;
        notifyDataSetChanged();
    }

    String getDoDay(Date today,String start) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
        String formatedNow = formatter.format(today);
        int tmp = Integer.parseInt(formatedNow) - Integer.parseInt(start);
        return tmp + "일";
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
