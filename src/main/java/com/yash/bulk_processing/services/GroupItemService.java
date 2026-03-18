package com.yash.bulk_processing.services;

import java.util.List;

import com.yash.bulk_processing.entities.GroupItem;

public interface GroupItemService {
	boolean exists(String groupId, String itemName);

	void saveBatch(List<GroupItem> items);
}
