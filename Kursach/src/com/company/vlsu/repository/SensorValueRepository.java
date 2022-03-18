package com.company.vlsu.repository;

import com.company.vlsu.models.User;
import org.springframework.data.repository.CrudRepository;

interface SensorValueRepository extends CrudRepository<User, Long> {
}
