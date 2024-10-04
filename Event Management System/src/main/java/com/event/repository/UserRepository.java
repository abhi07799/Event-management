package com.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>
{
	Optional<UserModel> findByUserMail(String mail);
}
