package pe.com.ham.dtogo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pe.com.ham.dtogo.dao.Todo;
import pe.com.ham.dtogo.dao.TodoViewModel;

public class Fragment2 extends Fragment implements ViewModelStoreOwner {
    private TodoViewModel mTodoViewModel;
    private FloatingActionButton btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);

        mTodoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        btn = view.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Intent2.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        Adapter2 adapter2 = new Adapter2(this.getContext());

        adapter2.setOnItemClickListener(new Adapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Todo todo) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Intent2.class);
                intent.putExtra("todo", (Parcelable) todo);
                intent.putExtra("done", 0);
                intent.putExtra("number", todo.getNumber());
                intent.putExtra("state", todo.getState() );
                intent.putExtra("memo", todo.getMemo());
                intent.putExtra("date", todo.getDate());
                intent.putExtra("time", todo.getTime());
                intent.putExtra("use",todo.getUse());
                startActivity(intent);
            }

            @Override
            public void onItemUpdate(Todo todo) {
                mTodoViewModel.updateTodo(todo);
            }
        });

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
