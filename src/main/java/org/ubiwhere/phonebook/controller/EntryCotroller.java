package org.ubiwhere.phonebook.controller;

import org.ubiwhere.phonebook.persistence.dao.EntryDao;
import org.ubiwhere.phonebook.persistence.model.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entries")
public class EntryCotroller {

    private EntryDao entryDao = new EntryDao();

    @GET
    @Path("/")
    public Response getEntries() {
        // replace with service call instead of dao
        // when dao is replaced Entry also should be replace by EntryDto
        List<Entry> entry = entryDao.findAll();
        return Response.ok().entity(entry).build();
    }
}
