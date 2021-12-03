package pe.com.ham.dtogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {

    private ArrayList<String> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;
        TextView start_date;
        TextView now_date;

        ViewHolder(View itemView){
            super(itemView);

            icon = itemView.findViewById(R.id.item1_icon);
            title = itemView.findViewById(R.id.textView);
            start_date = itemView.findViewById(R.id.item1_start_date);
            now_date = itemView.findViewById(R.id.TextView3);
        }
    }

    Adapter1(ArrayList<String> list) {
        mData = null;
    }

    @Override
    public Adapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item1, parent, false) ;
        Adapter1.ViewHolder vh = new Adapter1.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1.ViewHolder holder, int position) {
        String text = mData.get(position) ;
        holder.title.setText(text) ;
    }

    @Override
    public int getItemCount() {
        return 0 ;
    }
}
