package co.com.ceiba.mobile.pruebadeingreso.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.bd.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract UserDao userDao();

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDataBase.class, "ceiba-db").build();
        }
        return instance;
    }
}
