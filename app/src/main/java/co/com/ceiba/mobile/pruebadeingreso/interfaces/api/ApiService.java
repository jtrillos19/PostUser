package co.com.ceiba.mobile.pruebadeingreso.interfaces.api;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Endpoints.GET_USERS)
    Call<List<User>> getUsers();

    @GET(Endpoints.GET_POST_USER)
    Call<List<Post>> getPostById(@Query("userId") int id);

}
