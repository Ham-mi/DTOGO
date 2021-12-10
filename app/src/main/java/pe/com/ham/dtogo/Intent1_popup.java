package pe.com.ham.dtogo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import pe.com.ham.dtogo.dao.Dday;
import pe.com.ham.dtogo.dao.DdayViewModel;

public class Intent1_popup extends AppCompatActivity {
    private DdayViewModel ddayViewModel;
    LinearLayout linearEdit, linearDelete;
    Intent intentDefault;
    Parcelable ddayP;
    Dday intentDday;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent1_popup);

        ddayViewModel = new ViewModelProvider(this).get(DdayViewModel.class);

        linearEdit = findViewById(R.id.linearEdit);
        linearDelete = findViewById(R.id.linearDelete);

        intentDefault = getIntent();
        ddayP = intentDefault.getParcelableExtra("dday");
        intentDday = (Dday) ddayP;
        if(intentDday != null) {
            Log.d("D","not null");
        }

        linearEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit = new Intent(getApplicationContext(),Intent1_save.class);
                intentEdit.putExtra("dday",ddayP);
                intentEdit.putExtra("done",0);
                startActivity(intentEdit);

            }
        });

        linearDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ddayViewModel.deleteDday(intentDday);
                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
