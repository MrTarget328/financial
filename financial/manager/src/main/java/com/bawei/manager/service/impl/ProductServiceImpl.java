package com.bawei.manager.service.impl;

import com.bawei.entity.Product;
import com.bawei.manager.error.ErrorEnum;
import com.bawei.manager.repositories.ProductRepository;
import com.bawei.manager.service.ProductService;
import com.bawei.entity.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        log.debug("创建产品，参数：{}", product);
        setDefault(product);
        checkProduct(product);
        Product result = productRepository.save(product);
        log.debug("创建产品，结果：{}", result);
        return result;

    }
    /**
     * 设置默认值
     * 创建时间、更新时间、投资步长、锁定期
     * @param product
     */
    private void setDefault(Product product) {
        if (product.getCreateAt()==null){
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt()==null){
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount()==null){
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm()==null){
            product.setLockTerm(0);
        }
        if (product.getStatus()==null){
            product.setStatus(ProductStatus.AUDITING.name());
        }

    }

    /**
     * 产品数据校验
     * 1. 非空校验
     * 2. 收益率要0-30以内
     * 3. 投资步长需为整数
     * @param product
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getName(), ErrorEnum.NAME_NOT_NULL.getCode());
        Assert.notNull(product.getId(), ErrorEnum.ID_NOT_NULL.getCode());
        Assert.notNull(product.getThresholdAmount(), ErrorEnum.THRESHOLD_AMOUNT_NOT_NULL.getCode());
        Assert.notNull(product.getStepAmount(), ErrorEnum.STEP_AMOUNT_NOT_NULL.getCode());
        Assert.notNull(product.getLockTerm(), ErrorEnum.LOCK_TERM_NOT_NULL.getCode());
        Assert.notNull(product.getRewardRate(), ErrorEnum.REWARD_RATE_NOT_NULL.getCode());
        Assert.notNull(product.getStatus(), ErrorEnum.STATUS_NOT_NULL.getCode());
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate())<0
                && BigDecimal.valueOf(30).compareTo(product.getRewardRate())>=0, ErrorEnum.YIELD_RANGE_ERROR.getCode());
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount())==0,
                "投资步长需为整数");
    }

    /**
     * 查询单个产品
     * @param id 产品编号
     * @return 返回对应产品或Null
     */
    @Override
    public Product findOne(String id) {
        //校验数据
        Assert.notNull(id,"需要产品编号参数");
        log.debug("查询单个产品，id={}", id);
        Product product = productRepository.findOne(id);
        log.debug("查询单个产品，结果={}",product);
        return product;
    }

    /**
     * 分页查询
     * @param idList
     * @param minRewardRate
     * @param maxRewardRate
     * @param statusList
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> query(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate,
                               List<String> statusList, Pageable pageable) {
        log.debug("查询产品，idList={}，minRewardRate={}，maxRewardRate={}，statusList={}，pageable={}",idList,minRewardRate,maxRewardRate
        ,statusList,pageable);
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Expression<String> idCol  = root.get("id");
                Expression<BigDecimal> rewardRateCol   = root.get("rewardRate");
                Expression<String> statusCol  = root.get("status");
                List<Predicate> predicates = new ArrayList<>();
                if ( idList != null && idList.size()>0){
                    predicates.add(idCol.in(idList));
                }
                if (minRewardRate!=null && BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                    predicates.add(cb.ge(rewardRateCol, minRewardRate));
                }
                if (maxRewardRate!=null && BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                    predicates.add(cb.le(rewardRateCol, maxRewardRate));
                }
                if (statusList!=null && statusList.size()>0){
                    predicates.add(statusCol.in(statusList));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
        Page<Product> page = productRepository.findAll(specification, pageable);
        log.debug("查询产品，结果={}", page);
        return page;
    }
}
