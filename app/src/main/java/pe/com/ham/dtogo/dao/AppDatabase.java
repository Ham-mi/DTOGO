package pe.com.ham.dtogo.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Dday.class, Goal.class, Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DdayDao ddayDao();
    public abstract GoalDao goalDao();
    public abstract TodoDao todoDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,AppDatabase.class, "dtogo-db").build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
