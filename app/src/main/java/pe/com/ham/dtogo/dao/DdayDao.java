package pe.com.ham.dtogo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DdayDao {
    @Insert
    void insertDday(Dday dday);

    @Update
    void updateDday(Dday dday);

    @Delete
    void deleteDday(Dday dday);

    @Query("SELECT * FROM DDAY")
    LiveData<List<Dday>> getAll();
}
