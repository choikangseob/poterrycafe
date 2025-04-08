package ks.poterrycafe.repository;

import ks.poterrycafe.dto.response.SaveProductResponse;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;


@Mapper
public interface GetProductMybatisRepository {
    List<SaveProductResponse> findProductIdAndProductNameAndDescriptionAndQuantityAndPrice();
}
