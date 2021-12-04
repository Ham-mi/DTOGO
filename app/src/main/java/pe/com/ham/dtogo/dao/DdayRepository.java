package pe.com.ham.dtogo.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DdayRepository {
    private DdayDao mDdayDao;

    private LiveData<List<Dday>> mAllDday;

    DdayRepository(Application application){
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mDdayDao = db.ddayDao();

        mAllDday = mDdayDao.getAll();
    }

    //select * from Dday
    LiveData<List<Dday>> getmAllDday(){ return mAllDday; }

    /* Dday Dao */

    // insert AsyncTask
    public void insertDday(Dday dday){
        new insertDdayAsyncTask(mDdayDao).execute(dday);
    }

    private static class insertDdayAsyncTask extends AsyncTask<Dday, Void, Void>{
        private DdayDao mAsyncTaskDdayDao;

        insertDdayAsyncTask(DdayDao dao){
            mAsyncTaskDdayDao = dao;
        }

        @Override
        protected Void doInBackground(final Dday... params) {
            mAsyncTaskDdayDao.insertDday(params[0]);
            return null;
        }
    }

    // update AsyncTask
    public void updateDday(Dday dday){
        new updateDdayAsyncTask(mDdayDao).execute(dday);
    }

    private static class updateDdayAsyncTask extends AsyncTask<Dday,Void,Void> {
        private DdayDao mAsyncTaskDdayDao;

        updateDdayAsyncTask(DdayDao dao){
            mAsyncTaskDdayDao = dao;
        }

        @Override
        protected Void doInBackground(final Dday... params) {
            mAsyncTaskDdayDao.updateDday(params[0]);
            return null;
        }
    }

    // delete AsyncTask
    public void deleteDday(Dday dday){
        new deleteDdayAsyncTask(mDdayDao).execute(dday);
    }

    private static class deleteDdayAsyncTask extends AsyncTask<Dday,Void,Void>{
        private DdayDao mAsyncTaskDdayDao;

        deleteDdayAsyncTask(DdayDao dao){
            mAsyncTaskDdayDao = dao;
        }

        @Override
        protected Void doInBackground(final Dday... params) {
            mAsyncTaskDdayDao.deleteDday(params[0]);
            return null;
        }
    }
}
