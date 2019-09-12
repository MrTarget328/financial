package com.bawei.seller.service;

import com.bawei.api.ProductRpc;
import com.bawei.api.domain.ProductRpcReq;
import com.bawei.entity.Product;
import com.bawei.entity.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductRpcService {

    @Autowired
    private ProductRpc productRpc;
    /**
     * 查询全部产品
     * @return
     */
    public List<Product> findAll(){
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add("1");
        req.setStatusList(status);
        log.info("rpc查询全部产品，请求：{}", req);
        List<Product> result = productRpc.query(req);
        log.info("rpc查询全部产品，结果：{}", result);
        return result;
    }
    public Product findOne(String id){
        log.info("rpc查询全部产品，请求：{}", id);
        Product result = productRpc.queryById(id);
        log.info("rpc查询全部产品，结果：{}", result);
        return result;
    }
}