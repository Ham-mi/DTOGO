package pe.com.ham.dtogo.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "todo")
public class Todo {
    @PrimaryKey(autoGenerate = true) private int number;

    @ColumnInfo(defaultValue = "1") @NotNull
    private int state; // 종류 1,2,3
    @ColumnInfo(defaultValue = "") @NotNull
    private String memo; // 제한X
    @ColumnInfo(defaultValue = "00000000") @NotNull
    private String date; // 8자 제한
    @ColumnInfo(defaultValue = "000000") @NotNull
    private String time; // 6자 제한
    @ColumnInfo(defaultValue = "0") @NotNull
    private int use; // boolean 0(사용),(종료)

    public int getNumber() {
        return number;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }
    public void setMemo( String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }
    public void setDate( String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime( String time) {
        this.time = time;
    }

    public int getUse() {
        return use;
    }
    public void setUse(int use) {
        this.use = use;
    }
}
