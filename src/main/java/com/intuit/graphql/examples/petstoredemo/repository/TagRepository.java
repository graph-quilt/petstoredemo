package com.intuit.graphql.examples.petstoredemo.repository;

import com.intuit.graphql.examples.petstoredemo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
} 