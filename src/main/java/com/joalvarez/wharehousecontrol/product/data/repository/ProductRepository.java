package com.joalvarez.wharehousecontrol.product.data.repository;

import com.joalvarez.wharehousecontrol.product.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySkuIgnoreCase(String sku);
}
