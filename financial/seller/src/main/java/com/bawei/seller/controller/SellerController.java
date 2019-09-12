package com.bawei.seller.controller;
import com.bawei.entity.Order;
import com.bawei.seller.params.OrderParam;
import com.bawei.seller.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
public class SellerController {

    @Autowired
    private OrderService orderService;
    /**
     * 下单
     * @return
     */
    @ApiOperation(value = "下单呢1",notes = "申购,下单")
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody OrderParam param){
        log.info("申购请求：{}", param);
        Order order = new Order();
        BeanUtils.copyProperties(param,order);
        order.setOuterOrderId(param.getOrderId());
        order = orderService.apply(order);
        log.info("申购结果：{}", param);
        return order;
    }
}
