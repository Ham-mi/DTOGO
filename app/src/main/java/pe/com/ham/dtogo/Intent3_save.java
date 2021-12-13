package pe.com.ham.dtogo;

import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.InputFilter;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import pe.com.ham.dtogo.dao.GoalViewModel;


public class Intent3_save extends AppCompatActivity implements ViewModelStoreOwner{


    private GoalViewModel goalViewModel;
    InputMethodManager inputMethodManager;

    private ImageView imageBack_g1;
    private TextView textSave_g1, goalunit_btn;
    private EditText editTitle_g1, edit_goalnum;
    private LinearLayout selecttext_g1 , selecttext_g2, selecttext_g3;
    private TextView selecttext_g1_1, selecttext_g1_2, selecttext_g2_1, selecttext_g2_2, selecttext_g3_1, selecttext_g3_2;
    private Button changeClick;
    private TextView textColor1_B_g1, textColor1_T_g1;
    private LinearLayout linearColor1_g1;
    int select;
    boolean updown;


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

        imageBack_g1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            ((MainActivity)MainActivity.mContext).onMove3();
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

        select = 0;
        updown = true;
        selecttext_g1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
        selecttext_g1_1.setTextColor(Color.WHITE); selecttext_g1_2.setTextColor(Color.WHITE);

        selecttext_g1.setOnClickListener(v -> {
            select = 0;
            selecttext_g1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            selecttext_g1_1.setTextColor(Color.WHITE);  selecttext_g1_2.setTextColor(Color.WHITE);

            selecttext_g2.setBackgroundTintList(null);
            selecttext_g2_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g2_2.setTextColor(getResources().getColor(R.color.colorBlue));

            selecttext_g3.setBackgroundTintList(null);
            selecttext_g3_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g3_2.setTextColor(getResources().getColor(R.color.colorBlue));

            changeClick.setText("수치 증가");
        });
        selecttext_g2.setOnClickListener(v -> {
            select = 1;
            selecttext_g2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            selecttext_g2_1.setTextColor(Color.WHITE);  selecttext_g2_2.setTextColor(Color.WHITE);

            selecttext_g1.setBackgroundTintList(null);
            selecttext_g1_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g1_2.setTextColor(getResources().getColor(R.color.colorBlue));

            selecttext_g3.setBackgroundTintList(null);
            selecttext_g3_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g3_2.setTextColor(getResources().getColor(R.color.colorBlue));

            changeClick.setText("횟수 증가");
        });
        selecttext_g3.setOnClickListener(v -> {
            select = 2;
            selecttext_g3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            selecttext_g3_1.setTextColor(Color.WHITE);  selecttext_g3_2.setTextColor(Color.WHITE);

            selecttext_g2.setBackgroundTintList(null);
            selecttext_g2_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g2_2.setTextColor(getResources().getColor(R.color.colorBlue));

            selecttext_g1.setBackgroundTintList(null);
            selecttext_g1_1.setTextColor(getResources().getColor(R.color.colorBlue));   selecttext_g1_2.setTextColor(getResources().getColor(R.color.colorBlue));

            changeClick.setText("시간 증가");
        });

        changeClick.setOnClickListener(v -> {
            if(select == 0){
                if(updown == true){
                    updown = false;
                    changeClick.setText("수치 감소");
                }
                else{
                    updown = true;
                    changeClick.setText("수치 증가");
                }
            }
            else if(select == 1 ){
                if(updown == true){
                    updown = false;
                    changeClick.setText("횟수 감소");
                }
                else{
                    updown = true;
                    changeClick.setText("횟수 증가");
                }
            }
            else{
                if(updown == true){
                    updown = false;
                    changeClick.setText("시간 감소");
                }
                else{
                    updown = true;
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
    }
}
