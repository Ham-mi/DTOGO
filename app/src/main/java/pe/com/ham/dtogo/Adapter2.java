package pe.com.ham.dtogo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
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

    OnItemClickListener mListener = null;
    OnItemLongClickListener mlListener = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView state_icon;
        TextView text1;
        TextView dday;
        ImageView next_icon;

        int imageNumber = 0;

        ViewHolder(View itemView){
            super(itemView);

            state_icon = itemView.findViewById(R.id.fragment2_state_icon);
            text1 = itemView.findViewById(R.id.fragment2_text1);
            dday = itemView.findViewById(R.id.fragment2_D_Day);
            next_icon = itemView.findViewById(R.id.fragment2_next_icon);

            state_icon.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                Todo todo = null;
                if(pos != RecyclerView.NO_POSITION){
                    todo = mData.get(pos);
                    }
                state_icon.setImageResource(ImageId[imageNumber]);
                imageNumber += 1;
                if(imageNumber == ImageId.length) imageNumber = 0;

                if(imageNumber==0){
                    state_icon.setImageTintList(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorBlue)));
                    text1.setTextColor(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorBlue)));
                    dday.setTextColor(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorBlue)));
                    next_icon.setImageTintList(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorBlue)));

                    text1.setPaintFlags(0);
                }else if(imageNumber == 1 ){
                    state_icon.setImageTintList(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorLightBlue)));
                    text1.setTextColor(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorLightBlue)));
                    dday.setTextColor(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorLightBlue)));
                    next_icon.setImageTintList(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorLightBlue)));

                    text1.setPaintFlags(text1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    state_icon.setImageTintList(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorRed)));
                    text1.setTextColor(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorRed)));
                    dday.setTextColor(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorRed)));
                    next_icon.setImageTintList(ColorStateList.valueOf(mInflater.getContext().getResources().getColor(R.color.colorRed)));

                    text1.setPaintFlags(text1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                }

                if(todo!=null) {
                    todo.setState(imageNumber);
                    if(mListener!= null) {
                        mListener.onItemUpdate(todo);
                    }
                }
            });



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Todo todo = mData.get(pos);
                        if(mListener != null) {
                            mListener.onItemClick(v,pos,todo);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Todo todo = mData.get(pos);
                        if(mlListener != null) {
                            mlListener.onItemLongClick(v,pos,todo);
                        }
                    }
                    return false;
                }
            });
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
//            holder.dday.setText(getDoday(today, current.getDate()));
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

    public interface OnItemClickListener{
        void onItemClick(View v, int position, Todo todo);
        void onItemUpdate(Todo todo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {this.mListener = listener;}

    public interface OnItemLongClickListener{
        void onItemLongClick(View v, int position, Todo todo);
    }

    public void setOnItenLongClickListener(OnItemLongClickListener listener) {this.mlListener = listener;}

    @Override
    public int getItemCount() {
        if(mData != null) {
            return mData.size();
        }else{
            return 0 ;
        }
    }
}
