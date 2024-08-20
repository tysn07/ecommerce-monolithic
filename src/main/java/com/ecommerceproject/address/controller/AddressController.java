package com.ecommerceproject.address.controller;

import com.ecommerceproject.address.service.AddressService;
import com.ecommerceproject.global.security.UserDetailsImpl;
import com.ecommerceproject.address.dto.AddressRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/address")
@CrossOrigin("http://43.201.135.127:3000")
public class AddressController {

    private final AddressService addressService;
    @PostMapping
    public ResponseEntity<String> createAddress(
            @RequestBody AddressRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        addressService.createAddress(requestDto,userDetails.getUser());

        return ResponseEntity.ok("주소 생성 완료");
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable Long addressId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws AccessDeniedException {
        addressService.deleteAddress(addressId, userDetails.getUser());

        return ResponseEntity.ok("주소 삭제 완료");
    }

}
