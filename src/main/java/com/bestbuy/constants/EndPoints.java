package com.bestbuy.constants;

public class EndPoints {

    /**
     * This is Endpoints of Best Buy - API
     */

    //Categories
    public static final String CREATE_NEW_CATEGORIES = "/categories";
    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String GET_SINGLE_CATEGORIES_BY_ID = "/{categoriesID}";
    public static final String UPDATE_CATEGORIES_BY_ID = "/categories/{categoriesID}";
    public static final String DELETE_CATEGORIES_BY_ID = "/categories/{categoriesID}";

    //PRODUCTS
    public static final String CREATE_NEW_PRODUCTS = "/products";
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/{productsID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productsID}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productsID}";
    public static final String NAME = "Name";

    //STORES
    public static final String GET_ALL_STORES = "/stores";
    public static final String CREATE_NEW_STORES = "/stores";
    public static final String GET_SINGLE_STORES_BY_ID = "/{storesID}";
    public static final String UPDATE_STORES_BY_ID = "/stores/{storesID}";
    public static final String DELETE_STORES_BY_ID = "/stores/{storesID}";

    //SERVICES
    public static final String GET_ALL_SERVICES = "/services";
    public static final String CREATE_NEW_SERVICES = "/services";
    public static final String GET_SINGLE_SERVICES_BY_ID = "/{servicesID}";
    public static final String UPDATE_SERVICES_BY_ID = "/services/{servicesID}";
    public static final String DELETE_SERVICES_BY_ID = "/services/{servicesID}";

    /**
     * This is Endpoints of Authentication api
     */

}
