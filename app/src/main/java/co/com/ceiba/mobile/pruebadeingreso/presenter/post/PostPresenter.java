package co.com.ceiba.mobile.pruebadeingreso.presenter.post;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.interfaces.post.IPost;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.model.interactor.PostInteractor;

public class PostPresenter implements IPost.Presenter {

    private IPost.View view;

    public PostPresenter(IPost.View view) {
        this.view = view;
    }

    @Override
    public void showPosts(ArrayList<Post> posts) {
        view.showPosts(posts);
    }

    @Override
    public void findPosts(User user) {
        IPost.Interactor interactor;
        interactor = new PostInteractor(this);
        interactor.findPosts(user);
    }
}
