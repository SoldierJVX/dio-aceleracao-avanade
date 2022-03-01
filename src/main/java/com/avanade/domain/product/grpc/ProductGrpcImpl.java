package com.avanade.domain.product.grpc;

import com.avanade.domain.product.model.ProductModel;
import com.avanade.domain.product.repositories.ProductRepository;
import com.avanade.grpc.product.lib.ProductRequest;
import com.avanade.grpc.product.lib.ProductResponse;
import com.avanade.grpc.product.lib.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Optional;

@GrpcService
public class ProductGrpcImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductRepository repository;

    public ProductGrpcImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void productsByDescription(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        Optional<ProductModel> productOpt = repository.findByDescription(request.getDescription());
        ProductResponse productResponse;


        if (productOpt.isPresent()) {
            ProductModel productModel = productOpt.get();
            productResponse = ProductResponse.newBuilder()
                    .setId(productModel.getId())
                    .setDescription(productModel.getDescription())
                    .build()
        } else {
            productResponse = ProductResponse.newBuilder().build();
        }

        responseObserver.onNext(productResponse);
    }
}
