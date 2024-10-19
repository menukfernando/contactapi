package com.menukfernando.contactapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.menukfernando.contactapi.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {  
}
