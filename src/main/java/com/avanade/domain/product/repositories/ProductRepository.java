package com.avanade.domain.product.repositories;

import com.avanade.domain.product.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductModel, String> {
}
