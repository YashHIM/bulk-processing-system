package com.yash.bulk_processing.serviceImpls;

import org.springframework.stereotype.Service;

import com.yash.bulk_processing.entities.Group;
import com.yash.bulk_processing.repositories.GroupRepository;
import com.yash.bulk_processing.services.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;

	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Group getOrCreateGroup(String groupId, String groupName) {
		return groupRepository.findByGroupId(groupId).orElseGet(() -> {
			Group g = new Group();
			g.setGroupId(groupId);
			g.setGroupName(groupName);
			return groupRepository.save(g);
		});
	}
}
