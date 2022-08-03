package com.siliconstack.applications.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.service.TEApplicationsService;

@WebMvcTest(ApplicationsController.class)
public class ApplicationsControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TEApplicationsService teApplicationsService;
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    void testGetAllApplications_forEmptyResult() throws Exception {
        when(teApplicationsService.getAllTeApplications()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/application/getAllApplications"))
                .andExpect(status().isOk());

    }
     @Test
     void testGetAllApplications() throws Exception {
         List<TEApplications> applicationsList = new ArrayList<>();
         applicationsList.add(new TEApplications());

         when(teApplicationsService.getAllTeApplications()).thenReturn(applicationsList);

         this.mockMvc.perform(get("/application/getAllApplications"))
             .andExpect(status().isOk())
             .andExpect(content().json("[{'appid':0, 'appname':null, 'appdescription':null, 'deleted':false' 'projectid':0, 'platformid':0, createdBy':0, 'createdOn':null, 'updatedBy':0, 'updatedOn':null}]"));
     }

     @Test
     void testCreateApplication_ifApplicationAlreadyExist() throws Exception {
         when(teApplicationsService.saveTeApplications(mock(TEApplicationsDTO.class))).thenReturn(null);
         mockMvc.perform( MockMvcRequestBuilders.post("/application/createApplication")
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(new TEApplicationsDTO(0, "App1", "Test Application", false, 0, 0, 1234, new Date(), 0, null))))
         .andExpect(status().isAlreadyReported());
     }

     @Test
     void testCreateApplication_ifApplicationObjectIsNull() throws Exception {
    	 TEApplicationsDTO application = new TEApplicationsDTO();
    	 TEApplications newApplication = new TEApplications(0, "App1", "Test Application", false, 0, 0, 1234, new Date(), 0, null);

         when(teApplicationsService.saveTeApplications(new TEApplicationsDTO())).thenThrow(Exception.class);

         mockMvc.perform( MockMvcRequestBuilders.post("/application/createApplication")
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(null)))
         .andExpect(status().isBadRequest());
     }

     @Test
     void testCreateApplication_createApplication() throws Exception {
    	 TEApplicationsDTO application = new TEApplicationsDTO();
    	 TEApplications newApplication = new TEApplications(0, "App1", "Test Application", false, 0, 0, 1234, new Date(), 0, null);

         when(teApplicationsService.saveTeApplications(any())).thenReturn(newApplication);
         mockMvc.perform( MockMvcRequestBuilders.post("/application/createApplication")
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(new TEApplicationsDTO(0, "App1", "Test Application", false, 0, 0, 1234, new Date(), 0, null))))
         .andExpect(status().isCreated());
     }

}
