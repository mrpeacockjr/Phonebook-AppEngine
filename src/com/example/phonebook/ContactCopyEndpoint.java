package com.example.phonebook;

import com.example.phonebook.EMF;
import com.example.utils.ContactHelper;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "contactcopyendpoint", namespace = @ApiNamespace(ownerDomain = "example.com", ownerName = "example.com", packagePath = "phonebook"))
public class ContactCopyEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listContactCopy")
	public CollectionResponse<ContactCopy> listContactCopy(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {
		
		EntityManager mgr = null;
		Cursor cursor = null;
		List<ContactCopy> execute = null;
		
		System.out.println("Begin contactCopyEndpoint listContactCopy");
		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from ContactCopy as ContactCopy");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ContactCopy>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ContactCopy obj : execute)
				;
		} finally {
			mgr.close();
		}
		
		// adding sort code here.... try to put code that ill be user specific here as well
		execute = ContactHelper.sorter(execute);
		System.out.println("End contactCopyEndpoint listContactCopy");
		return CollectionResponse.<ContactCopy> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getContactCopy")
	public ContactCopy getContactCopy(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		ContactCopy contactcopy = null;
		try {
			contactcopy = mgr.find(ContactCopy.class, id);
		} finally {
			mgr.close();
		}
		return contactcopy;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param contactcopy the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertContactCopy")
	public ContactCopy insertContactCopy(ContactCopy contactcopy) {
		EntityManager mgr = getEntityManager();
		try {
			//if (containsContactCopy(contactcopy)) {
			//	throw new EntityExistsException("Object already exists");
			//}
			mgr.persist(contactcopy);
		} finally {
			mgr.close();
		}
		return contactcopy;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param contactcopy the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateContactCopy")
	public ContactCopy updateContactCopy(ContactCopy contactcopy) {
		EntityManager mgr = getEntityManager();
		try {
			//if (!containsContactCopy(contactcopy)) {
			//	throw new EntityNotFoundException("Object does not exist");
			//}
			mgr.persist(contactcopy);
		} finally {
			mgr.close();
		}
		return contactcopy;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeContactCopy")
	public void removeContactCopy(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ContactCopy contactcopy = mgr.find(ContactCopy.class, id);
			mgr.remove(contactcopy);
		} finally {
			mgr.close();
		}
	}

	private boolean containsContactCopy(ContactCopy contactcopy) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ContactCopy item = mgr.find(ContactCopy.class, contactcopy.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
