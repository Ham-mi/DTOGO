package pe.com.ham.dtogo;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Pattern;

import pe.com.ham.dtogo.dao.Goal;
import pe.com.ham.dtogo.dao.GoalViewModel;

public class Intent3_show extends  AppCompatActivity implements ViewModelStoreOwner{

    private GoalViewModel goalViewModel;
    InputMethodManager inputMethodManager;

    private ImageView imageBack_gd1;
    private TextView textSave_gd1;
    private EditText editTitle_gd1;
    private TextView goalnumber1, goalunit1, goal_percent1;
    private View colorview_gd1;
    private TextView goaln1, goaln2;
    private FloatingActionButton fab4, fab5;
    private EditText edit_goalnum_d;

    int state, unit, increase;
    boolean done = true;
    Parcelable goalP;
    Goal intentGoal;
    int width;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent3_show);

        getSupportActionBar().hide();

        imageBack_gd1 = findViewById(R.id.imageBack_gd1);
        textSave_gd1 = findViewById(R.id.textSave_gd1);
        editTitle_gd1 = findViewById(R.id.editTitle_gd1);
        goalnumber1 = findViewById(R.id.goalnumber1);
        goalunit1 = findViewById(R.id.goalunit1);
        goalnumber1 = findViewById(R.id.goalnumber1);
        goal_percent1 = findViewById(R.id.goal_percent1);
        colorview_gd1 = findViewById(R.id.colorview_gd1);
        goaln1 = findViewById(R.id.goaln1);
        goaln2 = findViewById(R.id.goaln2);
        fab4 = findViewById(R.id.fab4);
        fab5 = findViewById(R.id.fab5);
        edit_goalnum_d = findViewById(R.id.edit_goalnum_d);
        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        Intent intent = getIntent();

        goalP = intent.getParcelableExtra("goal");
        intentGoal = (Goal) goalP;
        if(intentGoal != null){
            Log.d("D","not null");
        }
        editTitle_gd1.setHint(intentGoal.getTitle());
        goalnumber1.setText(String.valueOf(intentGoal.getGoalnum()));
        colorview_gd1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(intentGoal.getBack_color())));
        increase = Integer.parseInt(String.valueOf(intentGoal.getIncrease()));
        state = Integer.parseInt(String.valueOf(intentGoal.getState()));
        unit = Integer.parseInt(String.valueOf(intentGoal.getGoalunit()));

        if(increase == 0){
            goaln1.setText("0");
            goaln2.setText(String.valueOf(intentGoal.getGoalnum()));
        }
        else if(increase == 1){
            goaln2.setText("0");
            goaln1.setText(String.valueOf(intentGoal.getGoalnum()));
        }

        if(state == 1){
            if(unit == 1){
                goalunit1.setText("???");
            }
            else if(unit == 2){
                goalunit1.setText("???");
            }
            else{
                goalunit1.setText("???");
            }
        }
        else if(state == 2){
            if(unit == 1){
                goalunit1.setText("???");
            }
            else if(unit == 2){
                goalunit1.setText("???");
            }
        }
        else if(state == 3){
            if(unit == 1){
                goalunit1.setText("??????");
            }
            else if(unit == 2){
                goalunit1.setText("???");
            }
            else{
                goalunit1.setText("???");
            }
        }
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        width = size.x;

        edit_goalnum_d.setText(String.valueOf(intentGoal.getGoalnow()));
        String gT = edit_goalnum_d.getText().toString();
        goal_percent1.setText(getPercent(Integer.parseInt(gT),intentGoal.getGoalnum()));
        colorview_gd1.setLayoutParams(new LinearLayout.LayoutParams(getParams(intentGoal.getGoalnow(),intentGoal.getGoalnum()),LinearLayout.LayoutParams.MATCH_PARENT));

        imageBack_gd1.setOnClickListener(v -> {
            Intent intents = new Intent(getApplicationContext(),MainActivity.class);
            intents.putExtra("tab",3);
            startActivity(intents);
        });

        editTitle_gd1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(50) });

        editTitle_gd1.setOnEditorActionListener((v, actionId, event) -> {
            boolean hanled = false;

            if(actionId == EditorInfo.IME_ACTION_DONE){
                inputMethodManager.hideSoftInputFromWindow(editTitle_gd1.getWindowToken(),0);
                hanled = true;
            }
            return hanled;
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(edit_goalnum_d.getText().toString())==0){
                    Toast.makeText(getApplicationContext(),"0?????? ?????? ????????? ??? ????????????.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int gn = Integer.parseInt(edit_goalnum_d.getText().toString());
                    gn -= 1;
                    edit_goalnum_d.setText(String.valueOf(gn));
                }
            }
        });

        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(edit_goalnum_d.getText().toString())==9999){
                    Toast.makeText(getApplicationContext(),"4?????? ?????? ????????? ??? ????????????.",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Integer.parseInt(edit_goalnum_d.getText().toString())==Integer.parseInt(goalnumber1.getText().toString())){
                        Toast.makeText(getApplicationContext(),"??????????????? ?????? ????????? ??? ????????????.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        int gn = Integer.parseInt(edit_goalnum_d.getText().toString());
                        gn += 1;
                        edit_goalnum_d.setText(String.valueOf(gn));
                    }
                }
                if(Integer.parseInt(edit_goalnum_d.getText().toString())==Integer.parseInt(goalnumber1.getText().toString())){
                    Toast.makeText(getApplicationContext(),"??????????????? ?????? ????????? ??? ????????????.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit_goalnum_d.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Pattern ps = Pattern.compile("^[a-zA-Z0-9???-??????-??????-???\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$");
                if (source.equals("") || ps.matcher(source).matches()) {
                    return source;
                }
                Toast.makeText(getApplicationContext(), "????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
                return "";
            }
        },new InputFilter.LengthFilter(4)});

        textSave_gd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goal goal;

                goal = intentGoal;
                if(goal.getNumber() == 0){
                    Toast.makeText(getApplicationContext(),"????????? ID ?????????.\n???????????? ?????? ??????????????????.", Toast.LENGTH_LONG).show();
                }
                inputMethodManager.hideSoftInputFromWindow(editTitle_gd1.getWindowToken(),0);
                if(editTitle_gd1.getText().toString().equals("")){
                    goal.setTitle(goal.getTitle());
                }
                else{
                    goal.setTitle(editTitle_gd1.getText().toString());
                }

                if(goal != null){
                    goal.setGoalnow(Integer.parseInt(edit_goalnum_d.getText().toString()));
                    goalViewModel.updateGoal(goal);
                }
                Toast.makeText(getApplicationContext(),"?????????????????????.",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                intent1.putExtra("tab",3);
                startActivity(intent1);
            }
        });

    }

    String getPercent(int now, int num){
        if(now == 0){
            String zero = "(0%)";
            return zero;
        }
        else{
            int pc = (int)((double)now/(double)num *100.0);
            Log.d("???",String.valueOf(pc));
            String percent = String.format("("+pc+"%%)");
            return percent;
        }
    }

    Integer getParams(int now, int num){
        if(now == 0){
            return 0;
        }
        else{
            int lg = (int)((double)now/(double)num * width);
            Log.d("???",String.valueOf(lg));
            return lg;
        }
    }
}

