package com.data;

import net.thucydides.core.pages.WebElementFacade;

/**
 * Created by dys on 25.11.2014.
 */
public class Product {

    private WebElementFacade productElement;
    private String productName;
    private String productPrice;
    private String productId;

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public WebElementFacade getProductElement() {
        return productElement;
    }

    public void setProductElement(WebElementFacade productElement) {
        this.productElement = productElement;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
