---
title: 'Maximo 7.6.1 Java Customization in Eclipse: Setup + Hotfix Deployment'
slug: maximo-761-eclipse-java-customization-hotfix
description: 'A developer-focused walkthrough to set up Maximo 7.6.1 sources in Eclipse, create an Item/ItemSet customization, and deploy it by updating businessobjects.jar inside the Maximo EAR.'
tags:
  - maximo
  - ibm
  - eclipse
  - java
  - websphere
  - customization
  - enterprise
added: 'Jan 28 2026'
---

This post walks through a practical, end-to-end workflow for **IBM Maximo 7.6.1** Java customization:

1. **Set up a Maximo Java dev project in Eclipse** (using the correct WebSphere JRE + Maximo classes).
2. **Create a custom Item/ItemSet class** to extend Maximo's base functionality.
3. **Deploy the customization** by replacing/augmenting `businessobjects.jar` inside the deployed **Maximo EAR**.
4. **Point Maximo to your custom class** through Database Configuration and apply config changes.

> **Audience:** Maximo developers who need a repeatable workflow for local development + deployment.

---

## Workflow 1 — Set up the Maximo Java dev environment in Eclipse

### 1) Confirm Maximo install and locate sources

First, confirm Maximo is installed and locate the relevant directories. Commonly you’ll find Maximo under something like:

- `C:\IBM\SMP761\maximo\...`

This matters because you’ll need the compiled Maximo business objects classes on your build path.

---

### 2) Create a Java Project in Eclipse (Maximo 7.6.1 = Java 8)

In Eclipse:

- **Project Explorer → New → Java Project**
- Name it something like: `Max761`
- Set the Java compliance level to **JavaSE-1.8** for Maximo 7.6.1

> If you’re on an older Maximo version (e.g., 7.5), you may see Java 7 mentioned in older guides—but for **7.6.1**, Java 8 is the common target in this workflow.

**Screenshot:** New project creation  
![Create Java project](/java_custom_max/1.project.png)

---

### 3) Point Eclipse to the correct JRE (WebSphere Java)

Maximo runs on WebSphere, so you typically want Eclipse compiling against the **same JRE** used by the app server.

In Eclipse:

- **Project → Properties → Java Build Path → Libraries**
- Select **JRE System Library → Edit**
- Choose **Alternate JRE**
- **Installed JREs → Add… → Standard VM**
- Browse to WebSphere’s JRE folder (example):

- `C:\IBM\WebSphere\AppServer\java\8.0\jre`

Apply and close.

**Screenshots:** Project properties / JRE setup  
![Project properties](/java_custom_max/2.properties.png)  
![Java build path](/java_custom_max/3.java_build_path.png)  
![Library setup](/java_custom_max/3_lib_setup_2.png)  
![Libraries tab](/java_custom_max/4_java_build_lib.png)  
![Java build path complete](/java_custom_max/5_java_build_path_comp.png)  
![JRE selection](/java_custom_max/6.JRE_system.png)  
![JRE path](/java_custom_max/7_JRE_path.png)  
![JRE after setup](/java_custom_max/8_jre_after_setup.png)  
![JRE completion](/java_custom_max/9_JRE_after_compeletion.png)  
![JRE configuration complete](/java_custom_max/5_java_jre_comp.png)

---

### 4) Add Maximo business objects classes to the build path

Now add Maximo classes so your custom classes can extend Maximo base classes like `psdi.app.item.Item`.

In Eclipse:

- **Project → Properties → Java Build Path → Libraries**
- **Add External Class Folder**
- Point it to the Maximo business objects classes folder (example shown in the workflow):

- `C:\IBM\SMP761\maximo\applications\maximo\businessobjects\classes`

Apply and close.

**Screenshots:** Add external class folder  
![Add external class folder](/java_custom_max/10_adding_external_class.png)  
![External classes added](/java_custom_max/12_external_classes.png)

---

### 5) Create custom package + classes + interfaces

Create your package (example):

- `cust.app.item`

**Screenshots:**  
![Creating package](/java_custom_max/13_creating_package.png)  
![Package created](/java_custom_max/14_package_created.png)

Create the following (minimal) classes and interfaces:

#### Interfaces (Remote)

```java
// ItemRemote.java
public interface ItemRemote extends psdi.app.item.ItemRemote{
}

// ItemSetRemote.java
public interface ItemSetRemote extends psdi.app.item.ItemSetRemote{
}
```

#### Classes

**ItemSet.java** — Tells Maximo to use your custom Item class

```java
public class ItemSet extends psdi.app.item.ItemSet{
    public ItemSet(MboServerInterface ms) throws RemoteException, MXException {
        super(ms);
    }
    // getMboInstance() override goes here (see step 6)
}
```

**Item.java** — Custom Item class (extend with your logic as needed)

```java
public class Item extends psdi.app.item.Item{
    public Item(MboSet ms) throws RemoteException, MXException {
        super(ms);
    }
    // Add custom business logic here as needed
}
```

**Screenshot:** class extension / code structure  
![Extending Maximo Item class](/java_custom_max/15_extending_inventory_item_class.png)

---

### 6) Override `getMboInstance()` in `ItemSet`

In `ItemSet`, override `getMboInstance()` so Maximo instantiates your custom `Item` class.

**CRITICAL:** Without this override, Maximo will create default `psdi.app.item.Item` instances, and your custom `save()` validation will NEVER be called!

```java
@Override
protected Mbo getMboInstance(MboSet mboSet) throws MXException, RemoteException {
    // Create and return YOUR custom Item class instance
    // This ensures Maximo calls YOUR save() method with validation logic
    return new Item(mboSet);
}
```

Conceptually, you're telling the framework:

> When this set needs an MBO instance, return _my_ custom Item, not the default one.

