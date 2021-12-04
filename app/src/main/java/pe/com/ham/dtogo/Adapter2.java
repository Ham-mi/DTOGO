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

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    private ArrayList<String> mData = null;
    int [] ImageId = {R.drawable.baseline_crop_square_24, R.drawable.baseline_check_24, R.drawable.baseline_close_24};

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView textView1;
        int imageNumber = 0;

        ViewHolder(View itemView){
            super(itemView);

            textView1 = itemView.findViewById(R.id.fragment2_text1);
            iv = itemView.findViewById(R.id.fragment2_state_icon);
            iv.setOnClickListener(v -> {
                iv.setImageResource(ImageId[imageNumber]);
                imageNumber += 1;
                if(imageNumber == ImageId.length) imageNumber = 0;
            });
        }
    }

    Adapter2(ArrayList<String> list) {
        mData = list;
    }

    @NonNull
    @Override
    public Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.recyclerview_item2,  parent, false);
        Adapter2.ViewHolder vh = new Adapter2.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.textView1.setText(text);
        holder.iv.setImageResource(ImageId[0]);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
