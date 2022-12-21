package com.example.supervision_java.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.supervision_java.R;
import com.example.supervision_java.adapters.OrderAdapter;
import com.example.supervision_java.adapters.TransactionAdapter;
import com.example.supervision_java.models.Transaction;
import com.example.supervision_java.viewmodels.TransactionViewModel;
import com.example.supervision_java.views.NavigationActivity;
import com.example.supervision_java.views.activities.MainActivity;

import java.util.List;

public class HistoryFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView transactionFragmentRV;
    private TransactionViewModel transactionViewModel;
    public static Context context;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        context = getActivity().getApplicationContext();
        NavigationActivity.actionBar.setTitle("Riwayat Transaksi");
        transactionFragmentRV = view.findViewById(R.id.historyFragmentRV);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.getAllTransactions("Bearer " + MainActivity.user.getToken());
        transactionViewModel.getAllTransactionsDetail().observe(getViewLifecycleOwner(), showAllTransactions);
        return view;
    }

    private Observer<List<Transaction.Transactions>> showAllTransactions = new Observer<List<Transaction.Transactions>>() {
        @Override
        public void onChanged(List<Transaction.Transactions> transactions) {
            transactionFragmentRV.setLayoutManager(new LinearLayoutManager(context));
            TransactionAdapter adapter = new TransactionAdapter(context);
            adapter.setTransactionsList(transactions);
            transactionFragmentRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (TransactionAdapter.transactionsList != null) {
            TransactionAdapter.transactionsList.clear();
        }
    }
}
