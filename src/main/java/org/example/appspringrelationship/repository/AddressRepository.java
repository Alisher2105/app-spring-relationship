package org.example.appspringrelationship.repository;

import org.example.appspringrelationship.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
