package pe.com.ham.dtogo.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoRepository {
    private TodoDao mTodoDao;

    private LiveData<List<Todo>> mAllTodo;

    TodoRepository(Application application){
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mTodoDao = db.todoDao();

        mAllTodo = mTodoDao.getAll();
    }

    //select * from Todo
    LiveData<List<Todo>> getmAllTodo() { return mAllTodo; }

    /* Todo Dao */

    // insert AsyncTask
    public void insertTodo(Todo todo){
        new TodoRepository.insertTodoAsyncTask(mTodoDao).execute(todo);
    }

    private static class insertTodoAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao mAsyncTaskTodoDao;

        insertTodoAsyncTask(TodoDao dao){
            mAsyncTaskTodoDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskTodoDao.insertTodo(params[0]);
            return null;
        }
    }

    // update AsyncTask
    public void updateTodo(Todo todo){
        new TodoRepository.updateTodoAsyncTask(mTodoDao).execute(todo);
    }

    private static class updateTodoAsyncTask extends AsyncTask<Todo,Void,Void> {
        private TodoDao mAsyncTaskTodoDao;

        updateTodoAsyncTask(TodoDao dao){
            mAsyncTaskTodoDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskTodoDao.updateTodo(params[0]);
            return null;
        }
    }

    // delete AsyncTask
    public void deleteTodo(Todo todo){
        new TodoRepository.deleteTodoAsyncTask(mTodoDao).execute(todo);
    }

    private static class deleteTodoAsyncTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao mAsyncTaskTodoDao;

        deleteTodoAsyncTask(TodoDao dao){
            mAsyncTaskTodoDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskTodoDao.deleteTodo(params[0]);
            return null;
        }
    }
}
