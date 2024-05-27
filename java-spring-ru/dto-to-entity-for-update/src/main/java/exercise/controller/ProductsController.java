package exercise.controller;

import exercise.service.ProductCore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductCore productCore;

    @GetMapping(path = "")
    public List<ProductDTO> index() {
        return productCore.findAllProducts();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable long id) {
        return productCore.findProductById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productData) {
        return productCore.createProduct(productData);
    }

    // BEGIN
    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable long id,
                             @RequestBody ProductUpdateDTO productData) {
        return productCore.updateProduct(id, productData);
    }
    // END
}
