package ks.poterrycafe.repository;


import ks.poterrycafe.dto.response.CartSummaryResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartSummaryMybatisRepository {

    int findByCartIdCount(int cartId);

    int findByCartIdOfSummaryCount(int cartId);

    List<CartSummaryResponse> findByCartId(int cartId);

    void insertCartSummary(int cartId);

    void updateCartSummary(int cartId);

    void deleteCartSummary(int cartId);
}
