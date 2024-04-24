package com.fishinghub.fishinghub.repository;
import com.fishinghub.fishinghub.entity.FishSpecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishSpeciesRepository extends JpaRepository<FishSpecies, Long> {
}
