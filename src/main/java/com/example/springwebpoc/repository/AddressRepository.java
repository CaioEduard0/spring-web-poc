package com.example.springwebpoc.repository;

import com.example.springwebpoc.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}