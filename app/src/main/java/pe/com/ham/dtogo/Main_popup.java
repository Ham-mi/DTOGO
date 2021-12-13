package pe.com.ham.dtogo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import pe.com.ham.dtogo.dao.Dday;
import pe.com.ham.dtogo.dao.DdayViewModel;
import pe.com.ham.dtogo.dao.Goal;
import pe.com.ham.dtogo.dao.GoalViewModel;
import pe.com.ham.dtogo.dao.Todo;
import pe.com.ham.dtogo.dao.TodoViewModel;

public class Main_popup extends AppCompatActivity implements LifecycleOwner{
    private DdayViewModel ddayViewModel;
    private TodoViewModel todoViewModel;
    private GoalViewModel goalViewModel;

    private Button allDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_popup);

        ddayViewModel = new ViewModelProvider(this).get(DdayViewModel.class);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);

        allDelete = findViewById(R.id.allDelete);

        allDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ddayViewModel.getmAllDday().observe(Main_popup.this, new Observer<List<Dday>>() {
                    @Override
                    public void onChanged(List<Dday> ddays) {
                        for(Dday dday : ddays) {
                            ddayViewModel.deleteDday(dday);
                        }
                    }
                });

                todoViewModel.getmAllTodo().observe(Main_popup.this, new Observer<List<Todo>>() {
                    @Override
                    public void onChanged(List<Todo> todos) {
                        for(Todo todo : todos){
                            todoViewModel.deleteTodo(todo);
                        }
                    }
                });

                goalViewModel.getmAllGoal().observe(Main_popup.this, new Observer<List<Goal>>() {
                    @Override
                    public void onChanged(List<Goal> goals) {
                        for(Goal goal : goals ){
                            goalViewModel.deleteGoal(goal);
                        }
                    }
                });
            }
        });
    }
}
