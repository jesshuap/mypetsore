package com.chtrembl.petstore.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chtrembl.petstore.pet.model.Pet;

@Repository
public interface PetServiceRepository extends JpaRepository<Pet, Long> {
    // Define custom queries or use Spring Data methods
}
