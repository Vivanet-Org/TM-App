package com.siliconstack.applications.service;

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

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.repository.TEApplicationsRepository;

@RunWith(MockitoJUnitRunner.class)
public class TEApplicationsServiceTest {
	
	 @Mock
	 TEApplicationsRepository teApplicationsRepository;
	 
	 @InjectMocks
	 public TEApplicationsService service;
	 
	 @Test
	    public void return_applicationslist_when_call_getAllApplications() {
	    	TEApplications teApplications = new TEApplications(0, "App1", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null);
	
	        when(teApplicationsRepository.findAll()).thenReturn(Arrays.asList(teApplications));
	        Iterable<TEApplications> result = service.getAllTeApplications();
	        assertNotNull(result);
	        assertEquals(((Collection<?>) result).size(), 1);
	    }
	 
	 @Test
	    public void when_save_application_it_should_return_new_application() throws Exception {
		 TEApplicationsDTO teApplicationsDTO = new TEApplicationsDTO(0, "App1", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null);
		 TEApplications newApplication = new TEApplications(0,"App1", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null);

	        when(teApplicationsRepository.findByAppName(teApplicationsDTO.getAppName())).thenReturn(Collections.emptyList());
	        when(teApplicationsRepository.save(any(TEApplications.class))).thenReturn(newApplication);
	        TEApplications result = service.saveTeApplications(teApplicationsDTO);
	        assertEquals(result.getAppName(), teApplicationsDTO.getAppName());
	    }

	    @Test
	    public void return_null_when_application_already_exist_for_save_application() throws Exception {
	    	TEApplicationsDTO teApplicationsDTO = new TEApplicationsDTO(0,"App1", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null);

	        List<TEApplications> applicationsList = new ArrayList<>();
	        applicationsList.add(new TEApplications());

	        when(teApplicationsRepository.findByAppName(anyString())).thenReturn(applicationsList);
	        TEApplications result = service.saveTeApplications(teApplicationsDTO);
	        assertNull(result);
	    }
	    
	    @Test
	    public void update_application_when_there_is_change() {
	    	TEApplicationsDTO teApplicationsDTO = new TEApplicationsDTO(0,"App1", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null);
	        long appID = 0;
	        Optional<TEApplications> teApplications = Optional.of(new TEApplications(0,"App123", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null));
	        TEApplications newApplication = new TEApplications(0,"App1", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null);

	        when(teApplicationsRepository.findById(appID)).thenReturn(teApplications);

	        when(teApplicationsRepository.save(any(TEApplications.class))).thenReturn(newApplication);

	        TEApplications result = service.updateApplication(teApplicationsDTO, appID);
	        assertEquals(result.getAppName(), teApplicationsDTO.getAppName());
	    }

//	    @Test(expected = ResourceNotFoundException.class)
//	    public void throw_exception_if_application_not_found_during_update() {
//	        when(teApplicationsRepository.findById((long) 0)).thenReturn( Optional.empty());
//
//	        TEApplications result = service.updateApplication(new TEApplicationsDTO(), 0);
//	    }

	    @Test
	    public void delete_application_if_available() {
	        long appID = 0;
	        Optional<TEApplications> teApplications = Optional.of(new TEApplications(0,"App1", "Test Applicatio", false, 0, 0, 1234, new Date(), 0, null));
	        when(teApplicationsRepository.findById(appID)).thenReturn(teApplications);
	        service.deleteApplication(appID);

	    }

}
