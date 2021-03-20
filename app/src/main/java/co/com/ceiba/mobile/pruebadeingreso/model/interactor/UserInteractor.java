package co.com.ceiba.mobile.pruebadeingreso.model.interactor;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.bd.AsyncGetUserBD;
import co.com.ceiba.mobile.pruebadeingreso.bd.AsyncSaveUserBD;
import co.com.ceiba.mobile.pruebadeingreso.interfaces.user.IUser;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiAdapter;
import co.com.ceiba.mobile.pruebadeingreso.utils.DialogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInteractor implements IUser.Interactor, Callback<List<User>> {

    private List<User> users;
    private IUser.Presenter presenter;
    private Context context;

    public UserInteractor(IUser.Presenter presenter, Context context) {
        this.users = new ArrayList<>();
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) {
            users = response.body();
            if (users != null) {
                presenter.showUsers((ArrayList<User>) users);
                new AsyncSaveUserBD(context, new AsyncSaveUserBD.CallbackBD() {
                    @Override
                    public void result(String msj) {
                        Toast.makeText(context, msj, Toast.LENGTH_SHORT).show();
                    }
                }).execute((ArrayList<User>) users);
            } else {
                Log.e("onResponseUsers", "Response is null");
            }
            DialogUtil.dismissDailog();
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        DialogUtil.dismissDailog();
        Log.e("getServicesFailed", "Response failed");
    }

    @Override
    public void findUsers() {
        new AsyncGetUserBD(context, new AsyncGetUserBD.CallbackBD() {
            @Override
            public void result(List<User> users) {
                initUserList((ArrayList<User>) users);
            }
        }).execute();
    }

    private void initUserList(ArrayList<User> usersList) {
        if (usersList.isEmpty()) {
            Call<List<User>> call = ApiAdapter.getApiService().getUsers();
            call.enqueue(this);
        } else {
            users = usersList;
            presenter.showUsers((ArrayList<User>) users);
            DialogUtil.dismissDailog();
        }
    }
}
