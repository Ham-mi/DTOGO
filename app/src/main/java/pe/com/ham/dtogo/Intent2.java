package pe.com.ham.dtogo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.Calendar;
import java.util.Locale;

import pe.com.ham.dtogo.dao.Todo;
import pe.com.ham.dtogo.dao.TodoViewModel;


public class Intent2 extends AppCompatActivity implements ViewModelStoreOwner {
    private TodoViewModel mTodoViewModel;
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


    boolean done = false;
    Parcelable todoP;
    Todo intentTodo;


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

        mTodoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        Intent intents = getIntent();

        if(intents.getIntExtra("done",-1)== 0){ // 0이면
            done = true;
            todoP = intents.getParcelableExtra("todo");
            intentTodo = (Todo) todoP;
            if(intentTodo!=null) {
                Log.d("D" , "not null");
            }

            editTitle.setText(intentTodo.getMemo());
            cYear = Integer.parseInt(intentTodo.getDate().substring(0,4));
            cMonth = Integer.parseInt(intentTodo.getDate().substring(4,6));
            cDay = Integer.parseInt(intentTodo.getDate().substring(6,8));

            cHour = Integer.parseInt(intentTodo.getTime().substring(0,2)); //0,1
            cAPM = cHour>12? 1 : 0 ; //0~11 AM 12~23 PM
            cMinute = Integer.parseInt(intentTodo.getTime().substring(2,4)); //2,3

        }

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(cHour);
            timePicker.setMinute(cMinute);
        }


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
                String tmpMinute = minute < 10 ? "0" + minute : String.valueOf(minute);

                cTimeShow = String.format(tmpFHour + ":" + tmpMinute + " " + tmpAPM);
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

                Todo todo;

                if(done) {
                    todo = intentTodo;
                    if(intentTodo.getNumber()==0){
                        Toast.makeText(getApplicationContext(),"잘못된 ID 입니다.\n되돌아가 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    todo = new Todo();
                    todo.setState(0);
                    todo.setUse(0);
                }

                if(todo != null) {
                    todo.setMemo(editTitle.getText().toString());
                    String tmpMonth = datePicker.getMonth() < 10 ? "0" + datePicker.getMonth() : String.valueOf(datePicker.getMonth());
                    String tmpDay = datePicker.getDayOfMonth() < 10 ? "0" + datePicker.getDayOfMonth() : String.valueOf(datePicker.getDayOfMonth());

                    todo.setDate(String.format(datePicker.getYear() + "" + tmpMonth + "" + tmpDay));

                    String tmpHour = timePicker.getHour() < 10 ? "0" + timePicker.getHour() : String.valueOf(timePicker.getHour());
                    String tmpMinute = timePicker.getMinute() < 10 ? "0" + timePicker.getMinute() : String.valueOf(timePicker.getMinute());
                    todo.setTime(String.format(tmpHour + tmpMinute));

                    if(done){
                        mTodoViewModel.updateTodo(todo);
                    }
                    else{mTodoViewModel.insertTodo(todo);}
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
                }
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
