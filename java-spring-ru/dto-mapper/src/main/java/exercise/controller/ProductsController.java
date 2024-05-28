package exercise.controller;

import exercise.service.ProductCore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductCore productCore;

    // BEGIN
//    GET /products – вывод списка всех товаров
    @GetMapping
    public List<ProductDTO> findAll() {
        return productCore.findAllProducts();
    }
//    GET /products/{id} – просмотр конкретного товара
    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable long id) {
        return productCore.findById(id);
    }
//    POST /products – создание нового товара
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return productCore.createProduct(productCreateDTO);
    }
//    PUT /products/{id} – редактирование товара
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable long id,
                                    @RequestBody ProductUpdateDTO productUpdateDTO) {
        return productCore.updateProduct(id, productUpdateDTO);
    }
    // END
}
