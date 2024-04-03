package com.techverse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techverse.Model.UserForm;

public interface UserFormRepository extends JpaRepository<UserForm, Long>{

}
