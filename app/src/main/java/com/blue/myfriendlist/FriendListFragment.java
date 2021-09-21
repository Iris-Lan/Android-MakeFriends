package com.blue.myfriendlist;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FriendListFragment extends Fragment {
    private MainActivity activity;
    private List<Friend> friends;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        setHasOptionsMenu(true);
        friends = getFriends();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //為什麼要加這個?
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.fragment_friend_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        //SearchView
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                FriendAdapter adapter = (FriendAdapter) recyclerView.getAdapter();
                if(adapter != null){
                    if(newText.isEmpty()){
                        adapter.setFriends(friends);
                    }else{
                        List<Friend> searchFriends = new ArrayList<>();
                        for(Friend friend : friends){
                            if(friend.getName().toUpperCase().contains(newText.toUpperCase())){
                                searchFriends.add(friend);
                            }
                        }
                        adapter.setFriends(searchFriends);
                    }
                    adapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        //recycleView Code
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        List<Friend> friends = getFriends();
        recyclerView.setAdapter(new FriendAdapter(activity,friends));

    }



    private class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {
        Context context;
        List<Friend> friends;

        public FriendAdapter(Context context, List<Friend> friends) {
            this.context = context;
            this.friends = friends;
        }

        public void setFriends(List<Friend> friends) {this.friends = friends;}

        @Override
        public int getItemCount() {
            return friends.size();
        }

        //找出MyViewHolder 自定義的itemView內容物們
        private class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView tvName;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                tvName = itemView.findViewById(R.id.tvName);
            }
        }

        @NonNull
        @Override
        public FriendAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.friend_item_view,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull  FriendAdapter.MyViewHolder holder, int position) {
            Friend friend =friends.get(position);
            holder.imageView.setImageResource(friend.getImageId());
            holder.tvName.setText(friend.getName());
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("friend", friend);
                Navigation.findNavController(recyclerView).navigate(R.id.action_friendListFragment_to_friendList_DetailFragment, bundle);
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.friend_list_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message;
        int itemId = item.getItemId();
        if(itemId == R.id.orderByDate){
            message = "已根據最新加入日期排序";
        }else if(itemId == R.id.orderByAge){
            message = "已根據年紀排序";
        }else{
            return super.onOptionsItemSelected(item);
        }
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
        return true;
    }

    private List<Friend> getFriends() {
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend(R.drawable.ivy,"Ivy","0912345678","1.6","55"));
        friends.add(new Friend(R.drawable.mary,"mary","0912123123","1.7","80"));
        friends.add(new Friend(R.drawable.sue,"Sue","0922445566","1.55","21"));
        friends.add(new Friend(R.drawable.ivy,"Tina","0912345678","1.6","55"));
        friends.add(new Friend(R.drawable.mary,"Aurora","0912123123","1.7","80"));
        friends.add(new Friend(R.drawable.sue,"Emma","0922445566","1.55","21"));
        friends.add(new Friend(R.drawable.ivy,"Chloe","0912345678","1.6","55"));
        friends.add(new Friend(R.drawable.mary,"Alethea","0912123123","1.7","80"));
        friends.add(new Friend(R.drawable.sue,"Gill","0922445566","1.55","21"));
        friends.add(new Friend(R.drawable.ivy,"Hazel","0912345678","1.6","55"));
        friends.add(new Friend(R.drawable.mary,"Janice","0912123123","1.7","80"));
        friends.add(new Friend(R.drawable.sue,"Kay","0922445566","1.55","21"));
        return friends;
    }
}