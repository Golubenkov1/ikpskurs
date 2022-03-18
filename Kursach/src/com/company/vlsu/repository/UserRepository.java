package com.company.vlsu.repository;

import com.company.vlsu.models.*;
import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, Long> {
}
