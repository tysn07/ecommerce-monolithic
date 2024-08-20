package com.ecommerceproject.kakaopay.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CancelResDto {
    private String tid;
    private String status;
    private Amount amount;
    private String item_name;
}
