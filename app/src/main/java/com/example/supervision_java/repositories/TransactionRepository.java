package com.example.supervision_java.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.api.ApiService;
import com.example.supervision_java.models.Transaction;
import com.example.supervision_java.views.fragments.HistoryFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionRepository {
    private static TransactionRepository repository;

    private TransactionRepository() {
    }

    public static TransactionRepository getInstance() {
        if (repository == null) {
            repository = new TransactionRepository();
        }

        return repository;
    }

    private List<Transaction.Transactions> transactionsList = new ArrayList<>();

    public MutableLiveData<List<Transaction.Transactions>> getAllTransactions(String token) {
        final MutableLiveData<List<Transaction.Transactions>> allTransactionsResult = new MutableLiveData<>();

        ApiService.endPoint().getAllTransactions(token).enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if (response.isSuccessful()) {
                    transactionsList.addAll(response.body().getTransactions());
                    allTransactionsResult.setValue(transactionsList);
                } else {
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        Toast.makeText(HistoryFragment.context, error.getString("status_message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return allTransactionsResult;
    }
}
