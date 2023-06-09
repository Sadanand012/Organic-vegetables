package com.organic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organic.model.BillingDetails;

@Repository
public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Integer> {

}
