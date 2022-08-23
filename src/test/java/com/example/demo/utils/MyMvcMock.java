package com.example.demo.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Component
public class MyMvcMock {

	@Autowired
	private MockMvc mvc;

	public ResultActions created(String uri, Object objeto) throws Exception {
		return sendPost(uri, objeto).andExpect(status().isCreated());
	}

	public ResultActions createdWithBadRequest(String uri, Object objeto) throws Exception {
		return sendPost(uri, objeto).andExpect(status().isBadRequest());
	}

	public ResultActions update(String uri, Object objeto) throws Exception {
		return sendPut(uri, objeto).andExpect(status().isOk());
	}

	public ResultActions updateWithBadRequest(String uri, Object objeto) throws Exception {
		return sendPut(uri, objeto).andExpect(status().isBadRequest());
	}

	public ResultActions find(String uri) throws Exception {
		return sendGet(uri).andExpect(status().isOk());
	}

	public ResultActions findWithNotFound(String uri) throws Exception {
		return sendGet(uri).andExpect(status().isNotFound());
	}

	private ResultActions sendPost(String uri, Object objeto) throws Exception {
		return mvc.perform(post(uri).content(JSON.asJsonString(objeto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
	}

	private ResultActions sendPut(String uri, Object objeto) throws Exception {
		return mvc.perform(put(uri).content(JSON.asJsonString(objeto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
	}

	private ResultActions sendGet(String uri) throws Exception {
		return mvc.perform(get(uri).accept(MediaType.APPLICATION_JSON));
	}
}
