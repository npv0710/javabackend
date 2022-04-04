package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "/api/departments")
public class DepartmentController {
	
	@Autowired
	private IDepartmentRepository dpRepository;
	
	@Autowired
	private IDepartmentService dpService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<DepartmentDTO> getListDepartments() {
		List<Department> departments = dpRepository.findAll();
		List<DepartmentDTO> lsDpDTO = modelMapper.map(departments, new TypeToken< List<DepartmentDTO> >() {}.getType());
		return lsDpDTO;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/paging")
	public Page<DepartmentDTO> getListDepartmentsPaging(Pageable pageable, 
				@RequestParam(name = "search", required = false) String search,
				DepartmentFilterForm dpFF
			) {
		Page<Department> pgDepartment = dpService.getListDepartmentsPaging(pageable, search, dpFF);
		List<DepartmentDTO> listDepartmentDTO = modelMapper.map(pgDepartment.getContent(), new TypeToken< List<DepartmentDTO> >() {}.getType());
		Page<DepartmentDTO> pgDepartmentDTO = new PageImpl(listDepartmentDTO, pageable, pgDepartment.getTotalElements());
		return pgDepartmentDTO;
	}
	
}
