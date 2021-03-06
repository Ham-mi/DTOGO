package pe.com.ham.dtogo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import pe.com.ham.dtogo.dao.AppDatabase;

public class MainActivity extends AppCompatActivity {


    public static Context mContext;
    AppBarLayout appBar;
    Toolbar toolbar;

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private FragmentManager fragmentManager;


    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    TabLayout tabs;
    TabItem tab1;
    TabItem tab2;
    TabItem tab3;
    Intent intent;

    int tab = 0;

    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바 설정
        toolbar = findViewById(R.id.toolbar);
        intent = getIntent();
        tab = intent.getIntExtra("tab", 0);
//        appBar = findViewById(R.id.appbar);
//
//        if (appBar.getLayoutParams() != null) {
//            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
//            AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
//            appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
//                @Override
//                public boolean canDrag( AppBarLayout appBarLayout) {
//                    return false;
//                }
//            });
//            layoutParams.setBehavior(appBarLayoutBehaviour);
//        }

//        toolbar.scrollTo(0,0);
//        appBar.setExpanded(true);
//        setSupportActionBar(toolbar);
//
//        //액션바 기본 타이틀 보여지지 않게
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        fragmentManager = getSupportFragmentManager();

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        tabs = findViewById(R.id.tabs);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);

        mContext = this;

//        coordinatorLayout = findViewById(R.id.coorLayout);

//        dbHelper = new DBHelper(this);
//        db = dbHelper.getReadableDatabase();

//        coordinatorLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        fragmentManager.beginTransaction().replace(R.id.container, fragment1).commit();

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


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPop = new Intent(getApplicationContext(), Main_popup.class);
                startActivity(intentPop);
            }
        });

        if(tab==1) {
            onMove1();
        }else if(tab==2){
            onMove2();
        }else if(tab==3){
            onMove3();
        }else{
            onMove1();
        }

    }


    public void onMove1(){
        TabLayout tabLayout = findViewById(R.id.tabs);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();
        fragmentManager.beginTransaction().replace(R.id.container, fragment1).commit();
    }

    public void onMove2(){
        TabLayout tabLayout = findViewById(R.id.tabs);
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();
        fragmentManager.beginTransaction().replace(R.id.container, fragment2).commit();
    }

    public void onMove3(){
        TabLayout tabLayout = findViewById(R.id.tabs);
        TabLayout.Tab tab = tabLayout.getTabAt(2);
        tab.select();
        fragmentManager.beginTransaction().replace(R.id.container, fragment3).commit();
    }

}