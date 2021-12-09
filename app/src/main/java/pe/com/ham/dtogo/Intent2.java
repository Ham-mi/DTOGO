package pe.com.ham.dtogo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

import pe.com.ham.dtogo.dao.Todo;


public class Intent2 extends AppCompatActivity {
    InputMethodManager inputMethodManager;

    private ImageView imageBack;
    private TextView textSave;
    private EditText editTitle;
    private TextView textDate;
    private Button dateClick;
    private static DatePicker datePicker;
    private TextView textTime;
    private Button timeClick;
    private static TimePicker timePicker;

    Calendar cal = Calendar.getInstance();
    int cYear = cal.get(Calendar.YEAR);
    int cMonth = cal.get(Calendar.MONTH);
    int cDay = cal.get(Calendar.DAY_OF_MONTH);

    int cAPM = cal.get(Calendar.AM_PM);
    int cHour = cal.get(Calendar.HOUR_OF_DAY);
    int cMinute = cal.get(Calendar.MINUTE);
    String cDateShow = "", cTimeShow = "";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent2);

        getSupportActionBar().hide();

        imageBack = findViewById(R.id.imageBack);
        textSave = findViewById(R.id.textSave);
        editTitle = findViewById(R.id.editTitle);
        textDate = findViewById(R.id.textDate);
        dateClick = findViewById(R.id.dateClick);
        datePicker = findViewById(R.id.datePicker);
        textTime = findViewById(R.id.textTime);
        timeClick = findViewById(R.id.timeClick);
        timePicker = findViewById(R.id.timePicker);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //되돌아가기 (화면전환)
        imageBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });

        //초기 셋팅
        cal.set(cYear,cMonth,cDay);

        /* datePicker */
        String tmpcMonth = (cMonth+1)<10 ? "0"+(cMonth+1) : String.valueOf((cMonth+1));
        String tmpcDay = cDay<10 ? "0"+cDay : String.valueOf(cDay);
        cDateShow = String.format(cYear+"."+tmpcMonth+"."+tmpcDay+"("+cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.KOREAN)+")");
        textDate.setText(cDateShow);

        /* timePicker */

        String tmpcAPM = (cAPM==1) ? "PM" : "AM";
        cTimeShow = String.format(cHour + ":" + cMinute + " " + tmpcAPM);
        textTime.setText(cTimeShow);

        // datePicker변경시 셋팅
        datePicker.init(cYear, cMonth, cDay, (view, year, monthOfYear, dayOfMonth) -> {
            cal.set(year,monthOfYear,dayOfMonth);

            String tmpMonth = (monthOfYear+1)<10 ? "0"+(monthOfYear+1) : String.valueOf((monthOfYear+1));
            String tmpDay = dayOfMonth<10 ? "0"+dayOfMonth : String.valueOf(dayOfMonth);

            cDateShow = String.format(year+"."+tmpMonth+"."+tmpDay+"("+cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.KOREAN)+")");
            textDate.setText(cDateShow);
        });

        dateClick.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DatePickerDialogTheme(cYear,cMonth,cDay);
            dialogFragment.show(getSupportFragmentManager(),"Theme");
        });

        //timePicker변경시 셋팅
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String tmpAPM = (hourOfDay<12) ? "AM" : "PM";
                int tmpHour = (hourOfDay>12) ? hourOfDay-12 : hourOfDay;
                String tmpFHour = tmpHour<10 ? "0" + tmpHour : ""+tmpHour;
                cTimeShow = String.format(tmpFHour + ":" + minute + " " + tmpAPM);
                textTime.setText(cTimeShow);
            }
        });

        timeClick.setOnClickListener(v -> {
            DialogFragment dialogFragment = new TimePickerDialogTheme(cHour,cMinute,cAPM);
            dialogFragment.show(getSupportFragmentManager(),"Theme");
        });

        textSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(editTitle.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "메모 제목을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                inputMethodManager.hideSoftInputFromWindow(editTitle.getWindowToken(),0);

                Todo todo = new Todo();
                todo.setMemo(editTitle.getText().toString());
                todo.setState(1);

                String tmpMonth = datePicker.getMonth()<10 ? "0"+datePicker.getMonth() : String.valueOf(datePicker.getMonth());
                String tmpDay = datePicker.getDayOfMonth()<10 ? "0"+datePicker.getDayOfMonth() : String.valueOf(datePicker.getDayOfMonth());

                todo.setDate(String.format(datePicker.getYear()+""+tmpMonth+""+tmpDay));

                String tmpHour = timePicker.getHour()<10 ? "0"+timePicker.getHour() : String.valueOf(timePicker.getHour());
                String tmpMinute = timePicker.getMinute()<10 ? "0"+timePicker.getMinute() : String.valueOf(timePicker.getMinute());
                todo.setTime(String.format(tmpHour+tmpMinute));
                todo.setUse(0);
            }
        });




    }

    public static class DatePickerDialogTheme extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        int year,month,day;

        DatePickerDialogTheme(int year, int month, int day){
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,year,month,day);

            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            datePicker.updateDate(year,month,dayOfMonth);
        }
    }

    public static class TimePickerDialogTheme extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        int hour,minute, apm;

        TimePickerDialogTheme(int hour, int minute, int apm){
            this.hour = hour;
            this.minute = minute;
            this.apm = apm;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK, this, hour, minute, false );

            return timePickerDialog;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timePicker.setHour(hourOfDay);
            timePicker.setMinute(minute);
        }
    }
}
