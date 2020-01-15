package br.com.bonaldoapps.trymee.rest;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.dao.LevelDAO;
import br.com.bonaldoapps.trymee.entity.Level;
import br.com.bonaldoapps.trymee.entity.dto.LevelDTO;

@Path("/levels")
@Stateless
public class LevelResource extends BaseResource {

	@Inject
	private LevelDAO levelDAO;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response list() {

		List<Level> levels = levelDAO.listAll();

		List<LevelDTO> dtos = new LinkedList<LevelDTO>();

		for (Level l : levels) {

			dtos.add(new LevelDTO(l));

		}

		return Response.ok(dtos).build();

	}

}