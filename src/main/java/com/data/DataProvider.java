package com.data;

import com.utils.LoadProperties;

public class DataProvider {


    public static final String USERNAME = "mrosupplytesting@gmail.com";
    public static final String PASSWORD = "Toriabra909";
    public static final String ADMIN_USERNAME = "nicoletavisteanu@yahoo.com";
    public static final String ADMIN_PASSWORD = "nico7777";
    public static final String CREDITCARD_NUMBER = "4242424242424242";
    public static final String ENVIROMENT_URL = LoadProperties.loadProperty("url");
    public static final String USER_EMAIL = LoadProperties.loadProperty("gmail.address");
    public static Product ADDED_PRODUCT;
    public static Product SELECTED_PRODUCT;

    public DataProvider() throws Exception {

        new FlowDataProvider();
        SELECTED_PRODUCT = null;
        ADDED_PRODUCT = null;
    }

}
