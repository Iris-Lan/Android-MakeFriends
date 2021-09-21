package com.blue.myfriendlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FriendList_DetailFragment extends Fragment {
    private MainActivity activity;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend_list__detail, container, false);
    }




    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        if(activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true); //左上角返回箭頭
        }

        imageView = view.findViewById(R.id.imageView_Detail);
        TextView tvName = view.findViewById(R.id.tvName_Detail);
        TextView tvPhone = view.findViewById(R.id.tvPhone);
        Bundle bundle = getArguments();
        TextView tvBMI = view.findViewById(R.id.tvBMI);
        if(bundle != null){
            Friend friend = (Friend) bundle.getSerializable("friend");
            if(friend != null){
                friend.getBMI();
                imageView.setImageResource(friend.getImageId());
                tvName.setText(friend.getName());
                tvPhone.setText(friend.getPhone());
                tvBMI.setText(friend.toString());
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull  Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.friend_list_menu_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message;
        int itemId = item.getItemId();
        if(itemId == android.R.id.home){
            Navigation.findNavController(imageView).popBackStack();
            return true;
        }else if(itemId == R.id.menuCall){
            message = "真的要打電話？";
        }else if(itemId == R.id.menuMessage){
            message = "恭喜你，跨出第一步！";
        }else if(itemId == R.id.menuVideo){
            message = "等等等等等 先別啦！";
        }else{
            //甚麼情況會出現?
            return super.onOptionsItemSelected(item);
        }
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
        return true;
    }
}