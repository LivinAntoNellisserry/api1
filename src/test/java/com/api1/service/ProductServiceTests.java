package com.api1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.model.Api1Response;
import com.api1.model.Api2Response;
import com.api1.model.Product;
import com.api1.model.ProductClone;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@Autowired
	ProductServiceImpl service;

	Api2Response api2Response;
	Product product;
	ObjectMapper objectMapper;

	@MockBean
	WebClient webClientMock;

	@Mock
	private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

	@Mock
	private WebClient.RequestBodySpec requestBodySpecMock;

	@SuppressWarnings("rawtypes")
	@Mock
	private WebClient.RequestHeadersSpec requestHeadersSpecMock;

	@SuppressWarnings("rawtypes")
	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

	@Mock
	private WebClient.ResponseSpec responseSpecMock;

	@Mock
	private Mono<Api2Response> monoApi2Resp;

	@BeforeEach
	public void setup() {
		api2Response = new Api2Response();
		product = new Product();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getProductById() throws Exception {

		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate("2021-12-12");

		api2Response.setProductClone(this.getProductClone(product));
		api2Response.setResponseMessage("NOT EXPIRED");
		api2Response.setResponseType("SUCCESS");

		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(api2Response);

		Api1Response actualApi1Response = service.getProductById("G1");
		assertEquals("NOT EXPIRED", actualApi1Response.getStatus());

	}

	@Test
	@SuppressWarnings("unchecked")
	public void addProduct() throws Exception {
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate("2021-12-12");

		api2Response.setProductClone(this.getProductClone(product));
		api2Response.setResponseMessage("PRODUCT SAVED");
		api2Response.setResponseType("SUCCESS");

		when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
		when(requestBodyUriSpecMock.uri(Mockito.anyString())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.header(Mockito.any(), Mockito.any())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.bodyValue(Mockito.any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(api2Response);
		Api1Response actualApi1Response = service.addProduct(product);
		assertEquals("PRODUCT SAVED", actualApi1Response.getStatus());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void updateProduct() throws Exception {
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate("2021-12-12");

		api2Response.setProductClone(this.getProductClone(product));
		api2Response.setResponseMessage("PRODUCT UPDATED");
		api2Response.setResponseType("SUCCESS");

		when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
		when(requestBodyUriSpecMock.uri(Mockito.anyString())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.header(Mockito.any(), Mockito.any())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.bodyValue(Mockito.any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(api2Response);
		Api1Response actualApi1Response = service.updateProduct(product);
		assertEquals("PRODUCT UPDATED", actualApi1Response.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void deleteProduct() throws Exception {

		api2Response.setProductClone(null);
		api2Response.setResponseMessage("PRODUCT EXPIRED AND DELETED");
		api2Response.setResponseType("SUCCESS");

		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(api2Response);

		String actualApi1Response = service.deleteProduct("G1");
		assertEquals("PRODUCT EXPIRED AND DELETED", actualApi1Response);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getProductReturnNull() throws Exception {
		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(null);

		assertNull(service.getProductById("G1"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void addProductReturnNull() throws Exception
	{
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate("2021-12-12");

		when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
		when(requestBodyUriSpecMock.uri(Mockito.anyString())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.header(Mockito.any(), Mockito.any())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.bodyValue(Mockito.any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(null);
		
		assertNull(service.addProduct(product));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateProductReturnNull() throws Exception
	{
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate("2021-12-12");

		when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
		when(requestBodyUriSpecMock.uri(Mockito.anyString())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.header(Mockito.any(), Mockito.any())).thenReturn(requestBodySpecMock);
		when(requestBodySpecMock.bodyValue(Mockito.any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(null);
		
		assertNull(service.updateProduct(product));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deleteProductReturnNull() throws Exception
	{
		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Api2Response>>notNull())).thenReturn(monoApi2Resp);
		when(monoApi2Resp.block()).thenReturn(null);
		
		assertNull(service.deleteProduct("G1"));
		
	}

	private ProductClone getProductClone(Product product) {
		ProductClone productClone = new ProductClone();
		productClone.setCloneId(product.getId());
		productClone.setCloneProductExpiryDate(product.getProductExpiryDate().toString());
		productClone.setCloneProductId(product.getProductId());
		productClone.setCloneProductName(product.getProductName());
		return productClone;
	}

}