/**
 * 
 */
package br.com.bonaldoapps.trymee.rest;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bonaldoapps.trymee.dao.TestGradeDAO;
import br.com.bonaldoapps.trymee.entity.TestGrade;
import br.com.bonaldoapps.trymee.entity.dto.TestGradeDTO;

/**
 * @author Daniel
 *
 */
@Path("/testGrades")
@Stateless
public class TestGradeResource extends BaseResource {

	@Inject
	private TestGradeDAO testGradeDAO;

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWithParams(@QueryParam("idProcess") Long idProcess) {

		List<TestGradeDTO> dtos = new LinkedList<TestGradeDTO>();

		for (TestGrade t : testGradeDAO.listWithParams(idProcess, null)) {

			dtos.add(new TestGradeDTO(t));

		}

		return Response.ok(dtos).build();

	}

}
