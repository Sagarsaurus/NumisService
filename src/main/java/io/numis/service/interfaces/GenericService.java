package io.numis.service.interfaces;

import io.numis.domain.interfaces.DomainNode;
import spark.Request;
import spark.Response;

public interface GenericService {
	
	public void create(Request request, Response response);
	
	public void destroy(Request request, Response response);
	
	public void update(Request request, Response response);
	
	public DomainNode get(Request request, Response response);
}
