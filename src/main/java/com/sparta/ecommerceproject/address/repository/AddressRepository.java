package com.sparta.ecommerceproject.address.repository;

import com.sparta.ecommerceproject.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
}
