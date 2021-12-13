package pe.com.ham.dtogo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import pe.com.ham.dtogo.dao.Goal;
import pe.com.ham.dtogo.dao.GoalViewModel;

public class Intent3_popup extends AppCompatActivity {
    private GoalViewModel goalViewModel;

    TextView textView_name;
    LinearLayout linearDetail_g1,linearEdit_g1,linearDelete_g1;
    Intent intent;
    Parcelable goalP;
    Goal intentGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent3_popup);

        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);

        linearDetail_g1 = findViewById(R.id.linearDetail_g1);
        linearEdit_g1 = findViewById(R.id.linearEdit_g1);
        linearDelete_g1 = findViewById(R.id.linearDelete_g1);

        intent = getIntent();
        goalP = intent.getParcelableExtra("goal");
        intentGoal = (Goal) goalP;
        if(intentGoal != null){
            Log.d("G","not null");
        }

        linearDetail_g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail = new Intent(getApplicationContext(),Intent3_show.class);
                intentdetail.putExtra("goal",goalP);
                intentdetail.putExtra("done",0);
                startActivity(intentdetail);
            }
        });

        linearEdit_g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail = new Intent(getApplicationContext(),Intent3_save.class);
                intentdetail.putExtra("goal",goalP);
                intentdetail.putExtra("done",0);
                startActivity(intentdetail);
            }
        });

        linearDelete_g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalViewModel.deleteGoal(intentGoal);
                Toast.makeText(getApplicationContext(),"삭제되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
