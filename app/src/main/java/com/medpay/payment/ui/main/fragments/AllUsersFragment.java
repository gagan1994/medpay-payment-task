package com.medpay.payment.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.medpay.payment.R;
import com.medpay.payment.databinding.FragmentAllUsersBinding;
import com.medpay.payment.ui.main.MainViewModel;
import com.medpay.payment.ui.main.adapters.RvUserAdapter;

public class AllUsersFragment extends Fragment {

    private FragmentAllUsersBinding fragmentAllUsersBinding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAllUsersBinding = DataBindingUtil
                .inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_all_users,
                        container, false);
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        return fragmentAllUsersBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RvUserAdapter adapter = new RvUserAdapter();
        fragmentAllUsersBinding.rvUsersList.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentAllUsersBinding.rvUsersList.setAdapter(adapter);
        viewModel.fetchAllUserData().observe(getViewLifecycleOwner(), transactionAndUsers -> {
            adapter.setData(transactionAndUsers);
        });
    }
}