package co.com.ceiba.mobile.pruebadeingreso.bd;

import android.content.Context;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

public class UserBD {

    private UserBD() {
    }

    public static void saveUserBD(Context context, List<User> userList) {
        AppDataBase appDataBase = AppDataBase.getInstance(context);
        for (User user : userList) {
            appDataBase.userDao().insert(user);
        }
    }

    public static List<User> getUsersBD(Context context) {
        return AppDataBase.getInstance(context).userDao().getAll();
    }
}
