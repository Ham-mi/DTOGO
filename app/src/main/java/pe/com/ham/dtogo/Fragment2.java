package pe.com.ham.dtogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.com.ham.dtogo.dao.Todo;
import pe.com.ham.dtogo.dao.TodoViewModel;

public class Fragment2 extends Fragment implements ViewModelStoreOwner {
    private TodoViewModel mTodoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);

        mTodoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        Adapter2 adapter2 = new Adapter2(this.getContext());

        mTodoViewModel.getmAllTodo().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter2.setmData(todos);
            }
        });

        recyclerView.setAdapter(adapter2);

        return view;
    }
}
