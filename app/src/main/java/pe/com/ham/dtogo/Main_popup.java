package pe.com.ham.dtogo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import pe.com.ham.dtogo.dao.DdayViewModel;
import pe.com.ham.dtogo.dao.GoalViewModel;
import pe.com.ham.dtogo.dao.TodoViewModel;

public class Main_popup extends AppCompatActivity {
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
            }
        });
    }
}
