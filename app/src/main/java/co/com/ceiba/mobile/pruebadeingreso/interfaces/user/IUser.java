package co.com.ceiba.mobile.pruebadeingreso.interfaces.user;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

public interface IUser {
    interface View {
        void showUsers(ArrayList<User> users);
    }
    interface Presenter{
        void showUsers(ArrayList<User> users);
        void findUsers();
    }
    interface Interactor{
        void findUsers();
    }
}
