package pe.com.ham.dtogo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import pe.com.ham.dtogo.dao.Goal;


public class Adapter3 extends RecyclerView.Adapter<Adapter3.ViewHolder> {


    private final LayoutInflater mInflater;
    private List<Goal> mGData = null;

    OnItemClickListener mGListener = null;

    int width;

    public class ViewHolder extends RecyclerView.ViewHolder{

        View view_color;
        TextView name;
        TextView number;
        TextView unit;
        TextView percent;

        ViewHolder(View itemView){
            super(itemView);

            view_color = itemView.findViewById(R.id.colorview_1);
            name = itemView.findViewById(R.id.goalname);
            number = itemView.findViewById(R.id.goalnumber);
            unit = itemView.findViewById(R.id.goalunit);
            percent = itemView.findViewById(R.id.goal_percent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gap = getAdapterPosition();
                    if(gap != RecyclerView.NO_POSITION){
                        Goal goal = mGData.get(gap);
                        if(mGListener != null){
                            mGListener.onItemClick(v,gap,goal);
                        }
                    }
                }
            });
        }

    }

    Adapter3(Context context) { mInflater = LayoutInflater.from(context);}

    @Override
    public Adapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.recyclerview_item3,parent,false);
        Adapter3.ViewHolder viewHolder = new Adapter3.ViewHolder(view);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull Adapter3.ViewHolder holder, int position) {
        if(mGData != null){
            Goal current = mGData.get(position);
            Display display = mInflater.getContext().getDisplay();
            Point size = new Point();
            display.getRealSize(size);
            width = size.x;
            holder.name.setText(current.getTitle());
            holder.number.setText(String.valueOf(current.getGoalnum()));
            holder.unit.setText(getUnit(current.getState(), current.getGoalunit()));
            holder.percent.setText(getPercent(current.getGoalnow(), current.getGoalnum()));
            holder.view_color.setLayoutParams(new LinearLayout.LayoutParams(getParams(current.getGoalnow(), current.getGoalnum()), LinearLayout.LayoutParams.MATCH_PARENT));
            ViewCompat.setBackgroundTintList(holder.view_color, ColorStateList.valueOf(Color.parseColor(current.getBack_color())));

        }
    }

    Integer getParams(int now, int num){

        if(now == 0){
            return 0;
        }
        else{
            int lg = (int)((double)now/(double)num * width);
            Log.d("값",String.valueOf(lg));
            return lg;
        }
    }

    String getUnit(int state , int unit){

        String un = null;
        if(state == 1){
            if(unit == 1){
                un = "회";
            }
            else if(unit == 2){
                un = "개";
            }
            else{
                un = "장";
            }
        }
        else if(state == 2){
            if(unit == 1){
                un = "회";
            }
            else if(unit == 2){
                un = "번";
            }
        }
        else if(state == 3){
            if(unit == 1){
                un = "시간";
            }
            else if(unit == 2){
                un = "분";
            }
            else{
                un = "일";
            }
        }
        return un;
    }

    String getPercent(int now, int num){
        if(now == 0){
            String zero = "(0%)";
            return zero;
        }
        else{
            int pc = (int)((double)now/(double)num *100.0);
            Log.d("값",String.valueOf(pc));
            String percent = String.format("("+pc+"%%)");
            return percent;
        }
    }
    void setmGData(List<Goal> goal){
        mGData = goal;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Goal goal);
    }

    public void setOnItemClickListener(Adapter3.OnItemClickListener mGListener){
        this.mGListener = mGListener;
    }

    @Override
    public int getItemCount(){
        if(mGData != null){
            return mGData.size();
        }
        else{
            return 0;
        }
    }
}
