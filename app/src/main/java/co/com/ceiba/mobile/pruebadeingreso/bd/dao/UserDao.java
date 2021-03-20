package co.com.ceiba.mobile.pruebadeingreso.bd.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    Long insert(User user);
}
