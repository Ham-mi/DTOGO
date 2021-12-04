package pe.com.ham.dtogo.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "dday")
public class Dday {
    @PrimaryKey(autoGenerate = true) private int number;

    @ColumnInfo(defaultValue = "") @NotNull
    private String title; // 제목 50자 제한걸기
    @ColumnInfo(defaultValue = "0") @NotNull
    private int calc; // 종류 0,1
    @ColumnInfo(defaultValue = "00000000") @NotNull
    private String date; // 날짜 8자 제한
    @ColumnInfo(defaultValue = "#5D88BB") @NotNull
    private String back_color; // 배경 색상 7자 제한
    @ColumnInfo(defaultValue = "#FFFFFF") @NotNull
    private String text_color; // 글자 색상 7자 제한
    @ColumnInfo(defaultValue = "700102") @NotNull
    private int icon; // drawable 고유 번호
    @ColumnInfo(defaultValue = "0") @NotNull
    private int use; // boolean 0(사용),1(종료)

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public int getCalc(){
        return calc;
    }
    public void setCalc(int calc){
        this.calc = calc;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getBack_color(){
        return back_color;
    }
    public void setBack_color(String back_color){
        this.back_color = back_color;
    }

    public String getText_color(){
        return text_color;
    }
    public void setText_color(String text_color){
        this.text_color = text_color;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getUse(){
        return use;
    }
    public void setUse(int use){
        this.use = use;
    }
}
