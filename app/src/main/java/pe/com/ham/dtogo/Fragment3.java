package pe.com.ham.dtogo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import pe.com.ham.dtogo.dao.Goal;
import pe.com.ham.dtogo.dao.GoalViewModel;

public class Fragment3 extends Fragment implements ViewModelStoreOwner{

    private GoalViewModel mGoalViewModel;
    private FloatingActionButton btn1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);

        mGoalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
        btn1 = view.findViewById(R.id.fab3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Intent3_save.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        Adapter3 adapter3 = new Adapter3(this.getContext());

        adapter3.setOnItemClickListener(new Adapter3.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Goal goal) {
                Intent intentPop3 = new Intent(getActivity().getApplicationContext(),Intent3_popup.class);
                intentPop3.putExtra("goal",(Parcelable) goal);
                startActivity(intentPop3);
            }
        });

        mGoalViewModel.getmAllGoal().observe(getViewLifecycleOwner(), new Observer<List<Goal>>() {
            @Override
            public void onChanged(List<Goal> goals) {
                adapter3.setmGData(goals);
            }
        });

        recyclerView.setAdapter(adapter3);


        return view;
    }
}
