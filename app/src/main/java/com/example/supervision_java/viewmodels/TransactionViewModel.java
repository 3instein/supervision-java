package com.example.supervision_java.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.models.ShowTransaction;
import com.example.supervision_java.models.Transaction;
import com.example.supervision_java.repositories.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = TransactionRepository.getInstance();
    }

    private MutableLiveData<List<Transaction.Transactions>> getAllTransactionsResult = new MutableLiveData<>();

    public void getAllTransactions(String token) {
        getAllTransactionsResult = repository.getAllTransactions(token);
    }

    public LiveData<List<Transaction.Transactions>> getAllTransactionsDetail() {
        return getAllTransactionsResult;
    }

    private MutableLiveData<ShowTransaction> showTransactionResult = new MutableLiveData<>();

    public void showTransaction(String token, String transactionId) {
        showTransactionResult = repository.showTransaction(token, transactionId);
    }

    public LiveData<ShowTransaction> getShowTransactionResult() {
        return showTransactionResult;
    }
}
