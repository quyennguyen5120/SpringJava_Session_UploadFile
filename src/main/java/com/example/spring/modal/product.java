package com.example.spring.modal;

import java.util.ArrayList;
import java.util.List;

public class product {
    public Integer id;
    public String name;
    public String nameImage;
    public static List<product> lstProduct = new ArrayList<>();
    static{
        lstProduct.add(new product(1,"Mỳ1","3_20221648712639AnhDeMo.png"));
        lstProduct.add(new product(2,"Mỳ2","3_20221648712639AnhDeMo.png"));
        lstProduct.add(new product(3,"Mỳ3","3_20221648712639AnhDeMo.png"));
        lstProduct.add(new product(4,"Mỳ4","3_20221648712639AnhDeMo.png"));
        lstProduct.add(new product(5,"Mỳ5","3_20221648712639AnhDeMo.png"));
        lstProduct.add(new product(6,"Mỳ6","3_20221648712639AnhDeMo.png"));
        lstProduct.add(new product(7,"Mỳ7","3_20221648712639AnhDeMo.png"));
        lstProduct.add(new product(8,"Mỳ8","3_20221648712639AnhDeMo.png"));
    }
    public product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public static List<product> getLstProduct() {
        return lstProduct;
    }

    public static void setLstProduct(List<product> lstProduct) {
        product.lstProduct = lstProduct;
    }

    public product(Integer id, String name, String nameImage) {
        this.id = id;
        this.name = name;
        this.nameImage = nameImage;
    }
}
