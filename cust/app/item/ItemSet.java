package cust.app.item;

import java.rmi.RemoteException;

import psdi.mbo.Mbo;
import psdi.mbo.MboSet;
import psdi.mbo.MboServerInterface;
import psdi.util.MXException;

public class ItemSet extends psdi.app.item.ItemSet{

	public ItemSet(MboServerInterface ms) throws RemoteException, MXException {
		super(ms);
	}

	/**
	 * CRITICAL METHOD: Creates instances of your custom Item class
	 * 
	 * Without this override, Maximo will create default psdi.app.item.Item instances,
	 * and your custom save() validation will NEVER be called!
	 * 
	 * This method tells Maximo to create instances of YOUR custom cust.app.item.Item
	 * class instead of the default one.
	 */
	@Override
	protected Mbo getMboInstance(MboSet mboSet) throws MXException, RemoteException {
		// Create and return YOUR custom Item class instance
		// This ensures Maximo calls YOUR save() method with validation logic
		return new Item(mboSet);
	}

}