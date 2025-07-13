package com.intuit.graphql.examples.petstoredemo.repository;

import com.intuit.graphql.examples.petstoredemo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
} 