package com.yash.bulk_processing.services;

import com.yash.bulk_processing.entities.Group;

public interface GroupService {
	Group getOrCreateGroup(String groupId, String groupName);
}
