package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account>{
	Account findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	Account findByEmail(String email);
	
	Account findById(int id);
	
	@Query("SELECT ac FROM Account ac WHERE ac.firstName =:firstNameParameter")
	Account findByFirstName(@Param("firstNameParameter") String firstName);
	
	@Modifying
	@Transactional
	@Query("DELETE Account ac WHERE ac.id IN(:ids)")
	public void deleteMultipleAccounts(List<Integer> ids);
}
