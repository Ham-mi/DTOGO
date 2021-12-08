package pe.com.ham.dtogo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
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

import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.data.Icon;
import com.maltaisn.icondialog.pack.IconPack;
import com.maltaisn.icondialog.pack.IconPackLoader;
import com.maltaisn.iconpack.defaultpack.IconPackDefault;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

import pe.com.ham.dtogo.dao.Dday;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder>{

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
//            Log.d("ICON", "onBindViewHolder: " + current.getIcon());
            holder.title.setText(current.getTitle());

            if(current.getIcon()!=0) {
                IconPackLoader loader = new IconPackLoader(this.mInflater.getContext());
                IconPack iconPack;
                // Create an icon pack and load all drawables.
                iconPack = IconPackDefault.createDefaultIconPack(loader);
                iconPack.loadDrawables(loader.getDrawableLoader());
                Icon icon = iconPack.getIcon(current.getIcon());
//                Log.d("ICON", "onBindViewHolder: "+current.getIcon() +"" +iconPack.getIcon(current.getIcon()));
                if(icon != null) {
                    Drawable drawable = icon.getDrawable();
                    drawable.setColorFilter(Color.parseColor(current.getText_color()), PorterDuff.Mode.SRC_ATOP);

                    holder.icon.setImageDrawable(drawable);
                }
            }
            holder.start_date.setText(setDayFormat(current.getDate()));
            holder.now_date.setText(getDoDay(current.getDate(), current.getCalc()));

            //배경색 변경
            ViewCompat.setBackgroundTintList(holder.back_color, ColorStateList.valueOf(Color.parseColor(current.getBack_color())));

            //전체 텍스트 컬러 변경
            holder.title.setTextColor(Color.parseColor(current.getText_color()));
            holder.start_date.setTextColor(Color.parseColor(current.getText_color()));
            holder.now_date.setTextColor(Color.parseColor(current.getText_color()));
        }
    }

    void setmData(List<Dday> dday){
        mData = dday;
        notifyDataSetChanged();
    }

    String getDoDay(String start, int calc) {
        Calendar nCal = Calendar.getInstance();
        int nYear = Integer.parseInt(start.substring(0,4));
        int nMonth = Integer.parseInt(start.substring(4,6));
        int nDay = Integer.parseInt(start.substring(6,8));
//        Log.d("D", "getDoDay: " + nYear + " " + nMonth + " " + nDay);
        nCal.set(nYear,nMonth,nDay);
        Date date = nCal.getTime();

        Date cal = Calendar.getInstance().getTime();
//        int cYear = cal.get(Calendar.YEAR);
//        int cMonth = cal.get(Calendar.MONTH);
//        int cDay = cal.get(Calendar.DAY_OF_MONTH);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        long diffDays =( (cal.getTime() - date.getTime() ) / 1000 );
        diffDays /= (24 * 60 * 60 );

        if(calc == 0 ) {
            diffDays+=1; // 1일부터 시작
            return diffDays + "일";
        }
        else {
            return "D+" + diffDays;
        }

//        int tmp = Integer.parseInt(cDate) - Integer.parseInt(ndate);
//        Log.d("T", "getDoDay: " + diffDays + " now " + cal.getTime() + " start " + date.getTime());
//        return diffDays + "일";
    }

    String setDayFormat (String date ) {
        Calendar cal = Calendar.getInstance();
        int nYear = Integer.parseInt(date.substring(0,4));
        int nMonth = Integer.parseInt(date.substring(4,6));
        int nDay = Integer.parseInt(date.substring(6,8));
        cal.set(nYear,nMonth,nDay);

        String tmpMonth = (nMonth+1)<10 ? "0"+(nMonth+1) : String.valueOf((nMonth+1));
        String tmpDay = nDay<10 ? "0"+nDay : String.valueOf(nDay);

        String cShow = String.format(nYear+"."+tmpMonth+"."+tmpDay+"("+cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.KOREAN)+")");
        return cShow;
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
