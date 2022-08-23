package com.revature.rbcGames.DAO;

import java.util.ArrayList;

public interface DAO<T> {
	T AddInstance(T newInstance);
	//T GetInstanceByID(int id);
	ArrayList<T> GetAllInstances();
	T UpdateInstance(T instance);
	boolean RemoveInstance(T instance);
}
