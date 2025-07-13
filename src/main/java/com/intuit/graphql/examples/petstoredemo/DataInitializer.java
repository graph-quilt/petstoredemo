package com.intuit.graphql.examples.petstoredemo;

import com.intuit.graphql.examples.petstoredemo.model.Category;
import com.intuit.graphql.examples.petstoredemo.model.Pet;
import com.intuit.graphql.examples.petstoredemo.model.Tag;
import com.intuit.graphql.examples.petstoredemo.repository.CategoryRepository;
import com.intuit.graphql.examples.petstoredemo.repository.PetRepository;
import com.intuit.graphql.examples.petstoredemo.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PetRepository petRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public DataInitializer(PetRepository petRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.petRepository = petRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category dogs = new Category();
        dogs.setId("1");
        dogs.setName("Dogs");
        categoryRepository.save(dogs);

        Tag tag1 = new Tag();
        tag1.setId("1");
        tag1.setName("friendly");
        tagRepository.save(tag1);

        Pet pet = new Pet();
        pet.setId("1");
        pet.setName("Buddy");
        pet.setCategory(dogs);
        pet.setPhotoUrls(Collections.singletonList("url1"));
        pet.setTags(Collections.singletonList(tag1));
        pet.setStatus("AVAILABLE");
        petRepository.save(pet);
    }
} 