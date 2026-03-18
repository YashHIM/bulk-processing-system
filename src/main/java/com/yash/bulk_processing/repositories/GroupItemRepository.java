package com.yash.bulk_processing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.bulk_processing.entities.GroupItem;

public interface GroupItemRepository extends JpaRepository<GroupItem, Long> {

	boolean existsByGroup_GroupIdAndItemName(String groupId, String itemName);
}
