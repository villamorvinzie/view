package com.villamorvinzie.view.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.villamorvinzie.view.domain.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);

    Mono<Void> deleteByUsername(String username);

    Mono<Boolean> existsByUsername(String username);

    default Mono<Boolean> existsByUsernameIgnoreCase(String username) {
        return existsByUsername(username.toLowerCase());
    }
}
