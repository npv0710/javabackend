package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.DepartmentSpecification;

@Service
public class DepartmentService implements IDepartmentService{

	@Autowired
	private IDepartmentRepository dpRepository;
	
	@Override
	public List<Department> getListDepartments() {
		return dpRepository.findAll();
	}


	@Override
	public Page<Department> getListDepartmentsPaging(Pageable pageable, String search, DepartmentFilterForm dpFF) {
		Specification<Department> where = DepartmentSpecification.buildWhere(search, dpFF);
		return dpRepository.findAll(where, pageable);
	}

}
