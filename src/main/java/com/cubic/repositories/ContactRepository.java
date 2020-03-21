package com.cubic.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cubic.entities.MainContactEntity;

@Repository
public interface ContactRepository extends CrudRepository<MainContactEntity, Integer> {

	MainContactEntity save(Optional<MainContactEntity> mainContactEntity);

}
