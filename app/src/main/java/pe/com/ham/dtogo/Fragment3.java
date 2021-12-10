package pe.com.ham.dtogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        return view;
    }
}
