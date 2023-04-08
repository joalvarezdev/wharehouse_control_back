package com.joalvarez.wharehousecontrol.product.controller;

import com.joalvarez.wharehousecontrol.product.data.dto.ProductDTO;
import com.joalvarez.wharehousecontrol.product.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(this.service.save(dto));
    }

    @GetMapping
    @ApiImplicitParams({@ApiImplicitParam(
            value = "page",
            paramType = "query",
            dataType = "integer",
            defaultValue = "0"
    ), @ApiImplicitParam(
            value = "size",
            paramType = "query",
            dataType = "integer",
            defaultValue = "${spring.data.web.pageable.default-page-size:20}"
    )
    })
    public ResponseEntity<Page<ProductDTO>> findAllPageable(
            @ApiIgnore Pageable pageable
            ) {
        return ResponseEntity.ok(this.service.findAllPageable(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.delete(id));
    }
}
