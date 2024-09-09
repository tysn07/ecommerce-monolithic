package com.ecommerceproject.address.repository;

import com.ecommerceproject.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository  extends JpaRepository<Address, Long> {
    List<Address> findAddressesByUserId(Long UserId);
}
