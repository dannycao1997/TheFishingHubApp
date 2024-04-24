package com.fishinghub.fishinghub.repository;
import com.fishinghub.fishinghub.entity.Catch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatchRepository extends JpaRepository<Catch, Long> {
}
