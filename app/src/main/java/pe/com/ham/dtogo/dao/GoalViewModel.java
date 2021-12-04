package pe.com.ham.dtogo.dao;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GoalViewModel extends AndroidViewModel {
    private GoalRepository mRepository;

    private LiveData<List<Goal>> mAllGoal;

    public GoalViewModel(Application application) {
        super(application);
        mRepository = new GoalRepository(application);
        mAllGoal = mRepository.getmAllGoal();
    }

    public LiveData<List<Goal>> getmAllGoal() {
        return mAllGoal;
    }

    private void insertGoal(Goal goal) {mRepository.insertGoal(goal);}
    private void updateGoal(Goal goal) {mRepository.updateGoal(goal);}
    private void deleteGoal(Goal goal) {mRepository.deleteGoal(goal);}

}
