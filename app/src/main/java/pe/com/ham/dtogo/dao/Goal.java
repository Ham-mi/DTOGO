package pe.com.ham.dtogo.dao;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "goal")
public class Goal implements Parcelable {
    @PrimaryKey(autoGenerate = true) private int number;

    @ColumnInfo(defaultValue = "") @NotNull
    private String title; // 50자 제한걸기
    @ColumnInfo(defaultValue = "1") @NotNull
    private int state; // 종류 1,2,3
    @ColumnInfo(defaultValue = "0") @NotNull
    private int increase; //boolean 0,1
    @ColumnInfo(defaultValue = "1") @NotNull
    private int goalnum; // 목표치 1~
    @ColumnInfo(defaultValue = "1") @NotNull
    private int goalunit; // 단위종류 1,2,3,~
    @ColumnInfo(defaultValue = "0") @NotNull
    private int goalnow;  // 현재 달성치 0~
    @ColumnInfo(defaultValue = "#5D88BB") @NotNull
    private String back_color; // 배경 색상
    @ColumnInfo(defaultValue = "0") @NotNull
    private int use; // booelan 0(사용),1(종료)

    public Goal() {}

    protected Goal(Parcel in) {
        number = in.readInt();
        title = in.readString();
        state = in.readInt();
        increase = in.readInt();
        goalnum = in.readInt();
        goalunit = in.readInt();
        goalnow = in.readInt();
        back_color = in.readString();
        use = in.readInt();
    }

    public static final Creator<Goal> CREATOR = new Creator<Goal>() {
        @Override
        public Goal createFromParcel(Parcel in) {
            return new Goal(in);
        }

        @Override
        public Goal[] newArray(int size) {
            return new Goal[size];
        }
    };

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public int getIncrease() {
        return increase;
    }
    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public int getGoalnum() {
        return goalnum;
    }
    public void setGoalnum(int goalnum) {
        this.goalnum = goalnum;
    }

    public int getGoalunit() {
        return goalunit;
    }
    public void setGoalunit(int goalunit) {
        this.goalunit = goalunit;
    }

    public int getGoalnow() {
        return goalnow;
    }
    public void setGoalnow(int goalnow) {
        this.goalnow = goalnow;
    }

    public String getBack_color() {
        return back_color;
    }
    public void setBack_color(String back_color) {
        this.back_color = back_color;
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
        dest.writeString(title);
        dest.writeInt(state);
        dest.writeInt(increase);
        dest.writeInt(goalnum);
        dest.writeInt(goalunit);
        dest.writeInt(goalnow);
        dest.writeString(back_color);
        dest.writeInt(use);
    }
}
