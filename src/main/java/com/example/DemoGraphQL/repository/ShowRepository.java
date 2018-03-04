package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Show;
import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {
}
