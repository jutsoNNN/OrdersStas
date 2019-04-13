package com.example.ordersstas;
import java.util.ArrayList;
import java.util.List;

public class OrderStas {

        private int orderNumber;
        private String orderName;
        private List<OrderItemStas> orderItems;

        public String getOrderName()
        {
            return orderName;
        }

        public void setOrderName( String orderName )
        {
            this.orderName = orderName;
        }

        public int getOrderNumber()
        {
            return orderNumber;
        }

        public void setOrderNumber( int orderNumber )
        {
            this.orderNumber = orderNumber;
        }

        public void addOrderItem( OrderItemStas orderItem )
        {
            if( orderItems == null )
                orderItems = new ArrayList<OrderItemStas>();

            orderItems.add( orderItem );
        }

        public List<OrderItemStas> getOrderItems()
        {
            return orderItems;
        }

        public void setOrderItems( List<OrderItemStas> orderItems )
        {
            this.orderItems = orderItems;
        }
    }
