package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<Product> index(@RequestParam(defaultValue = "0") Integer min,
                               @RequestParam(defaultValue = "0") Integer max) {
        List<Product> products;
        if (min > 0 && max > 0) {
            products = productRepository.findByPriceBetweenOrderByPrice(min, max);
        } else if (min > 0 && max == 0) {
            products = productRepository.findByPriceGreaterThanEqualOrderByPrice(min);
        } else if (min == 0 && max > 0) {
            products = productRepository.findByPriceLessThanEqualOrderByPrice(max);
        } else {
            products = productRepository.findAll(Sort.by("price"));
        }
        return products;
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
