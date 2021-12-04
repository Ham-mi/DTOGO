package pe.com.ham.dtogo.dao;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class DdayViewModel extends AndroidViewModel {
    private DdayRepository mRepository;

    private LiveData<List<Dday>> mAllDday;

    public DdayViewModel( Application application) {
        super(application);
        mRepository = new DdayRepository(application);
        mAllDday = mRepository.getmAllDday();
    }
    public LiveData<List<Dday>> getmAllDday() {
        return mAllDday;
    }

    public void insertDday(Dday dday) {mRepository.insertDday(dday);}
    public void updateDday(Dday dday) {mRepository.updateDday(dday);}
    public void deleteDday(Dday dday) {mRepository.deleteDday(dday);}
}
