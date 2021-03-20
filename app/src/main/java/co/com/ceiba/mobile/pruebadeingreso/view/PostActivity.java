package co.com.ceiba.mobile.pruebadeingreso.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.interfaces.post.IPost;
import co.com.ceiba.mobile.pruebadeingreso.model.adapter.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.post.PostPresenter;
import co.com.ceiba.mobile.pruebadeingreso.utils.DialogUtil;

public class PostActivity extends AppCompatActivity implements IPost.View {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        User user = (User) getIntent().getSerializableExtra("User");
        initElements(user);
    }

    private void initElements(User user) {
        DialogUtil.showDialog(this);
        TextView name = findViewById(R.id.name);
        name.setText(user.getName());
        TextView phone = findViewById(R.id.phone);
        phone.setText(user.getPhone());
        TextView email = findViewById(R.id.email);
        email.setText(user.getEmail());
        recyclerView = findViewById(R.id.recyclerViewPostsResults);
        IPost.Presenter presenter = new PostPresenter(this);
        presenter.findPosts(user);
    }

    @Override
    public void showPosts(ArrayList<Post> posts) {
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        PostAdapter postAdapter = new PostAdapter(posts);
        recyclerView.setAdapter(postAdapter);
    }
}
