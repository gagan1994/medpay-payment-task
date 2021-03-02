package com.medpay.payment.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.medpay.payment.R;
import com.medpay.payment.data.db.models.TransactionAndUser;
import com.medpay.payment.databinding.TransactionCanceledItemLayoutBinding;
import com.medpay.payment.databinding.TransactionSuccessItemLayoutBinding;
import com.medpay.payment.ui.main.fragments.MainFragmentDirections;
import com.medpay.payment.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class RvTransactionAdapter extends RecyclerView.Adapter<RvTransactionAdapter.VH> {
    List<TransactionAndUser> mData = new ArrayList<>();

    public RvTransactionAdapter() {
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), viewType,
                        parent, false);

        return new VH(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).isSuccess() ? R.layout.transaction_success_item_layout
                : R.layout.transaction_canceled_item_layout;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<TransactionAndUser> TransactionAndUser) {
        this.mData = TransactionAndUser == null ? new ArrayList<>() : TransactionAndUser;
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final ViewDataBinding viewBinding;

        public VH(@NonNull ViewDataBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public void setData(TransactionAndUser transactionAndUser) {
            View retry = null;
            if (viewBinding instanceof TransactionCanceledItemLayoutBinding) {
                TransactionCanceledItemLayoutBinding binding = ((TransactionCanceledItemLayoutBinding) viewBinding);
                retry = binding.tvRetryPayAgain;
                binding.setTransactionModel(transactionAndUser);
            }
            if (viewBinding instanceof TransactionSuccessItemLayoutBinding) {
                TransactionSuccessItemLayoutBinding binding = ((TransactionSuccessItemLayoutBinding) viewBinding);
                retry = binding.tvRetryPayAgain;
                binding.setTransactionModel(transactionAndUser);
            }
            itemView.setOnClickListener(v -> {
                MainFragmentDirections.ActionMainFragmentToPaymentDetailsFragment action
                        = MainFragmentDirections.actionMainFragmentToPaymentDetailsFragment()
                        .setTransactionId(transactionAndUser.getTransactionId());
                Navigation.findNavController(itemView).navigate(action);
            });

            retry.setOnClickListener(v -> {
                MainFragmentDirections.ActionMainFragmentToPaymentInputFragment action
                        = MainFragmentDirections.actionMainFragmentToPaymentInputFragment();
                action.setPhoneNumber(transactionAndUser.getPhoneNumber());
                action.setAmount(transactionAndUser.getAmountString());
                action.setName(transactionAndUser.getName());
                Navigation.findNavController(itemView)
                        .navigate(action);

            });

        }
    }
}
