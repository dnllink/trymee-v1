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

import br.com.bonaldoapps.trymee.dao.CategoryDAO;
import br.com.bonaldoapps.trymee.entity.Category;
import br.com.bonaldoapps.trymee.entity.dto.CategoryDTO;

@Path("/categories")
@Stateless
public class CategoryResource extends BaseResource {

	@Inject
	private CategoryDAO categoryDAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryDTO> list(@QueryParam("searchText") String text) {

		List<Category> categories;

		if (text != null && !text.isEmpty()) {

			categories = categoryDAO.searchByText(text);

		} else {

			categories = categoryDAO.listAll();

		}

		List<CategoryDTO> dtos = new LinkedList<CategoryDTO>();

		for (Category l : categories) {

			dtos.add(new CategoryDTO(l));

		}

		return dtos;

	}

}