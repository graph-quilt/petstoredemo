package com.intuit.graphql.examples.petstoredemo.datafetcher;

import com.intuit.graphql.examples.petstoredemo.codegen.DgsConstants;
import com.intuit.graphql.examples.petstoredemo.codegen.types.Category;
import com.intuit.graphql.examples.petstoredemo.codegen.types.Pet;
import com.intuit.graphql.examples.petstoredemo.codegen.types.Tag;
import com.intuit.graphql.examples.petstoredemo.repository.PetRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@DgsComponent
public class PetDataFetcher {

    private final PetRepository petRepository;

    @Autowired
    public PetDataFetcher(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @DgsQuery(field = DgsConstants.QUERY.Pet)
    public Pet getPet(@InputArgument("id") String id) {
        return petRepository.findById(id).map(this::toGqlPet).orElse(null);
    }

    private Pet toGqlPet(com.intuit.graphql.examples.petstoredemo.model.Pet petEntity) {
        Pet pet = new Pet();
        pet.setId(petEntity.getId());
        pet.setName(petEntity.getName());
        pet.setStatus(com.intuit.graphql.examples.petstoredemo.codegen.types.PetStatus.valueOf(petEntity.getStatus()));

        if (petEntity.getCategory() != null) {
            Category category = new Category();
            category.setId(petEntity.getCategory().getId());
            category.setName(petEntity.getCategory().getName());
            pet.setCategory(category);
        }

        if (petEntity.getTags() != null) {
            pet.setTags(petEntity.getTags().stream().map(tagEntity -> {
                Tag tag = new Tag();
                tag.setId(tagEntity.getId());
                tag.setName(tagEntity.getName());
                return tag;
            }).collect(Collectors.toList()));
        }

        pet.setPhotoUrls(petEntity.getPhotoUrls());

        return pet;
    }
} 