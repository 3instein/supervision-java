package com.example.supervision_java.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.supervision_java.R;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.EditOrderResponse;
import com.example.supervision_java.models.ShowOrder;
import com.example.supervision_java.views.activities.EditMenuActivity;
import com.example.supervision_java.views.activities.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class EditMenuAdapter extends RecyclerView.Adapter<EditMenuAdapter.EditViewHolder> {
    private Context context;
    public static List<ShowOrder.Order.Menus> menusList;

    public EditMenuAdapter(Context context) {
        this.context = context;
    }

    private List<ShowOrder.Order.Menus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<ShowOrder.Order.Menus> menusList) {
        this.menusList = menusList;
    }

    @NonNull
    @Override
    public EditMenuAdapter.EditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu, parent, false);
        return new EditMenuAdapter.EditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditMenuAdapter.EditViewHolder holder, int position) {
        final ShowOrder.Order.Menus menus = getMenusList().get(position);
        Glide.with(context).load(menus.getImage()).into(holder.cardMenuImage);
        holder.cardMenuName.setText(menus.getQuantity() + "x " + menus.getName());
        holder.cardMenuPrice.setText(Utils.convertToIdr(menus.getPrice()));

        holder.cardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.dialog_quantity, null);
                TextInputLayout dialogQuantity = dialogView.findViewById(R.id.dialogQuantity);
                dialogQuantity.getEditText().setText(Integer.toString(menus.getQuantity()));

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Menu : " + menus.getQuantity() + "x " + menus.getName());
                builder.setView(dialogView)
                        .setNegativeButton("Batal", (dialogInterface, i) -> {
                            dialogInterface.cancel();
                        }).setPositiveButton("Ubah", (dialogInterface, i) -> {
                            int quantity = Integer.parseInt(dialogQuantity.getEditText().getText().toString());
                            EditMenuActivity.orderViewModel.updateOrder("Bearer " + MainActivity.user.getToken(), EditMenuActivity.orderId, "update", menus.getId(), quantity);
                            EditMenuActivity.orderViewModel.getUpdateOrderDetail().observe(EditMenuActivity.lifecycleOwner, new Observer<EditOrderResponse>() {
                                @Override
                                public void onChanged(EditOrderResponse editOrderResponse) {
                                    if (editOrderResponse.getStatus_code() == 200) {
                                        EditMenuActivity.orderViewModel.showOrder("Bearer " + MainActivity.user.getToken(), EditMenuActivity.orderId);
                                        EditMenuActivity.orderViewModel.getShowOrderDetail().observe(EditMenuActivity.lifecycleOwner, EditMenuActivity.showOrder);
                                    }
                                }
                            });

                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMenusList().size();
    }

    public class EditViewHolder extends RecyclerView.ViewHolder {
        CardView cardMenu;
        ImageView cardMenuImage;
        TextView cardMenuName, cardMenuPrice;

        public EditViewHolder(@NonNull View itemView) {
            super(itemView);
            cardMenu = itemView.findViewById(R.id.cardMenu);
            cardMenuName = itemView.findViewById(R.id.cardMenuName);
            cardMenuImage = itemView.findViewById(R.id.cardMenuImage);
            cardMenuPrice = itemView.findViewById(R.id.cardMenuPrice);
        }
    }
}