---

## Workflow 2 — Deploy the customization (“hotfix”) into Maximo

This section covers a “drop-in” deployment approach by updating the `businessobjects.jar` inside the deployed Maximo EAR.

### 1) Locate the compiled `.class` files from Eclipse

After compiling, your generated `.class` files will be in your Eclipse workspace project output directory, e.g.:

- `Max761/bin/cust/app/item`

**Screenshot:** locating compiled output  
![Locate compiled classes](/java_custom_max/16_locate_compiled_class.png)

---

### 2) Stop the Maximo application server (WebSphere)

In the WebSphere admin console:

- **Servers → Server Types → WebSphere application servers**
- Stop the Maximo server

**Screenshot:**  
![Stop Maximo server](/java_custom_max/17_stop_maximo_server.png)

---

### 3) Find and extract `businessobjects.jar` from the deployed EAR

Navigate to the deployed EAR path, something like:

- `C:\IBM\WebSphere\AppServer\profiles\...\installedApps\...\MAX761.ear`

Locate:

- `businessobjects.jar`

Extract its contents to a folder so you can add your custom classes.

**Screenshot:**  
![Extract businessobjects.jar](/java_custom_max/18_extract_businessobjects.png)

---

### 4) Delete `businessobjects.jar` from the EAR folder

Delete the original jar from the EAR folder location (as shown in the workflow).  
This prepares for recreating the jar with your additions.

> Tip: Keep a backup of the original jar somewhere safe before you delete/replace it.

---

### 5) Copy your compiled custom classes into the extracted jar folder

Copy your `cust` package folder into the extracted `businessobjects.jar` contents:

- Copy: `cust/...`
- Into: extracted jar root (preserving package path)

**Screenshot:**  
![Copy customized classes](/java_custom_max/20_copy_the_customized_class.png)

---

### 6) Start the Maximo server again

Start the Maximo WebSphere application server.

**Screenshot:**  
![Start Maximo server](/java_custom_max/21_start_the_maximo_server.png)

---

### 7) Point Maximo to your custom class via Database Configuration

In Maximo:

- **System Configuration → Platform Configuration → Database Configuration**
- Search for object: `ITEM`
- Update the **Class** to: `cust.app.item.ItemSet`
- Save
- **Action → Manage Admin Mode**
  - set admin sessions allowed = `0`
  - Turn Admin Mode On
- **Action → Apply Configuration Changes**
  - run config (accept backup prompts)

**Screenshots:** DB config before/after + apply  
![Before DB config change](/java_custom_max/22_pre_db_config_change.png)  
![After DB config change](/java_custom_max/23_post_db_config_change.png)  
![Apply DB config](/java_custom_max/24_apply_db_config.png)  
![Checking changes status](/java_custom_max/25_checking_changes_status_change.png)

If you hit an error during apply/config, capture it and validate:

- your classes are in the jar correctly
- package names match exactly
- you rebuilt and copied the latest `.class` files
- the server was fully restarted after jar changes

---

### 7) Verify the customization in Item Master

Finally, verify that your custom Item class is being used:

- Go to **Item Master**
- Open an ITEM with active status
- Try to change it status other than active
- You will get a system message
- The expected behavior

The item should save successfully (confirming your custom `Item` class is instantiated via `getMboInstance()`)

**Screenshot:**  
![Error message](/java_custom_max/26_geting_an_error_message.png)

Your custom class is now active and ready for additional business logic as needed.

---

## Notes (Professional Reality Check)

- This approach is effective for learning and quick validation, but you should align with your org’s official Maximo deployment practices (build pipelines, packaging conventions, environments, promotion process).
- Keep backups before modifying deployed EAR/JAR artifacts.
- Treat this as a controlled hotfix workflow—document what changed, when, and why.

---

## Quick Checklist (TL;DR)

- [ ] Create Eclipse Java project (`JavaSE-1.8`)
- [ ] Point project JRE to WebSphere JRE
- [ ] Add Maximo `businessobjects\classes` to build path
- [ ] Create package `cust.app.item`
- [ ] Create `ItemSetRemote` and `ItemRemote` interfaces
- [ ] Create `ItemSet` class with `getMboInstance()` override
- [ ] Create `Item` class extending `psdi.app.item.Item`
- [ ] Compile all classes
- [ ] Copy compiled `.class` files into extracted `businessobjects.jar` contents
- [ ] Replace/update jar inside deployed EAR
- [ ] Restart server
- [ ] Update Database Configuration → object `ITEM` → class `cust.app.item.ItemSet`
- [ ] Apply configuration changes
- [ ] Verify in Item Master

---

## Complete Source Code

Below is the complete source code for all custom classes in the `cust.app.item` package.

### ItemRemote.java

```java
package cust.app.item;

public interface ItemRemote extends psdi.app.item.ItemRemote{

}
```

### ItemSetRemote.java

```java
package cust.app.item;

public interface ItemSetRemote extends psdi.app.item.ItemSetRemote{

}
```

### ItemSet.java

```java
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
```

### Item.java

```java
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
```

---

## Summary

You now have a complete, working example of **Maximo 7.6.1 Java customization** with:

- ✅ Eclipse project configured with WebSphere JRE + Maximo classes
- ✅ Custom `Item` and `ItemSet` classes extending Maximo base classes
- ✅ Remote interfaces for EJB compliance
- ✅ Proper `getMboInstance()` override to use your custom classes
- ✅ Deployment process via `businessobjects.jar` modification
- ✅ Database configuration pointing to your custom class

This foundation is ready for you to add your own business logic to the `Item` class as needed (validation, transformations, event handling, etc.).

**Next Steps:** Add custom methods or override other MBO lifecycle methods in `Item.java` to implement your specific business requirements.
