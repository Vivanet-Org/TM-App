package com.siliconstack.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siliconstack.model.TeProjects;
import com.siliconstack.repository.TeProjectsRepository;
import com.siliconstack.service.TeProjectsService;

@Service
public class TeProjectsServiceImpl implements TeProjectsService{
	
	private TeProjectsRepository teProjectsRepository;
	
	public TeProjectsServiceImpl(TeProjectsRepository teProjectsRepository) {
		super();
		this.teProjectsRepository = teProjectsRepository;
	}

	@Override
	public TeProjects saveTeProjects(TeProjects teProjects) {
		return teProjectsRepository.save(teProjects);
	}

	@Override
	public List<TeProjects> getAllTeProjects() {
		return teProjectsRepository.findAll();
	}
}
