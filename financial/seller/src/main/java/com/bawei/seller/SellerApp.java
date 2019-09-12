package com.bawei.seller;

import com.bawei.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;

/*@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})*/
@SpringBootApplication
@EntityScan(basePackages = {"com.bawei.entity"})
@Import(SwaggerConfiguration.class)
public class SellerApp {

    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class,args);
    }


}
