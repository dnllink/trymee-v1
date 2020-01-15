package br.com.trymee.tests;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.bonaldoapps.trymee.entity.dto.Credentials;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CandidateTest {

//	@Test
//	public void deveListarUsuarios() {
//
//		int contador = 0;
//
//		String email = null;
//
//		for (int i = 0; i < 100; i++) {
//
//			JsonPath path = get("https://app.trymee.com.br/trymee/api/info/users?token=tm-valid-token").andReturn()
//					.jsonPath();
//
//			email = path.getString("[0].email");
//
//			contador++;
//
//		}
//
//		assertEquals(email, "admin@tm.com");
//
//	}

//	@Test
//	public void deveLogarComSucesso() {
//
//		Response response = given().body(new Credentials("dnllink@hotmail.com", "Senh@2017"))
//				.post("https://app.trymee.com.br/trymee/api/tokens").andReturn();
//		
//		String token = response.getStatusLine();
//		
//		assertEquals(token, "200");
//
//	}

}
