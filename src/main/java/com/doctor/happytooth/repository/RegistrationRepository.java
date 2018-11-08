package com.doctor.happytooth.repository;

import com.doctor.happytooth.entity.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<Registration, Long> {

    List<Registration> findByReason(String reason);
}