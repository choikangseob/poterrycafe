package ks.poterrycafe.repository;

import ks.poterrycafe.dto.response.AddCartItemsResponse;
import ks.poterrycafe.dto.response.GetCartItemsResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface GetCartItemsMybatisRepository {
    List<GetCartItemsResponse> findByCartIdAndProductId(int cartId, int productId);
}
