package com.yash.bulk_processing.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.bulk_processing.entities.GroupItem;
import com.yash.bulk_processing.repositories.GroupItemRepository;
import com.yash.bulk_processing.services.GroupItemService;

import jakarta.transaction.Transactional;

@Service
public class GroupItemServiceImpl implements GroupItemService {

	private final GroupItemRepository groupItemRepository;

	public GroupItemServiceImpl(GroupItemRepository groupItemRepository) {
		this.groupItemRepository = groupItemRepository;
	}

	@Override
	public boolean exists(String groupId, String itemName) {
		return groupItemRepository.existsByGroup_GroupIdAndItemName(groupId, itemName);
	}

	@Override
	@Transactional
	public void saveBatch(List<GroupItem> items) {
		groupItemRepository.saveAll(items);
	}
}
