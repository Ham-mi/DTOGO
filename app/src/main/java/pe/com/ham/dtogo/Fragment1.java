package pe.com.ham.dtogo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pe.com.ham.dtogo.dao.Dday;
import pe.com.ham.dtogo.dao.DdayViewModel;

public class Fragment1 extends Fragment implements ViewModelStoreOwner{
    private DdayViewModel mDdayViewModel;
    private FloatingActionButton btn;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        mDdayViewModel = new ViewModelProvider(this).get(DdayViewModel.class);
        btn = view.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Intent1_save.class);
                startActivity(intent);
            }
        });

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = view.findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext())) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        Adapter1 adapter = new Adapter1(this.getContext()) ;

        mDdayViewModel.getmAllDday().observe(getViewLifecycleOwner(), new Observer<List<Dday>>() {
            @Override
            public void onChanged(List<Dday> ddays) {
                adapter.setmData(ddays);
            }
        });

        recyclerView.setAdapter(adapter) ;

        return view;
    }
}
