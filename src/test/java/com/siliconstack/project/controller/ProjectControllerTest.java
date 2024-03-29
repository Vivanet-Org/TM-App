package com.siliconstack.project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconstack.project.dto.TEProjectDTO;
import com.siliconstack.project.model.TEProjects;
import com.siliconstack.project.service.TEProjectsService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TEProjectsService teProjectsService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllProjects_forEmptyResult() throws Exception {
        when(teProjectsService.getAllTeProjects()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/project/getAllProjects"))
                .andExpect(status().isOk());

    }
     @Test
     public void testGetAllProjects() throws Exception {
         List<TEProjects> projectList = new ArrayList<>();
         projectList.add(new TEProjects());

         when(teProjectsService.getAllTeProjects()).thenReturn(projectList);

         this.mockMvc.perform(get("/project/getAllProjects"))
             .andExpect(status().isOk())
             .andExpect(content().json("[{'projectid':0,'projectName':null,'projectDescription':null,'createdBy':0,'createdOn':null,'updatedBy':0,'updatedOn':null,'deleted':false}]"));
     }

//     @Test
//     public void testCreateProject_ifAlreadyExist() throws Exception {
//         when(teProjectsService.saveTeProjects(mock(TEProjectDTO.class))).thenReturn(null);
//         mockMvc.perform( MockMvcRequestBuilders.post("/project/createProject")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON)
//         .content(asJsonString(new TEProjectDTO(0, "Project1", "Description", false, 0, null, 0, null))))
//         .andExpect(status().isAlreadyReported());
//     }

//     @Test
//     public void testCreateProject_ifProjectObjectIsNull() throws Exception {
//         TEProjectDTO project = new TEProjectDTO();
//         TEProjects newProject = new TEProjects(0,"Project1",null, false, 0, null, 0, null);
//
//         when(teProjectsService.saveTeProjects(new TEProjectDTO())).thenThrow(Exception.class);
//
//         mockMvc.perform( MockMvcRequestBuilders.post("/project/createProject")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON)
//         .content(asJsonString(null)))
//         .andExpect(status().isBadRequest());
//     }

     @Test
     public void testCreateProject_createProject() throws Exception {
         TEProjectDTO project = new TEProjectDTO();
         TEProjects newProject = new TEProjects(0,"Project1",null, false,0,null,0,null);

         when(teProjectsService.saveTeProjects(any())).thenReturn(newProject);
         mockMvc.perform( MockMvcRequestBuilders.post("/project/createProject")
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(new TEProjectDTO(0, "Project1", "Description", false, 0, null, 0, null))))
         .andExpect(status().isCreated());
     }

     @Test
     public void testUpdateProject_successfully() throws Exception {
         TEProjectDTO existingProjects = new TEProjectDTO(0,"Project1", null, false, 0, null, 0, null);
         TEProjects updatedProjects = new TEProjects(0,"Project2","Description", false, 0, null, 1, null);

         when(teProjectsService.updateProject(existingProjects, 0)).thenReturn(updatedProjects);

         mockMvc.perform(MockMvcRequestBuilders.put("/project/updateProject/{id}", 0, existingProjects)
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(new TEProjectDTO(0, "Project1", "Description", false, 0, null, 0, null))))
         .andExpect(status().isOk());
     }

     @Test
     public void testDeleteProject() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.delete("/project/deleteProject/{id}", 0))
         .andExpect(status().isOk());
     }
}
