package pe.com.ham.dtogo.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GoalRepository {
    private GoalDao mGoalDao;

    private LiveData<List<Goal>> mAllGoal;

    GoalRepository(Application application){
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mGoalDao = db.goalDao();

        mAllGoal = mGoalDao.getAll();
    }
    //select * from Goal
    LiveData<List<Goal>> getmAllGoal() { return mAllGoal; }

    /* Goal Dao */
    // insert AsyncTask
    public void insertGoal(Goal goal){
        new GoalRepository.insertGoalAsyncTask(mGoalDao).execute(goal);
    }

    private static class insertGoalAsyncTask extends AsyncTask<Goal, Void, Void>{
        private GoalDao mAsyncTaskGoalDao;

        insertGoalAsyncTask(GoalDao dao){
            mAsyncTaskGoalDao = dao;
        }

        @Override
        protected Void doInBackground(final Goal... params) {
            mAsyncTaskGoalDao.insertGoal(params[0]);
            return null;
        }
    }

    // update AsyncTask
    public void updateGoal(Goal goal){
        new GoalRepository.updateGoalAsyncTask(mGoalDao).execute(goal);
    }

    private static class updateGoalAsyncTask extends AsyncTask<Goal,Void,Void> {
        private GoalDao mAsyncTaskGoalDao;

        updateGoalAsyncTask(GoalDao dao){
            mAsyncTaskGoalDao = dao;
        }

        @Override
        protected Void doInBackground(final Goal... params) {
            mAsyncTaskGoalDao.updateGoal(params[0]);
            return null;
        }
    }

    // delete AsyncTask
    public void deleteGoal(Goal goal){
        new GoalRepository.deleteGoalAsyncTask(mGoalDao).execute(goal);
    }

    private static class deleteGoalAsyncTask extends AsyncTask<Goal,Void,Void>{
        private GoalDao mAsyncTaskGoalDao;

        deleteGoalAsyncTask(GoalDao dao){
            mAsyncTaskGoalDao = dao;
        }

        @Override
        protected Void doInBackground(final Goal... params) {
            mAsyncTaskGoalDao.deleteGoal(params[0]);
            return null;
        }
    }

}
