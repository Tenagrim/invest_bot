package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
    Optional<Command> findByText(String text);
}
