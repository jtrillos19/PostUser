package co.com.ceiba.mobile.pruebadeingreso.presenter.user;

import android.content.Context;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.interfaces.user.IUser;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.model.interactor.UserInteractor;

public class UserPresenter implements IUser.Presenter {

    private IUser.View view;
    private Context context;

    public UserPresenter(IUser.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void showUsers(ArrayList<User> users) {
        view.showUsers(users);
    }

    @Override
    public void findUsers() {
        IUser.Interactor interactor;
        interactor = new UserInteractor(this, context);
        interactor.findUsers();
    }
}
