package com.data;

import com.utils.LoadProperties;

public class DataProvider {


    public static final String USERNAME = "mrosupplytest@gmail.com";
    public static final String PASSWORD = "Toriabra909";
    public static final String ADMIN_USERNAME = "nicoletavisteanu@yahoo.com";
    public static final String ADMIN_PASSWORD = "nico7777";
    public static final String CREDITCARD_NUMBER = "4242424242424242";
    public static final String ENVIROMENT_URL = LoadProperties.loadProperty("url");
    public static final String USER_EMAIL = LoadProperties.loadProperty("gmail.address");
    public static Product ADDED_PRODUCT;
    public static Product SELECTED_PRODUCT;
    public static final String TEST_FILES_TO_UPLOAD = "src/main/resources/testfiletoupload.txt";
    public static String PRODUCT_MIN_QUANTITY;

    public void clear() {
        new FlowDataProvider().clear();
        SELECTED_PRODUCT = null;
        ADDED_PRODUCT = null;
        PRODUCT_MIN_QUANTITY = null;
    }
}
