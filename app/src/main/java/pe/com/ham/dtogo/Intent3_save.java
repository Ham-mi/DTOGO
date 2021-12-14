package pe.com.ham.dtogo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import pe.com.ham.dtogo.dao.Goal;
import pe.com.ham.dtogo.dao.GoalViewModel;


public class Intent3_save extends AppCompatActivity implements ViewModelStoreOwner{

    private GoalViewModel goalViewModel;
    InputMethodManager inputMethodManager;

    private ImageView imageBack_g1;
    private TextView textSave_g1 , goalunit_btn;
    private EditText editTitle_g1, edit_goalnum;
    private LinearLayout selecttext_g1 , selecttext_g2, selecttext_g3;
    private TextView selecttext_g1_1, selecttext_g1_2, selecttext_g2_1, selecttext_g2_2, selecttext_g3_1, selecttext_g3_2;
    private Button changeClick;
    private TextView textColor1_B_g1, textColor1_T_g1;
    private LinearLayout linearColor1_g1;
    int select, unit, updown;

    boolean done = false;
    Parcelable goalP;
    Goal intentGoal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent3_save);

        getSupportActionBar().hide();

        imageBack_g1 = findViewById(R.id.imageBack_g1);
        textSave_g1 = findViewById(R.id.textSave_g1);
        goalunit_btn = findViewById(R.id.goalunit_btn);
        editTitle_g1 = findViewById(R.id.editTitle_g1);
        edit_goalnum = findViewById(R.id.edit_goalnum);
        selecttext_g1 = findViewById(R.id.selecttext_g1);
        selecttext_g1_1 = findViewById(R.id.selecttext_g1_1);
        selecttext_g1_2 = findViewById(R.id.selecttext_g1_2);
        selecttext_g2 = findViewById(R.id.selecttext_g2);
        selecttext_g2_1 = findViewById(R.id.selecttext_g2_1);
        selecttext_g2_2 = findViewById(R.id.selecttext_g2_2);
        selecttext_g3 = findViewById(R.id.selecttext_g3);
        selecttext_g3_1 = findViewById(R.id.selecttext_g3_1);
        selecttext_g3_2 = findViewById(R.id.selecttext_g3_2);
        changeClick = findViewById(R.id.changeClick);
        textColor1_B_g1 = findViewById(R.id.textColor1_B_g1);
        textColor1_T_g1 = findViewById(R.id.textColor1_T_g1);
        linearColor1_g1 = findViewById(R.id.linearColor1_g1);
        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        Intent intents = getIntent();

        if(intents.getIntExtra("done",-1)==0){
            done = true;
            goalP = intents.getParcelableExtra("goal");
            intentGoal = (Goal) goalP;
            if(intentGoal != null){
                Log.d("G","not null");
            }

            editTitle_g1.setText(intentGoal.getTitle());
            edit_goalnum.setText(String.valueOf(intentGoal.getGoalnum()));
            textColor1_T_g1.setText(intentGoal.getBack_color());
            textColor1_B_g1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(intentGoal.getBack_color())));

        }

        imageBack_g1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });

        editTitle_g1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(50) });

        editTitle_g1.setOnEditorActionListener((v, actionId, event) -> {
            boolean hanled = false;

            if(actionId == EditorInfo.IME_ACTION_DONE){
                inputMethodManager.hideSoftInputFromWindow(editTitle_g1.getWindowToken(),0);
                hanled = true;
            }
            return hanled;
        });

        if(done){

            select = intentGoal.getState();
            updown = intentGoal.getIncrease();
            unit = intentGoal.getGoalunit();
            if(select == 1) {
                selecttext_g1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
                selecttext_g1_1.setTextColor(Color.WHITE);  selecttext_g1_2.setTextColor(Color.WHITE);

                selecttext_g2.setBackgroundTintList(null);
                selecttext_g2_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g2_2.setTextColor(getResources().getColor(R.color.colorBlue));

                selecttext_g3.setBackgroundTintList(null);
                selecttext_g3_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g3_2.setTextColor(getResources().getColor(R.color.colorBlue));
                if(updown == 0){
                    changeClick.setText("수치 증가");
                }
                else if(updown == 1){
                    changeClick.setText("수치 감소");
                }
                if(unit == 1){
                    goalunit_btn.setText("회");
                }
                else if(unit == 2){
                    goalunit_btn.setText("개");
                }
                else{
                    goalunit_btn.setText("장");
                }
            }
            else if(select == 2){
                selecttext_g2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
                selecttext_g2_1.setTextColor(Color.WHITE);  selecttext_g2_2.setTextColor(Color.WHITE);

                selecttext_g1.setBackgroundTintList(null);
                selecttext_g1_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g1_2.setTextColor(getResources().getColor(R.color.colorBlue));

                selecttext_g3.setBackgroundTintList(null);
                selecttext_g3_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g3_2.setTextColor(getResources().getColor(R.color.colorBlue));
                if(updown == 0){
                    changeClick.setText("횟수 증가");
                }
                else if(updown == 1){
                    changeClick.setText("횟수 감소");
                }
                if(unit == 1){
                    goalunit_btn.setText("회");
                }
                else if(unit == 2){
                    goalunit_btn.setText("번");
                }
            }
            else if(select == 3){
                selecttext_g3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
                selecttext_g3_1.setTextColor(Color.WHITE);  selecttext_g3_2.setTextColor(Color.WHITE);

                selecttext_g2.setBackgroundTintList(null);
                selecttext_g2_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g2_2.setTextColor(getResources().getColor(R.color.colorBlue));

                selecttext_g1.setBackgroundTintList(null);
                selecttext_g1_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g1_2.setTextColor(getResources().getColor(R.color.colorBlue));
                if(updown == 0){
                    changeClick.setText("시간 증가");
                }
                else if(updown == 1){
                    changeClick.setText("시간 감소");
                }
                if(unit == 1){
                    goalunit_btn.setText("분");
                }
                else if(unit == 2){
                    goalunit_btn.setText("일");
                }
                else{
                    goalunit_btn.setText("시간");
                }
            }
        }
        else{
            select = 1;
            updown = 1;
            selecttext_g1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            selecttext_g1_1.setTextColor(Color.WHITE); selecttext_g1_2.setTextColor(Color.WHITE);
            changeClick.setText("수치 증가");
            goalunit_btn.setFocusableInTouchMode(true);
        }


        selecttext_g1.setOnClickListener(v -> {
            select = 1;
            selecttext_g1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            selecttext_g1_1.setTextColor(Color.WHITE);  selecttext_g1_2.setTextColor(Color.WHITE);

            selecttext_g2.setBackgroundTintList(null);
            selecttext_g2_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g2_2.setTextColor(getResources().getColor(R.color.colorBlue));

            selecttext_g3.setBackgroundTintList(null);
            selecttext_g3_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g3_2.setTextColor(getResources().getColor(R.color.colorBlue));

            changeClick.setText("수치 증가");
            unit = 1;
            goalunit_btn.setText("회");
        });
        selecttext_g2.setOnClickListener(v -> {
            select = 2;
            selecttext_g2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            selecttext_g2_1.setTextColor(Color.WHITE);  selecttext_g2_2.setTextColor(Color.WHITE);

            selecttext_g1.setBackgroundTintList(null);
            selecttext_g1_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g1_2.setTextColor(getResources().getColor(R.color.colorBlue));

            selecttext_g3.setBackgroundTintList(null);
            selecttext_g3_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g3_2.setTextColor(getResources().getColor(R.color.colorBlue));

            changeClick.setText("횟수 증가");
            unit = 1;
            goalunit_btn.setText("회");
        });
        selecttext_g3.setOnClickListener(v -> {
            select = 3;
            selecttext_g3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            selecttext_g3_1.setTextColor(Color.WHITE);  selecttext_g3_2.setTextColor(Color.WHITE);

            selecttext_g2.setBackgroundTintList(null);
            selecttext_g2_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g2_2.setTextColor(getResources().getColor(R.color.colorBlue));

            selecttext_g1.setBackgroundTintList(null);
            selecttext_g1_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g1_2.setTextColor(getResources().getColor(R.color.colorBlue));

            changeClick.setText("시간 증가");
            unit = 1;
            goalunit_btn.setText("시간");

        });

        goalunit_btn.setOnClickListener(v -> {
            if(select == 1){
                if(unit == 1){
                    unit = 2;
                    goalunit_btn.setText("회");
                }
                else if(unit == 2){
                    unit = 3;
                    goalunit_btn.setText("개");
                }
                else{
                    unit = 1;
                    goalunit_btn.setText("장");
                }
            }
            else if(select == 2){
                if(unit == 1){
                    unit = 2;
                    goalunit_btn.setText("회");
                }
                else if(unit == 2){
                    unit = 1;
                    goalunit_btn.setText("번");
                }

            }
            else if(select == 3){
                if(unit == 1){
                    unit = 2;
                    goalunit_btn.setText("분");
                }
                else if(unit == 2){
                    unit = 3;
                    goalunit_btn.setText("일");
                }
                else{
                    unit = 1;
                    goalunit_btn.setText("시간");
                }
            }
        });

        changeClick.setOnClickListener(v -> {
            if(select == 1){
                if(updown == 0){
                    updown = 1;
                    changeClick.setText("수치 감소");
                }
                else{
                    updown = 0;
                    changeClick.setText("수치 증가");
                }
            }
            else if(select == 2 ){
                if(updown == 0){
                    updown = 1;
                    changeClick.setText("횟수 감소");
                }
                else{
                    updown = 0;
                    changeClick.setText("횟수 증가");
                }
            }
            else{
                if(updown == 0){
                    updown = 1;
                    changeClick.setText("시간 감소");
                }
                else{
                    updown = 0;
                    changeClick.setText("시간 증가");
                }
            }
        });

        linearColor1_g1.setOnClickListener(v -> {
            ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this)
                    .setTitle("배경색 선택")
                    .setPositiveButton("확인", new ColorEnvelopeListener() {
                        @Override
                        public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                            textColor1_B_g1.setBackgroundTintList(ColorStateList.valueOf(envelope.getColor()));
                            textColor1_T_g1.setText("#"+envelope.getHexCode().substring(2));

                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .attachAlphaSlideBar(false)
                    .attachBrightnessSlideBar(true)
                    .setBottomSpace(12);

            ColorPickerView colorPickerView = builder.getColorPickerView();
            colorPickerView.setFlagView(new CustomFlag(this,R.layout.layout_flag));

            builder.show();
        });

        textSave_g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTitle_g1.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "디데이 제목을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                inputMethodManager.hideSoftInputFromWindow(editTitle_g1.getWindowToken(),0);

                Goal goal;

                if (done) {
                    goal = intentGoal;
                    if(intentGoal.getNumber()==0){
                        Toast.makeText(getApplicationContext(),"잘못된 ID 입니다.\n되돌아가 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    goal = new Goal();
                    goal.setUse(0);
                }
                if(goal != null ){
                    goal.setTitle(editTitle_g1.getText().toString());
                    goal.setState(select);
                    goal.setIncrease(updown);
                    goal.setGoalnum(Integer.parseInt(edit_goalnum.getText().toString()));
                    goal.setGoalunit(unit);
                    goal.setBack_color(textColor1_T_g1.getText().toString());

                    if (done) {
                        goalViewModel.updateGoal(goal);
                    }else{
                        goal.setGoalnow(0);
                        goalViewModel.insertGoal(goal);
                    }
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
