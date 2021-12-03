package pe.com.ham.dtogo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    UsingDB db;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db = (UsingDB) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        ArrayList<String> list = new ArrayList<>();
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = view.findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext())) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        Adapter1 adapter = new Adapter1(list) ;
        recyclerView.setAdapter(adapter) ;

        return view;
    }
}
