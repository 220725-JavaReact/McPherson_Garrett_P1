package com.revature.rbcGames.util;

import java.util.Comparator;

import com.revature.rbcGames.models.LineItem;

public class LineItemSortByProductId implements Comparator<LineItem>{

	@Override
	public int compare(LineItem o1, LineItem o2) {
		
		return o1.getProduct().getId() - o2.getProduct().getId();
	}
	
}
