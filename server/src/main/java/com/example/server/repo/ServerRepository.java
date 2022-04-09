package com.example.server.repo;

import com.example.server.entity.LogingRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends CrudRepository<LogingRequest, Long> {
}
