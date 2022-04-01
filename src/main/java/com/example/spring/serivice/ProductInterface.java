package com.example.spring.serivice;

import com.example.spring.modal.product;

import java.util.List;

public interface ProductInterface {
    public void createProduct(product p);
    public List<product> getAll();
    public List<product> findByPage(int pageIndex, int pageSize);
}
