package com.siliconstack.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.siliconstack.project.dto.TEProjectDTO;
import com.siliconstack.project.model.TEProjects;
import com.siliconstack.project.repository.TEProjectRepository;


@RunWith(MockitoJUnitRunner.class)
public class TEProjectsServiceTest {
	
	@Mock
    TEProjectRepository teProjectsRepository;

    @InjectMocks
    TEProjectsService service;
    
    @Test
    public void return_projectlist_when_call_getAllProject() {
        TEProjects teProjects = new TEProjects(0,"Project1","Test Project",false, 1234, new Date(), 0, null);

        when(teProjectsRepository.findAll()).thenReturn(Arrays.asList(teProjects));
        Iterable<TEProjects> result = service.getAllTeProjects();
        assertNotNull(result);
        assertEquals(((Collection<?>) result).size(), 1);
    }
    
    @Test
    public void when_save_project_it_should_return_new_project() throws Exception {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project1", "Test Project", false, 1234, new Date(), 0, null);
        TEProjects newProject = new TEProjects(0,"Project1", "Test Project", false, 1234, new Date(), 0, null);

        when(teProjectsRepository.findByProjectName(teProjectDto.getProjectName())).thenReturn(Collections.emptyList());
        when(teProjectsRepository.save(any(TEProjects.class))).thenReturn(newProject);
        TEProjects result = service.saveTeProjects(teProjectDto);
        assertEquals(result.getProjectName(), teProjectDto.getProjectName());
    }

    @Test
    public void return_null_when_project_already_exist_for_save_project() throws Exception {
        TEProjectDTO teProjectDto = new TEProjectDTO(0, "Project1", "Test Project", false, 1234, new Date(), 0, null);

        List<TEProjects> projectList = new ArrayList<>();
        projectList.add(new TEProjects());

        when(teProjectsRepository.findByProjectName(anyString())).thenReturn(projectList);
        TEProjects result = service.saveTeProjects(teProjectDto);
        assertNull(result);
    }

    @Test
    public void update_project_when_there_is_change() {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project12", "Test Project", false, 1234, new Date(), 1234, new Date());
        long projectID = 0;
        Optional<TEProjects> teProjects = Optional.of(new TEProjects(0, "Project1", "Test Project", false, 1234, new Date(), 0, null));
        TEProjects newProject = new TEProjects(0,"Project12", "Test Project", false, 1234, new Date(), 1234, new Date());

        when(teProjectsRepository.findById(projectID)).thenReturn(teProjects);

        when(teProjectsRepository.save(any(TEProjects.class))).thenReturn(newProject);

        TEProjects result = service.updateProject(teProjectDto, projectID);
        assertEquals(result.getProjectName(), teProjectDto.getProjectName());
    }

//    @Test(expected = ResourceNotFoundException.class)
//    public void throw_exception_if_project_not_found_during_update() {
//        when(teProjectsRepository.findById((long) 0)).thenReturn( Optional.empty());
//
//        TEProjects result = service.updateProject(new TEProjectDTO(), 0);
//    }

    @Test
    public void delete_project_if_available() {
        long projectID = 0;
        Optional<TEProjects> teProject = Optional.of(new TEProjects(0, "Project1", "Test Project", false, 1234, new Date(), 0, null));
        when(teProjectsRepository.findById(projectID)).thenReturn(teProject);
        service.deleteProject(projectID);

    }

}
