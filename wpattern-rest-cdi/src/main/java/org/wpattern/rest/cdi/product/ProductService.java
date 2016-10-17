package org.wpattern.rest.cdi.product;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductService {

	@Inject
	private ProductData productData;

	@GET
	public List<ProductEntity> findAll() {
		return this.productData.findAll();
	}

	@POST
	public ProductEntity insert(ProductEntity entity) {
		return this.productData.insert(entity);
	}

	@PUT
	public void update(ProductEntity entity) {
		this.productData.update(entity);
	}

	@DELETE
	public void delete(ProductEntity entity) {
		this.productData.delete(entity);
	}

	@GET
	@Path("/{id}")
	public ProductEntity findById(@PathParam("id") Long id) {
		return this.productData.findById(id);
	}

}
