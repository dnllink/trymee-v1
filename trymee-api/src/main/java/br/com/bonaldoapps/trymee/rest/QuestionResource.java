package br.com.bonaldoapps.trymee.rest;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.dto.QuestionDTO;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;
import br.com.bonaldoapps.trymee.rest.filter.RequiresAuthToken;
import br.com.bonaldoapps.trymee.service.QuestionService;

@Path("/questions")
@Stateless
public class QuestionResource extends BaseResource {

	@Inject
	private QuestionService questionService;

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response listWithParams(@QueryParam("type") String type, @QueryParam("level") Long level,
			@QueryParam("category") Long category, @QueryParam("active") Long active,
			@QueryParam("searchText") String searchText) {

		Long idUser = getUserId();

		List<QuestionDTO> dtos = new LinkedList<>();

		if (searchText != null && !searchText.isEmpty()) {

			dtos = questionService.listByText(searchText, idUser);

		} else {

			Boolean activeParam = true;

			if (active != null) {
				activeParam = (active == 1 ? true : false);
			}

			QuestionTypeEnum typeId = null;

			if (type != null) {
				typeId = QuestionTypeEnum.getByDescription(type);
			}

			dtos = questionService.listwithParams(activeParam, typeId, category, level, idUser);

		}

		return Response.ok(dtos).build();

	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response find(@PathParam("id") long id) {

		Long idUser = getUserId();

		QuestionDTO dto = questionService.find(id, true, idUser);

		if (dto == null) {

			return Response.noContent().build();

		}

		return Response.ok(dto).build();

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response create(Question question) {

		Long idUser = getUserId();

		questionService.save(question, idUser);

		return Response.created(null).build();

	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@RequiresAuthToken
	public Response delete(@PathParam("id") long id) {

		Long idUser = getUserId();

		questionService.delete(id, idUser);

		return Response.ok().build();

	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresAuthToken
	public Response activate(Question question) {
		
		Long idUser = getUserId();

		questionService.update(question, idUser);

		return Response.ok().build();

	}
}