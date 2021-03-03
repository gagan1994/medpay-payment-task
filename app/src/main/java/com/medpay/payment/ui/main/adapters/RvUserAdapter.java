package com.medpay.payment.ui.main.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.medpay.payment.R;
import com.medpay.payment.data.db.models.TransactionAndUser;
import com.medpay.payment.databinding.UserListItemBinding;
import com.medpay.payment.ui.main.fragments.AllUsersFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class RvUserAdapter extends RecyclerView.Adapter<RvUserAdapter.VH> {
    private List<TransactionAndUser> mData=new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserListItemBinding viewBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.user_list_item,
                        parent, false);

        return new RvUserAdapter.VH(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<TransactionAndUser> transactionAndUsers) {
        mData=transactionAndUsers;
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final UserListItemBinding binding;

        public VH(@NonNull UserListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(TransactionAndUser transactionAndUser) {
            binding.setUsers(transactionAndUser);
            binding.tvUserName.setVisibility(TextUtils.isEmpty(transactionAndUser.getName()) ? View.GONE : View.VISIBLE);
            binding.btnPay.setOnClickListener(v -> {
                AllUsersFragmentDirections.ActionAllUsersFragmentToPaymentInputFragment action
                        = AllUsersFragmentDirections.actionAllUsersFragmentToPaymentInputFragment();
                action.setPhoneNumber(transactionAndUser.getPhoneNumber());
                action.setName(transactionAndUser.getName());
                Navigation.findNavController(itemView)
                        .navigate(action);
            });
        }
    }
}
