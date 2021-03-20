package co.com.ceiba.mobile.pruebadeingreso.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.interfaces.user.IUser;
import co.com.ceiba.mobile.pruebadeingreso.model.adapter.EmptyAdapter;
import co.com.ceiba.mobile.pruebadeingreso.model.adapter.UserAdapter;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.UserPresenter;
import co.com.ceiba.mobile.pruebadeingreso.utils.DialogUtil;

public class MainActivity extends AppCompatActivity implements IUser.View {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    EmptyAdapter emptyAdapter;
    EditText textSearch;
    List<User> listUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not Necessary
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not Necessary
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DialogUtil.getDialog() == null)
            DialogUtil.showDialog(this);
    }

    private void initElements() {
        DialogUtil.showDialog(this);
        textSearch = findViewById(R.id.editTextSearch);
        recyclerView = findViewById(R.id.recyclerViewSearchResults);
        IUser.Presenter presenter;
        presenter = new UserPresenter(this, getApplicationContext());
        presenter.findUsers();
    }

    @Override
    public void showUsers(ArrayList<User> users) {
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        userAdapter = new UserAdapter(users);
        recyclerView.setAdapter(userAdapter);
        listUser = users;
    }

    private void filter(String textSearch) {
        List<User> filterUser = new ArrayList<>();
        userAdapter = new UserAdapter(filterUser);
        recyclerView.setAdapter(userAdapter);
        for (User user : listUser) {
            if (user.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                filterUser.add(user);
            }
        }

        if (filterUser.isEmpty()) {
            emptyAdapter = new EmptyAdapter();
            recyclerView.setAdapter(emptyAdapter);
        } else {
            userAdapter.filterList(filterUser);
        }
    }
}