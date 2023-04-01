package com.joalvarez.wharehousecontrol.product.service;

import com.joalvarez.wharehousecontrol.general.data.type.ResponseCode;
import com.joalvarez.wharehousecontrol.general.exception.GenericException;
import com.joalvarez.wharehousecontrol.general.service.BaseService;
import com.joalvarez.wharehousecontrol.product.data.dto.ProductDTO;
import com.joalvarez.wharehousecontrol.product.data.entity.Product;
import com.joalvarez.wharehousecontrol.product.data.mapper.ProductMapper;
import com.joalvarez.wharehousecontrol.product.data.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<ProductMapper> {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    public ProductDTO save(ProductDTO dto) {
        if (this.repository.existsBySkuIgnoreCase(dto.getSku())) {
            throw new GenericException(
                    HttpStatus.BAD_REQUEST,
                    ResponseCode.PRODUCT_ALREADY_EXISTS,
                    "El producto con SKU {" + dto.getSku() + "} ya existe"
            );
        }
        return this.mapper.toDTO(this.repository.save(this.mapper.toEntity(dto)));
    }

    public Page<ProductDTO> findAllPageable(Pageable pageable) {
        return this.repository.findAll(pageable).map(this.mapper::toDTO);
    }

    public ProductDTO delete(Long id) {
        Product product = this.repository.findById(id).orElseThrow(() -> new GenericException(
                HttpStatus.BAD_REQUEST,
                ResponseCode.PRODUCT_DONT_EXISTS,
                "El producto con id {" + id + "} no existe"
        ));

        product.setActive(!product.isActive());

        return this.mapper.toDTO(this.repository.save(product));
    }

}
