package cust.app.item;

import java.rmi.RemoteException;

import psdi.mbo.MboSet;
import psdi.mbo.MboSetRemote;
import psdi.util.MXApplicationException;
import psdi.util.MXException;

public class Item extends psdi.app.item.Item{

	public Item(MboSet ms) throws RemoteException, MXException {
		super(ms);
	}

	@Override
	public void save() throws MXException, RemoteException {
		// Validate business rule: Block save if item is not active but has stock
		validateActiveStatusWithStock();
		
		// Perform the actual save
		super.save();
	}

	/**
	 * Validates that if an item has stock in any storeroom (INVBAL records),
	 * it must be in ACTIVE status. Throws exception if validation fails.
	 */
	private void validateActiveStatusWithStock() throws MXException, RemoteException {
		String status = this.getString("STATUS");
		String itemNum = this.getString("ITEMNUM");
		
		// Check if item is NOT active
		if (status == null || !status.equalsIgnoreCase("ACTIVE")) {
			// Look for any inventory balances (stock) for this item
			if (this.checkInvBalancesExists()) {
				// Item has stock but is not active - violation!
				throw new MXApplicationException("cust", "ITEM_NOT_ACTIVE_HAS_STOCK", 
					new String[]{itemNum, status});
			}
		}
	}

}