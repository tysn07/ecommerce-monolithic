package com.sparta.ecommerceproject.address.service;

import com.sparta.ecommerceproject.address.dto.AddressRequestDto;
import com.sparta.ecommerceproject.address.entity.Address;
import com.sparta.ecommerceproject.address.repository.AddressRepository;
import com.sparta.ecommerceproject.user.entity.User;
import com.sparta.ecommerceproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public void createAddress(AddressRequestDto requestDto, User user) {
       Address address = new Address(requestDto, user.getId());
       addressRepository.save(address);
    }

    public void deleteAddress(Long addressId, User user) throws AccessDeniedException {
        Address address = findOne(addressId);

        if(!address.getUserId().equals(user.getId())) {
            throw new AccessDeniedException("해당 주소에 대한 권한이 없습니다.");
        }
        addressRepository.delete(address);
    }

    public Address findOne(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("주소를 찾을 수 없습니다."));
    }

}
