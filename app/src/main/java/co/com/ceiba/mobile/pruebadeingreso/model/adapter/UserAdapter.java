package co.com.ceiba.mobile.pruebadeingreso.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> usersList;

    public UserAdapter(List<User> users) {
        this.usersList = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.initElements(user);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void filterList(List<User> userFilter) {
        usersList = userFilter;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView phone;
        TextView email;
        User user;
        Button btnPost;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            btnPost = itemView.findViewById(R.id.btn_view_post);
            btnPost.setOnClickListener(this);
        }

        public void initElements(User user) {
            this.user = user;
            this.name.setText(user.getName());
            this.phone.setText(user.getPhone());
            this.email.setText(user.getEmail());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent showPostIntent = new Intent();
            showPostIntent.setClass(context, PostActivity.class);
            showPostIntent.putExtra("User", (Serializable) user);
            context.startActivity(showPostIntent);
        }
    }
}
