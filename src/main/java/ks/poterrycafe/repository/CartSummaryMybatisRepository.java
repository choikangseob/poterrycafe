package ks.poterrycafe.repository;


import ks.poterrycafe.dto.response.CartSummaryResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartSummaryMybatisRepository {

    List<CartSummaryResponse> findByCartId(int cartId);

}
