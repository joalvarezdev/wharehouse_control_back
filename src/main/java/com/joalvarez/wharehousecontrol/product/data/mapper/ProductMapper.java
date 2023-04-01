package com.joalvarez.wharehousecontrol.product.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.wharehousecontrol.general.data.mapper.BaseMapper;
import com.joalvarez.wharehousecontrol.product.data.dto.ProductDTO;
import com.joalvarez.wharehousecontrol.product.data.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<ProductDTO, Product> {

    public ProductMapper(ObjectMapper mapper) {
        super(mapper);
    }
}
