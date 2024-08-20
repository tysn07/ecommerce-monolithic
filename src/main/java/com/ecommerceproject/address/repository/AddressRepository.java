package com.ecommerceproject.address.repository;

import com.ecommerceproject.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
}
