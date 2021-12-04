package pe.com.ham.dtogo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import pe.com.ham.dtogo.dao.AppDatabase;

public class MainActivity extends AppCompatActivity implements UsingDB{

    Toolbar toolbar;

    private SQLiteDatabase db;
    private DBHelper dbHelper;


    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    TabLayout tabs;
    TabItem tab1;
    TabItem tab2;
    TabItem tab3;

    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바 설정
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        //액션바 기본 타이틀 보여지지 않게
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        tabs = findViewById(R.id.tabs);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);

//        coordinatorLayout = findViewById(R.id.coorLayout);

//        dbHelper = new DBHelper(this);
//        db = dbHelper.getReadableDatabase();

//        coordinatorLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);



        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("D", "onTabSelected: "+position);

                Fragment selected = null;
                if(position==0) selected = fragment1;
                else if(position==1) selected = fragment2;
                else if(position==2) selected = fragment3;

                getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void item1_select() {

    }

    @Override
    public void item1_insert() {

    }

    @Override
    public void item1_delete() {

    }

    @Override
    public void item1_update() {

    }
}