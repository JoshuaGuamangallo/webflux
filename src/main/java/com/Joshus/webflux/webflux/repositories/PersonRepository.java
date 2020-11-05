package com.Joshus.webflux.webflux.repositories;

import com.Joshus.webflux.webflux.documents.Person;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface PersonRepository extends ReactiveSortingRepository<Person,Long> {
}
