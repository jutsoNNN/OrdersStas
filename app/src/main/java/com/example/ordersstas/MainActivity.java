package com.example.ordersstas;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.backendless.Backendless;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String APPID = "C0A895B0-EF11-396B-FFD1-6E65A8D69400";
    private static String APIKEY = "3286B362-42BA-A61A-FF75-816FE646FA00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backendless.initApp( this, APPID, APIKEY );

        OrderStas order = new OrderStas();

        OrderItemStas orderItem1 = new OrderItemStas();
        orderItem1.setName("Папка");
        orderItem1.setQuantity(4);
        orderItem1.setPrice(14);

        OrderItemStas orderItem2 = new OrderItemStas();
        orderItem2.setName("Карандаши");
        orderItem2.setQuantity(10);
        orderItem2.setPrice(4);

        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);
        order.setOrderName("Канцелярия");
        order.setOrderNumber(1);

        new SaveOrderTask().execute(order);
    }

    private class SaveOrderTask extends AsyncTask<OrderStas, Object, OrderStas> {
        @Override

        protected OrderStas doInBackground(OrderStas... orders) {
            OrderStas order = orders[0];

            List<OrderItemStas> orderItemList = order.getOrderItems();
            List<OrderItemStas> savedOrderItems = new ArrayList<OrderItemStas>();

            for (OrderItemStas orderItemStas : orderItemList) {
                OrderItemStas savedOrderItem = Backendless.Data.of(OrderItemStas.class).save(orderItemStas);
                savedOrderItems.add(savedOrderItem);
            }

            OrderStas savedOrder = Backendless.Data.of(OrderStas.class).save(order);
            Backendless.Data.of(OrderStas.class).addRelation(savedOrder,
                    "orderItems:OrderItemStas:n",
                    savedOrderItems);
            savedOrder.setOrderItems(savedOrderItems);
            return savedOrder;
        }
    }
}