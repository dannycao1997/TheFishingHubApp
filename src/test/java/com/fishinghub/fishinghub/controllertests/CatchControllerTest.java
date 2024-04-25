package com.fishinghub.fishinghub.controllertests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishinghub.fishinghub.controller.CatchController;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.service.CatchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CatchController.class)
public class CatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatchService catchService;

    @Autowired
    private ObjectMapper objectMapper;

    private Catch testCatch;

    @Before
    public void setUp() {
        testCatch = new Catch();
        testCatch.setId(1L);
        testCatch.setQuantity(5);
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testLogCatch() throws Exception {
        given(catchService.logCatch(any(Catch.class))).willReturn(testCatch);
        mockMvc.perform(post("/api/catches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCatch.getId()))
                .andExpect(jsonPath("$.quantity").value(testCatch.getQuantity()));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testGetAllCatches() throws Exception {
        List<Catch> allCatches = Arrays.asList(testCatch);
        given(catchService.getAllCatches()).willReturn(allCatches);
        mockMvc.perform(get("/api/catches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testCatch.getId()))
                .andExpect(jsonPath("$[0].quantity").value(testCatch.getQuantity()));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testGetCatchById() throws Exception {
        given(catchService.getCatchById(testCatch.getId())).willReturn(testCatch);
        mockMvc.perform(get("/api/catches/{id}", testCatch.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCatch.getId()))
                .andExpect(jsonPath("$.quantity").value(testCatch.getQuantity()));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testUpdateCatch() throws Exception {
        given(catchService.updateCatch(any(Catch.class))).willReturn(testCatch);
        mockMvc.perform(put("/api/catches/{id}", testCatch.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCatch.getId()))
                .andExpect(jsonPath("$.quantity").value(testCatch.getQuantity()));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testDeleteCatch() throws Exception {
        mockMvc.perform(delete("/api/catches/{id}", testCatch.getId()))
                .andExpect(status().isNoContent());
    }
}
