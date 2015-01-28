package com.data;

/**
 * Created by dsher_000 on 11.12.2014.
 */
public class FlowDataProvider {

    public static String ADMIN_ORDER_ID;
    public static Integer ORDERED_PRODUCT_TOTAL_PRICE = 0;
    public static Integer ORDERED_PRODUCT_SHIPMENT_PRICE = 0;

    public void clear() {
        ADMIN_ORDER_ID = null;
        ORDERED_PRODUCT_TOTAL_PRICE = 0;
        ORDERED_PRODUCT_SHIPMENT_PRICE = 0;
    }
}
