package com.revature.rbcGames.DAO;

import java.util.ArrayList;

public interface DAO<T> {
	T AddInstance(T newInstance);
	ArrayList<T> AddInstances(ArrayList<T> newInstances);
	//T GetInstanceByID(int id);
	ArrayList<T> GetAllInstances();
	T UpdateInstance(T instance);
	boolean RemoveInstance(T instance);
}
