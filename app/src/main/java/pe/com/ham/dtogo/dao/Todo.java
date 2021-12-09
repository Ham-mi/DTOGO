package pe.com.ham.dtogo.dao;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "todo")
public class Todo implements Parcelable {
    @PrimaryKey(autoGenerate = true) private int number;

    @ColumnInfo(defaultValue = "1") @NotNull
    private int state; // 종류 1,2,3
    @ColumnInfo(defaultValue = "") @NotNull
    private String memo; // 제한X
    @ColumnInfo(defaultValue = "00000000") @NotNull
    private String date; // 8자 제한
    @ColumnInfo(defaultValue = "000000") @NotNull
    private String time; // 4자 제한
    @ColumnInfo(defaultValue = "0") @NotNull
    private int use; // boolean 0(사용),(종료)

    public Todo() {}

    protected Todo(Parcel in) {
        number = in.readInt();
        state = in.readInt();
        memo = in.readString();
        date = in.readString();
        time = in.readString();
        use = in.readInt();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeInt(state);
        dest.writeString(memo);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeInt(use);
    }
}
