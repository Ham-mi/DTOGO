package pe.com.ham.dtogo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import pe.com.ham.dtogo.dao.Dday;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Dday> mData = null;


    OnItemClickListener mListener = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout back_color;
        ImageView icon;
        TextView title;
        TextView start_date;
        TextView now_date;

        ViewHolder(View itemView){
            super(itemView);

            back_color = itemView.findViewById(R.id.linearColor);
            icon = itemView.findViewById(R.id.item1_icon);
            title = itemView.findViewById(R.id.item1_title);
            start_date = itemView.findViewById(R.id.item1_start_date);
            now_date = itemView.findViewById(R.id.item1_now_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Dday dday = mData.get(pos);
                        if (mListener != null) {
                            mListener.onItemClick(v, pos, dday);
                        }
                    }
                }
            });
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

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull Adapter1.ViewHolder holder, int position) {
        if(mData != null){
            Dday current = mData.get(position);
            holder.title.setText(current.getTitle());
            holder.icon.setImageResource(current.getIcon());
            holder.start_date.setText(current.getDate());
            holder.now_date.setText(getDoDay(current.getDate()));
            ViewCompat.setBackgroundTintList(holder.back_color, ColorStateList.valueOf(Color.parseColor(current.getBack_color())));
        }
    }

    void setmData(List<Dday> dday){
        mData = dday;
        notifyDataSetChanged();
    }

    String getDoDay(String start) {
        Calendar nCal = Calendar.getInstance();
        int nYear = Integer.parseInt(start.substring(0,4));
        int nMonth = Integer.parseInt(start.substring(3,5));
        int nDay = Integer.parseInt(start.substring(5,7));
        Log.d("D", "getDoDay: " + nYear + " " + nMonth + " " + nDay);
        nCal.set(nYear,nMonth-1,nDay);
        Date date = nCal.getTime();

        Date cal = Calendar.getInstance().getTime();
//        int cYear = cal.get(Calendar.YEAR);
//        int cMonth = cal.get(Calendar.MONTH);
//        int cDay = cal.get(Calendar.DAY_OF_MONTH);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        long diffDays =( (cal.getTime() - date.getTime() ) / 1000 );
        diffDays /= (24 * 60 * 60 );

//        int tmp = Integer.parseInt(cDate) - Integer.parseInt(ndate);
//        Log.d("T", "getDoDay: " + tmp + " now " + cDate + " start " + ndate);
        return diffDays + "일";
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Dday dday);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
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
