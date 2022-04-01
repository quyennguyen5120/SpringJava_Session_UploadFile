package com.example.spring.serivice.serviceImp;

import com.example.spring.modal.product;
import com.example.spring.repository.productRepository;
import com.example.spring.serivice.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductInterface {
    @Autowired
    productRepository productRepository;

    @Override
    public void createProduct(product p) {
        productRepository.createProduct(p);
    }

    @Override
    public List<product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public List<product> findByPage(int pageIndex, int pageSize) {
        return productRepository.findByPage(pageIndex,pageSize);
    }

}
