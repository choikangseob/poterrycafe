package ks.poterrycafe.dto.response;

import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.DeleteCartRequest;
import ks.poterrycafe.entity.Cart;
import lombok.Builder;

@Builder
public record DeleteCartResponse(
        long cartID,
        long createMemberId
) {

    public static DeleteCartResponse of(Cart cartTobeDelete) {
     return DeleteCartResponse.builder()
             .cartID(cartTobeDelete.getCartId())
             .createMemberId(cartTobeDelete.getCreateMemberId())
             .build() ;
    }
}
