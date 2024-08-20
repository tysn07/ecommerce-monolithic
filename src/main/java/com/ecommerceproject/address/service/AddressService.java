package com.ecommerceproject.address.service;

import com.ecommerceproject.user.service.UserService;
import com.ecommerceproject.address.dto.AddressRequestDto;
import com.ecommerceproject.address.entity.Address;
import com.ecommerceproject.address.repository.AddressRepository;
import com.ecommerceproject.user.entity.User;
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
