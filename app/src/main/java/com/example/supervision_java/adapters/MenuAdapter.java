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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.supervision_java.R;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.EditOrderResponse;
import com.example.supervision_java.models.Menu;
import com.example.supervision_java.viewmodels.MenuViewModel;
import com.example.supervision_java.views.activities.AddMenuActivity;
import com.example.supervision_java.views.activities.MainActivity;
import com.example.supervision_java.views.activities.OrderDetailActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    public static List<Menu.Menus> menusList;

    public MenuAdapter(Context context) {
        this.context = context;
    }

    private List<Menu.Menus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<Menu.Menus> menusList) {
        this.menusList = menusList;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu, parent, false);
        return new MenuAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        final Menu.Menus menuResult = getMenusList().get(position);
        Glide.with(context).load(menuResult.getImage()).into(holder.cardMenuImage);
        holder.cardMenuName.setText(menuResult.getName());
        holder.cardMenuPrice.setText(Utils.convertToIdr(menuResult.getPrice()));

        holder.cardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.dialog_quantity, null);
                TextInputLayout dialogQuantity = dialogView.findViewById(R.id.dialogQuantity);

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Menu: " + menuResult.getName());
                builder.setView(dialogView)
                        .setNegativeButton("Batal", (dialogInterface, i) -> {
                            dialogInterface.cancel();
                        }).setPositiveButton("Tambah", (dialogInterface, i) -> {
                            int quantity = Integer.parseInt(dialogQuantity.getEditText().getText().toString());
                            OrderDetailActivity.orderViewModel.updateOrder("Bearer " + MainActivity.user.getToken(), AddMenuActivity.orderId, "add", menuResult.getId(), quantity);
                            OrderDetailActivity.orderViewModel.getUpdateOrderDetail().observe(AddMenuActivity.lifecycleOwner, new Observer<EditOrderResponse>() {
                                @Override
                                public void onChanged(EditOrderResponse editOrderResponse) {
                                    if (editOrderResponse.getStatus_code() == 200) {
                                        Toast.makeText(context, editOrderResponse.getMessage(), Toast.LENGTH_SHORT).show();
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

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        CardView cardMenu;
        ImageView cardMenuImage;
        TextView cardMenuName, cardMenuPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            cardMenu = itemView.findViewById(R.id.cardMenu);
            cardMenuName = itemView.findViewById(R.id.cardMenuName);
            cardMenuImage = itemView.findViewById(R.id.cardMenuImage);
            cardMenuPrice = itemView.findViewById(R.id.cardMenuPrice);
        }
    }
}
