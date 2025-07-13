package com.intuit.graphql.examples.petstoredemo.datafetcher;

import com.intuit.graphql.examples.petstoredemo.model.Category;
import com.intuit.graphql.examples.petstoredemo.model.Pet;
import com.intuit.graphql.examples.petstoredemo.model.Tag;
import com.intuit.graphql.examples.petstoredemo.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetDataFetcherTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetDataFetcher petDataFetcher;

    @Test
    void getPet() {
        Category dogs = new Category();
        dogs.setId("1");
        dogs.setName("Dogs");

        Tag tag1 = new Tag();
        tag1.setId("1");
        tag1.setName("friendly");

        Pet petEntity = new Pet();
        petEntity.setId("1");
        petEntity.setName("Buddy");
        petEntity.setCategory(dogs);
        petEntity.setPhotoUrls(Collections.singletonList("url1"));
        petEntity.setTags(Collections.singletonList(tag1));
        petEntity.setStatus("AVAILABLE");

        when(petRepository.findById("1")).thenReturn(Optional.of(petEntity));

        com.intuit.graphql.examples.petstoredemo.codegen.types.Pet result = petDataFetcher.getPet("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Buddy", result.getName());
    }
} 