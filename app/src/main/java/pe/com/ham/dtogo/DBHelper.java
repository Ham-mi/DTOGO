package pe.com.ham.dtogo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dtogo.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String sql;
         sql = "CREATE TABLE if not exists \"Dday\" (\n" +
                 "\"number\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                 "\"title\" VARCHAR(50) NOT NULL DEFAULT \"\",\n" +
                 "\"calc\" INTEGER NOT NULL DEFAULT 0,\n" +
                 "\"date\" VARCHAR(8) NOT NULL DEFAULT \"00000000\",\n" +
                 "\"back_color\" VARCHAR(7) NOT NULL DEFAULT \"#5D88BB\",\n" +
                 "\"text_color\" VARCHAR(7) NOT NULL DEFAULT \"#FFFFFF\",\n" +
                 "\"use\" INTEGER NOT NULL DEFAULT 0 \n" +
                 ");";
        db.execSQL(sql); // Dday table

        sql = "CREATE TABLE if not exists \"Todo\" (\n" +
                "\"number\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\"state\" INTEGER NOT NULL DEFAULT 1,\n" +
                "\"memo\" TEXT NOT NULL DEFAULT \"\",\n" +
                "\"date\" VARCHAR(8) NOT NULL DEFAULT \"00000000\",\n" +
                "\"time\" VARCHAR(6) NOT NULL DEFAULT \"000000\",\n" +
                "\"use\" INTEGER NOT NULL DEFAULT 0 \n" +
                ");";
        db.execSQL(sql); //todo table

        sql = "CREATE TABLE if not exists \"Goal\" (\n" +
                "\"number\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\"title\" VARCHAR(50) NOT NULL DEFAULT \"\",\n" +
                "\"state\" INTEGER NOT NULL DEFAULT 1,\n" +
                "\"increase\" INTEGER NOT NULL DEFAULT 0,\n" +
                "\"goalnum\" INTEGER NOT NULL DEFAULT 1,\n" +
                "\"goalunit\" INTEGER NOT NULL DEFAULT 1,\n" +
                "\"goalnow\" INTEGER NOT NULL DEFAULT 0,\n" +
                "\"back_color\" VARCHAR(7) NOT NULL DEFAULT \"#5D88BB\",\n" +
                "\"use\" INTEGER NOT NULL DEFAULT 0\n" +
                ");";
        db.execSQL(sql); // goal table

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql;
        sql = "DROP TABLE if exists \"Dday\";";
        db.execSQL(sql);

        sql = "DROP TABLE if exists \"Todo\";";
        db.execSQL(sql);

        sql = "DROP TABLE if exists \"Goal\";";
        db.execSQL(sql);
        onCreate(db);
    }
}
