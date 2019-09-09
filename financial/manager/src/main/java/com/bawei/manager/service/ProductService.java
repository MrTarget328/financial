package com.bawei.manager.service;


import com.bawei.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    public Product addProduct(Product product);

    public Product findOne(String id);

    Page<Product> query(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate,
                        List<String> statusList, Pageable pageable);
}
