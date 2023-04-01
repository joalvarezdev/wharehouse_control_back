package com.joalvarez.wharehousecontrol.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.wharehousecontrol.TestApplicationTests;
import com.joalvarez.wharehousecontrol.general.data.type.ResponseCode;
import com.joalvarez.wharehousecontrol.general.exception.GenericException;
import com.joalvarez.wharehousecontrol.product.data.dto.ProductDTO;
import com.joalvarez.wharehousecontrol.product.data.entity.Product;
import com.joalvarez.wharehousecontrol.product.data.mapper.ProductMapper;
import com.joalvarez.wharehousecontrol.product.data.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationTests.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService serviceTest;
    @Mock
    private ProductRepository repository;
    @Mock
    private ProductMapper mapper;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private Pageable pageable;

    @Test
    public void throwGenericExceptionWhenSKUExists() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setSku("ART-01");
        dto.setDescription("Pan sin marcar");
        dto.setPrice(100.99);

        when(this.repository.existsBySkuIgnoreCase(dto.getSku())).thenReturn(true);

        GenericException exception = assertThrows(GenericException.class, () -> this.serviceTest.save(dto));

        assertEquals(ResponseCode.PRODUCT_ALREADY_EXISTS.message(), exception.getResponseCode().message());
        assertEquals(ResponseCode.PRODUCT_ALREADY_EXISTS.code(), exception.getResponseCode().code());
    }

    @Test
    public void saveProduct() {
        Product entity = new Product();
        entity.setSku("ART-01");
        entity.setDescription("Pan sin marcar");
        entity.setPrice(100.99);

        ProductDTO dto = new ProductDTO();
        dto.setSku("ART-01");
        dto.setDescription("Pan sin marcar");
        dto.setPrice(100.99);

        when(this.repository.existsBySkuIgnoreCase(entity.getSku())).thenReturn(false);
        when(this.mapper.toEntity(dto)).thenReturn(entity);
        when(this.mapper.toDTO(entity)).thenReturn(dto);
        when(this.repository.save(entity)).thenReturn(entity);

        this.serviceTest.save(dto);

        assertAll(
                () -> assertEquals(dto.getSku(), entity.getSku()),
                () -> assertEquals(dto.getDescription(), entity.getDescription()),
                () -> assertEquals(dto.getPrice(), entity.getPrice())
        );
    }

    @Test
    public void findAllPageable() {
        List<Product> list = new ArrayList<>();

        Product entity = new Product();
        entity.setId(1L);
        entity.setSku("ART-01");
        entity.setDescription("Pan sin marcar");
        entity.setPrice(100.99);

        list.add(entity);
        Page<Product> page = new PageImpl<>(list, this.pageable, list.size());

        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setSku("ART-01");
        dto.setDescription("Pan sin marcar");
        dto.setPrice(100.99);

        when(this.mapper.toDTO(entity)).thenReturn(dto);
        when(this.repository.findAll(this.pageable)).thenReturn(page);

        this.serviceTest.findAllPageable(this.pageable);

        assertEquals(1, page.getTotalPages());
    }

    @Test
    public void throwGenericExceptionWhenIdNotExists() {
        Long id = 2L;
        when(this.repository.findById(id)).thenReturn(Optional.empty());

        GenericException ex = assertThrows(GenericException.class, () -> this.serviceTest.delete(id));

        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus()),
                () -> assertEquals(ResponseCode.PRODUCT_DONT_EXISTS.message(), ex.getResponseCode().message()),
                () -> assertEquals(ResponseCode.PRODUCT_DONT_EXISTS.code(), ex.getResponseCode().code())
        );
    }

    @Test
    public void changeValueActiveWhenProductExists() {
        Long id = 1L;
        Product entity = new Product();

        entity.setId(id);
        entity.setSku("ART-01");
        entity.setDescription("Coca-Cola default");
        entity.setPrice(546.76);
        entity.setActive(true);

        when(this.repository.findById(id)).thenReturn(Optional.of(entity));

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setSku(entity.getSku());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setActive(entity.isActive());

        when(this.mapper.toDTO(entity)).thenReturn(dto);

        this.serviceTest.delete(id);

        assertNotEquals(dto.isActive(), entity.isActive());
    }
}