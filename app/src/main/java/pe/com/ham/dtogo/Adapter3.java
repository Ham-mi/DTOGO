package pe.com.ham.dtogo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import pe.com.ham.dtogo.dao.Goal;


public class Adapter3 extends RecyclerView.Adapter<Adapter3.ViewHolder> {


    private final LayoutInflater mInflater;
    private List<Goal> mGData = null;

    OnItemClickListener mGListener = null;

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

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull Adapter3.ViewHolder holder, int position) {
        if(mGData != null){
            Goal current = mGData.get(position);
//            Log.d("ICON", "onBindViewHolder: " + current.getIcon());
            holder.name.setText(current.getTitle());
            holder.number.setText(current.getGoalnum());
            holder.unit.setText(getUnit(current.getState(), current.getGoalunit()));
            holder.percent.setText(getPercent(current.getGoalnow(), current.getGoalnum()));
            //배경색 변경
            ViewCompat.setBackgroundTintList(holder.view_color, ColorStateList.valueOf(Color.parseColor(current.getBack_color())));

        }
    }

    String getUnit(int state , int unit){

        String un = null;
        if(state == 0){
            if(unit == 0){
                un = "희";
            }
            else if(unit == 1){
                un = "개";
            }
            else{
                un = "장";
            }
        }
        else if(state == 1){
            if(unit == 0){
                un = "회";
            }
            else if(unit == 1){
                un = "번";
            }
        }
        else if(state == 2){
            if(unit == 0){
                un = "분";
            }
            else if(unit == 1){
                un = "일";
            }
            else{
                un = "시간";
            }
        }
        return un;
    }

    String getPercent(int now, int num){

        int pc = num / now * 100;
        String percent = String.format("("+pc+"%)");
        return percent;

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
