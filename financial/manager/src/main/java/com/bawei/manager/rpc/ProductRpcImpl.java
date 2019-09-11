package com.bawei.manager.rpc;

import com.bawei.api.ProductRpc;
import com.bawei.api.domain.ProductRpcReq;
import com.bawei.entity.Product;
import com.bawei.manager.service.ProductService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
@Service
@AutoJsonRpcServiceImpl
@Slf4j
public class ProductRpcImpl implements ProductRpc {

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        log.info("查询多个产品，请求：{}", req);
        Pageable pageable = new PageRequest(0,1000, Sort.Direction.DESC,"rewardRate");
        Page<Product> result = productService.query(req.getIdList(), req.getMinRewardRate(),
                req.getMaxRewardRate(), req.getStatusList(), pageable);
        log.info("查询多个产品，结果：{}", result);
        return result.getContent();
    }

    @Override
    public Product queryById(String id) {
        log.info("查询单个产品，请求：{}", id);
        Product product = productService.findOne(id);
        log.info("查询单个产品，结果：{}", product);
        return product;
    }



}
