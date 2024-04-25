package com.fishinghub.fishinghub.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishinghub.fishinghub.controller.CatchController;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.entity.FishSpecies;
import com.fishinghub.fishinghub.entity.Location;
import com.fishinghub.fishinghub.service.CatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CatchController.class)
@AutoConfigureMockMvc
public class CatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CatchService catchService;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testLogCatch() throws Exception {
        Catch catchToLog = new Catch();
        FishSpecies fishSpecies = new FishSpecies();
        fishSpecies.setName("Trout");
        catchToLog.setFishSpecies(fishSpecies);
        Location location = new Location();
        location.setName("River");
        catchToLog.setLocation(location);
        catchToLog.setQuantity(5);

        when(catchService.logCatch(any(Catch.class))).thenReturn(catchToLog);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/catches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(catchToLog))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testDeleteCatch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/catches/1")
                 .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testUpdateCatch() throws Exception {
        Catch catchToUpdate = new Catch();
        catchToUpdate.setId(1L);
        FishSpecies fishSpecies = new FishSpecies();
        fishSpecies.setName("Trout");
        catchToUpdate.setFishSpecies(fishSpecies);
        Location location = new Location();
        location.setName("River");
        catchToUpdate.setLocation(location);
        catchToUpdate.setQuantity(5);

        when(catchService.updateCatch(any(Long.class), any(Catch.class))).thenReturn(catchToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/catches/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(catchToUpdate))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
