package co.com.ceiba.mobile.pruebadeingreso.bd;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

public class AsyncSaveUserBD extends AsyncTask<ArrayList<User>, Void, String> {

    private Context context;
    private CallbackBD callback;


    public AsyncSaveUserBD(Context context, CallbackBD callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(ArrayList<User>... users) {
        UserBD.saveUserBD(context, users[0]);
        return context.getString(R.string.msj_result_bd);
    }

    @Override
    protected void onPostExecute(String msj) {
        super.onPostExecute(msj);
        callback.result(msj);
    }

    public interface CallbackBD {
        void result(String msj);
    }
}
