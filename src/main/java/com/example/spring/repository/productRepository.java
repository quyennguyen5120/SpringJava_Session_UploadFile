package com.example.spring.repository;

import com.example.spring.modal.product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class productRepository {

    public void createProduct(product prduct){
        product p = new product();
        p = prduct;
        int id = product.lstProduct.size() +1;
        prduct.setId(id);
        product.lstProduct.add(prduct);
    }

    public List<product> getAll(){
        return product.lstProduct;
    }

    public List<product> findByPage(int pageIndex, int pageSize){
        return product.lstProduct.subList(pageIndex, pageSize);
    }

    public product findById(int id){
        return product.lstProduct.stream().filter(x->x.getId() == id).findAny().orElse(null);
    }
}
