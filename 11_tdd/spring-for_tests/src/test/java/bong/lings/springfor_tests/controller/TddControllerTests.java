package bong.lings.springfor_tests.controller;

import bong.lings.springfor_tests.model.Member;
import bong.lings.springfor_tests.service.TDDService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TddController.class)
class TddControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TDDService service;

	@Test
	public void helloWorldGetTests() throws Exception {
		when(service.member()).thenReturn(new ArrayList<Member>());

		this.mockMvc.perform(get("/member"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

	@Test
	public void helloWorldPostTests() throws Exception {
		when(service.member()).thenReturn(new ArrayList<Member>());

		this.mockMvc.perform(post("/member"))
				.andDo(print())
				.andExpect(status().isOk());

	}
}
