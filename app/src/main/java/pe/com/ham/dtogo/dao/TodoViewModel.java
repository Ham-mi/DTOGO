package pe.com.ham.dtogo.dao;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository mRepository;

    private LiveData<List<Todo>> mAllTodo;

    public TodoViewModel( Application application) {
        super(application);
        mRepository = new TodoRepository(application);
        mAllTodo = mRepository.getmAllTodo();
    }

    public LiveData<List<Todo>> getmAllTodo() {
        return mAllTodo;
    }

    public void insertTodo(Todo todo) {mRepository.insertTodo(todo);}
    public void updateTodo(Todo todo) {mRepository.updateTodo(todo);}
    public void deleteTodo(Todo todo) {mRepository.deleteTodo(todo);}
}
