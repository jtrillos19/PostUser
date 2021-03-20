package co.com.ceiba.mobile.pruebadeingreso.model.interactor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.interfaces.post.IPost;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiAdapter;
import co.com.ceiba.mobile.pruebadeingreso.utils.DialogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostInteractor implements IPost.Interactor, Callback<List<Post>> {

    private ArrayList<Post> posts;
    private IPost.Presenter presenter;

    public PostInteractor(IPost.Presenter presenter) {
        this.presenter = presenter;
        posts = new ArrayList<>();
    }

    @Override
    public void findPosts(User user) {
        Call<List<Post>> call = ApiAdapter.getApiService().getPostById(user.getId());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful()) {
            posts = (ArrayList<Post>) response.body();
            if (posts != null) {
                presenter.showPosts(posts);
            } else {
                Log.e("onResponsePosts", "Response is null");
            }
            DialogUtil.dismissDailog();
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        DialogUtil.dismissDailog();
        Log.e("getServicesFailed", "Response failed");
    }
}
