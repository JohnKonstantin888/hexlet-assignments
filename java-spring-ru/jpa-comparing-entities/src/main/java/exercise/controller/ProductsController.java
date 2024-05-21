package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.exception.ResourceAlreadyExistsException;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        Optional<Product> optional = productRepository.findByTitleAndPrice(product.getTitle(), product.getPrice());
        if (optional.isPresent()) {
            throw new ResourceAlreadyExistsException(product.getTitle(), product.getPrice());
        }
        return productRepository.save(product);
    }
    // END

    @GetMapping("/{id}")
    public Product findById(@PathVariable long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id,
                                 @RequestBody Product productData) {
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return productRepository.save(
                product
                        .setTitle(productData.getTitle())
                        .setPrice(productData.getPrice())
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
