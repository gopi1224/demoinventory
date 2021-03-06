package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "sales", path = "sales")
public interface SalesRepository extends JpaRepository<Sales, Long> {
	List<Sales> findByCustomerType(@Param("customerType") String customertype);
}
