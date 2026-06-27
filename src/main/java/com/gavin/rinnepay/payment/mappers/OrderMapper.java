package com.gavin.rinnepay.payment.mappers;
import com.gavin.rinnepay.payment.dtos.responses.OrderResponse;
import com.gavin.rinnepay.payment.entities.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderResponse toResponse(OrderRecord order);

    List<OrderResponse> toResponseList(List<OrderRecord> orders);
}
