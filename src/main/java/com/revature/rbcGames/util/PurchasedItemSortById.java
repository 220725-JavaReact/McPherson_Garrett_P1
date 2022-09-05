package com.revature.rbcGames.util;

import java.util.Comparator;

import com.revature.rbcGames.models.PurchasedItem;

public class PurchasedItemSortById implements Comparator<PurchasedItem>{

	@Override
	public int compare(PurchasedItem o1, PurchasedItem o2) {
	
		return o1.getId() - o2.getId();
	}

}
