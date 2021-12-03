package pe.com.ham.dtogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2,container,false);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i<100;i++)
        {
            list.add(String.format("TEXT %d", i));

        }
        RecyclerView recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        Adapter2 adapter2 = new Adapter2(list);
        recyclerView.setAdapter(adapter2);

        return view;
    }
}
