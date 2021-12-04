package pe.com.ham.dtogo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import pe.com.ham.dtogo.dao.AppDatabase;
import pe.com.ham.dtogo.dao.Dday;
import pe.com.ham.dtogo.dao.DdayDao;
import pe.com.ham.dtogo.dao.Goal;
import pe.com.ham.dtogo.dao.GoalDao;
import pe.com.ham.dtogo.dao.Todo;
import pe.com.ham.dtogo.dao.TodoDao;

public class DtogoRepository {
    private DdayDao mDdayDao;
    private GoalDao mGoalDao;
    private TodoDao mTodoDao;

    private LiveData<List<Dday>> mAllDday;
    private LiveData<List<Goal>> mAllGoal;
    private LiveData<List<Todo>> mAllTodo;


    // db 핸들을 가져오고 멤버 변수를 초기화함.
    DtogoRepository(Application application){
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mDdayDao = db.ddayDao();
        mGoalDao = db.goalDao();
        mTodoDao = db.todoDao();

        mAllDday = mDdayDao.getAll();
        mAllGoal = mGoalDao.getAll();
        mAllTodo = mTodoDao.getAll();
    }

    LiveData<List<Dday>> getmAllDday(){ return mAllDday; }
    LiveData<List<Goal>> getmAllGoal() { return mAllGoal; }
    LiveData<List<Todo>> getmAllTodo() { return mAllTodo; }


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

}
