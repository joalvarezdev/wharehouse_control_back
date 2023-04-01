package com.joalvarez.wharehousecontrol.product.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.wharehousecontrol.product.data.dto.ProductDTO;
import com.joalvarez.wharehousecontrol.product.data.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductMapper mapper;

    @Test
    public void toentity() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setSku("ART-01");
        dto.setDescription("Pan sin marcar");
        dto.setPrice(100.99);

        assertAll(
                () -> assertEquals(Product.class, this.mapper.toEntity(dto).getClass()),
                () -> assertNotNull(this.mapper.toEntity(dto).getSku()),
                () -> assertTrue(dto.getDescription().equalsIgnoreCase(this.mapper.toEntity(dto).getDescription()))
        );
    }

    @Test
    public void toDTO() {
        Product entity = new Product();
        entity.setId(1L);
        entity.setSku("ART-01");
        entity.setDescription("Pan sin marcar");
        entity.setPrice(100.99);

        assertAll(
                () -> assertEquals(ProductDTO.class, this.mapper.toDTO(entity).getClass()),
                () -> assertNotNull(this.mapper.toDTO(entity).getSku()),
                () -> assertTrue(entity.getDescription().equalsIgnoreCase(this.mapper.toDTO(entity).getDescription()))
        );
    }

}