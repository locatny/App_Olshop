package com.example.girlnyshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private ImageView ivBack;
    private TextView tvTitle;
    private RecyclerView rvChats;

    private ChatAdapter chatAdapter;
    private List<ChatItem> chatItemList = new ArrayList<>();

    public ChatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ivBack = view.findViewById(R.id.ivBack);
        tvTitle = view.findViewById(R.id.tvTitle);
        rvChats = view.findViewById(R.id.rvChats);

        ivBack.setOnClickListener(v -> getActivity().onBackPressed());

        tvTitle.setText("Chat Title");

        chatAdapter = new ChatAdapter(chatItemList);
        rvChats.setLayoutManager(new LinearLayoutManager(getContext()));
        rvChats.setAdapter(chatAdapter);

        loadData();
    }

    private void loadData() {
        chatItemList.clear();
        chatItemList.add(new ChatItem("User 1", "walaupun produk preloved tapi bajunya bagus!", R.drawable.pp2));
        chatItemList.add(new ChatItem("User 2", "sangat memuaskan! barang cepat sampai", R.drawable.pp4));
        chatItemList.add(new ChatItem("User 3", "bagus bgttt roknya beli disini", R.drawable.pp3));
        chatItemList.add(new ChatItem("User 4", "ga nyesel beli produk preloved disinii pokokny", R.drawable.pp));
        chatItemList.add(new ChatItem("User 5", "bahan bajunya masih okee bgt", R.drawable.pp1  ));
        chatAdapter.notifyDataSetChanged();
    }

    public class ChatItem {
        String userName;
        String message;
        int userImage;

        public ChatItem(String userName, String message, int userImage) {
            this.userName = userName;
            this.message = message;
            this.userImage = userImage;
        }
    }

    public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

        private List<ChatItem> chatItems;

        public ChatAdapter(List<ChatItem> chatItems) {
            this.chatItems = chatItems;
        }

        @Override
        public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
            return new ChatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ChatViewHolder holder, int position) {
            ChatItem chatItem = chatItems.get(position);
            holder.message.setText(chatItem.message);
            Glide.with(holder.itemView.getContext())
                    .load(chatItem.userImage)
                    .into(holder.userImage);
        }

        @Override
        public int getItemCount() {
            return chatItems.size();
        }

        public class ChatViewHolder extends RecyclerView.ViewHolder {

            TextView message;
            ImageView userImage;

            public ChatViewHolder(View itemView) {
                super(itemView);
                message = itemView.findViewById(R.id.tvMessage);
                userImage = itemView.findViewById(R.id.ivUserImage);
            }
        }
    }
}
