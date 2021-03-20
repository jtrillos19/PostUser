package co.com.ceiba.mobile.pruebadeingreso.bd;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

public class AsyncGetUserBD extends AsyncTask<String, Void, ArrayList<User>> {

    Context context;
    private CallbackBD callback;

    public AsyncGetUserBD(Context context, CallbackBD callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected ArrayList<User> doInBackground(String... strings) {
        return (ArrayList<User>) UserBD.getUsersBD(context);
    }

    @Override
    protected void onPostExecute(ArrayList<User> users) {
        super.onPostExecute(users);
        callback.result(users);
    }

    public interface CallbackBD {
        void result(List<User> users);
    }
}
