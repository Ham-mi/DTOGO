package pe.com.ham.dtogo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.flag.FlagView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Intent1_save extends AppCompatActivity {
    private ImageView imageBack;
    private TextView textSave;
    private EditText editTitle;
    private Button btnIcon;
    private LinearLayout linearCalc1, linearCalc2;
    private TextView textCalc1_M, textCalc1_S;
    private TextView textCalc2_M, textCalc2_S;
    private TextView textDate;
    private Button dateClick;
    private static DatePicker datePicker;
    private LinearLayout linearColor1, linearColor2;
    private TextView textColor1_B, textColor1_T;
    private TextView textColor2_B, textColor2_T;
    int calc;
    Calendar cal = Calendar.getInstance();
    int cYear = cal.get(Calendar.YEAR);
    int cMonth = cal.get(Calendar.MONTH);
    int cDay = cal.get(Calendar.DAY_OF_MONTH);
    String cShow = "";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent1_save);

        getSupportActionBar().hide();

        imageBack = findViewById(R.id.imageBack);
        textSave = findViewById(R.id.textSave);
        editTitle = findViewById(R.id.editTitle);
        btnIcon = findViewById(R.id.btnIcon);
        linearCalc1 = findViewById(R.id.linearCalc1);   linearCalc2 = findViewById(R.id.linearCalc2);
        textCalc1_M = findViewById(R.id.textCalc1_M);   textCalc1_S = findViewById(R.id.textCalc1_S);
        textCalc2_M = findViewById(R.id.textCalc2_M);   textCalc2_S = findViewById(R.id.textCalc2_S);
        textDate = findViewById(R.id.textDate);
        dateClick = findViewById(R.id.dateClick);
        datePicker = findViewById(R.id.datePicker);
        linearColor1 = findViewById(R.id.linearColor1); linearColor2 = findViewById(R.id.linearColor2);
        textColor1_B = findViewById(R.id.textColor1_B); textColor1_T = findViewById(R.id.textColor1_T);
        textColor2_B = findViewById(R.id.textColor2_B); textColor2_T = findViewById(R.id.textColor2_T);


        //되돌아가기 (화면전환)
        imageBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });

        // 제목 50자 제한
        editTitle.setFilters(new InputFilter[] { new InputFilter.LengthFilter(50) });

        // Calc 초기화
        calc = 0;
        linearCalc1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
        textCalc1_M.setTextColor(Color.WHITE);  textCalc1_S.setTextColor(Color.WHITE);

        // Calc 선택
        linearCalc1.setOnClickListener(v -> {
            calc = 0;
            linearCalc1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            textCalc1_M.setTextColor(Color.WHITE);  textCalc1_S.setTextColor(Color.WHITE);

            linearCalc2.setBackgroundTintList(null);
            textCalc2_M.setTextColor(getResources().getColor(R.color.colorBlue));   textCalc2_S.setTextColor(getResources().getColor(R.color.colorBlue));
        });
        linearCalc2.setOnClickListener(v -> {
            calc = 1;
            linearCalc1.setBackgroundTintList(null);
            textCalc1_M.setTextColor(getResources().getColor(R.color.colorBlue));  textCalc1_S.setTextColor(getResources().getColor(R.color.colorBlue));

            linearCalc2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
            textCalc2_M.setTextColor(Color.WHITE);   textCalc2_S.setTextColor(Color.WHITE);
        });
        
        //초기 셋팅
        cal.set(cYear,cMonth,cDay);
        cShow = String.format(cYear+"."+cMonth+"."+cDay+"("+cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.KOREAN)+")");
        textDate.setText(cShow);
        
        // datePicker변경시 셋팅
        datePicker.init(cYear, cMonth, cDay, (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(year,monthOfYear,dayOfMonth);
            cShow = String.format(year+"."+(monthOfYear+1)+"."+dayOfMonth+"("+cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.KOREAN)+")");
            textDate.setText(cShow);
        });

        dateClick.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DatePickerDialogTheme(cYear,cMonth,cDay);
            dialogFragment.show(getSupportFragmentManager(),"Theme");
        });

        linearColor1.setOnClickListener(v -> {
            //ColorPicker
            ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this)
                    .setTitle("배경색 선택")
                    .setPositiveButton("확인", new ColorEnvelopeListener() {
                        @Override
                        public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                            textColor1_B.setBackgroundTintList(ColorStateList.valueOf(envelope.getColor()));
                            textColor1_T.setText("#"+envelope.getHexCode().substring(2));

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

        linearColor2.setOnClickListener(v ->{
            //ColorPicker
            ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this)
                    .setTitle("배경색 선택")
                    .setPositiveButton("확인", new ColorEnvelopeListener() {
                        @Override
                        public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                            textColor2_B.setBackgroundTintList(ColorStateList.valueOf(envelope.getColor()));
                            textColor2_T.setText("#"+envelope.getHexCode().substring(2));
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

    public static class DatePickerDialogTheme extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        int year, month, day;

        DatePickerDialogTheme(int year, int month, int day){
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public Dialog onCreateDialog( Bundle savedInstanceState) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,year,month,day);

            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            datePicker.updateDate(year,month,dayOfMonth);
        }
    }



}
