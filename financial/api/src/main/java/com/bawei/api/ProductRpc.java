package com.bawei.api;

import com.bawei.api.domain.ProductRpcReq;
import com.bawei.entity.Product;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;
@JsonRpcService("rpc/products")
public interface ProductRpc {


    /**
     * 查询多个产品
     */
    public List<Product> query(ProductRpcReq req);

    /**
     * 查询单个产品
     */
    public Product queryById(String id);

}
