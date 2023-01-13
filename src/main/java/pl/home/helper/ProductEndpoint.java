package pl.home.helper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.home.helper.models.ProductDto;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductEndpoint {

    RestTemplate restTemplate;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> showProduct(@PathVariable String id) {
        return restTemplate.getForEntity("http://localhost:8083/product/{id}", ProductDto.class, id);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(ProductDto product) {
        return restTemplate.postForEntity("http://localhost:8083/product", product, ProductDto.class);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return restTemplate.exchange("http://localhost:8083/product",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                });
    }

    @GetMapping(value = "/test")
    public ResponseEntity<ProductDto> showTestProduct() {
        return new ResponseEntity<>(new ProductDto("test product", 122), HttpStatus.FOUND);
    }

}
