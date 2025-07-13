package com.intuit.graphql.examples.petstoredemo.repository;

import com.intuit.graphql.examples.petstoredemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
} 