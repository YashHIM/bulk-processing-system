package com.yash.bulk_processing.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.bulk_processing.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

	Optional<Group> findByGroupId(String groupId);
}
