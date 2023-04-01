package com.joalvarez.wharehousecontrol.product.controller;

import com.joalvarez.wharehousecontrol.TestApplicationTests;
import com.joalvarez.wharehousecontrol.product.data.dto.ProductDTO;
import com.joalvarez.wharehousecontrol.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationTests.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;
    @Mock
    private ProductService service;
    @Mock
    private Pageable pageable;

    @Test
    public void save() {
        ProductDTO dto = new ProductDTO();
        dto.setSku("ART-01");
        dto.setDescription("Pan sin marcar");
        dto.setPrice(320.98);

        when(this.service.save(dto)).thenReturn(dto);
        ResponseEntity<ProductDTO> result = this.controller.save(dto);
        assertAll(
                () -> assertEquals(200, result.getStatusCodeValue()),
                () -> assertEquals(dto.getSku(), Objects.requireNonNull(result.getBody()).getSku())
        );
    }

    @Test
    public void findAllPageable() {
        List<ProductDTO> list = new ArrayList<>();
        Page<ProductDTO> page = new PageImpl<>(list, this.pageable, 0);
        when(this.service.findAllPageable(this.pageable)).thenReturn(page);

        ResponseEntity<Page<ProductDTO>> result = this.controller.findAllPageable(this.pageable);
        assertAll(
                () -> assertEquals(200, result.getStatusCodeValue()),
                () -> assertEquals(page.getClass(), Objects.requireNonNull(result.getBody()).getClass())
        );
    }
}