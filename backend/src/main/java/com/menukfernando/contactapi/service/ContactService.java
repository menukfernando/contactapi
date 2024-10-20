package com.menukfernando.contactapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.menukfernando.contactapi.domain.Contact;
import com.menukfernando.contactapi.exception.ContactNotFoundException;
import com.menukfernando.contactapi.repository.ContactRepository;

import jakarta.transaction.Transactional;

@Service
public class ContactService {
    private final Logger logger = LoggerFactory.getLogger(ContactService.class);
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
            if (contacts.isEmpty()) {
                logger.error("Contacts not found");
                throw new ContactNotFoundException("Contacts not found");
            }
        return contacts;
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Contact not found for the selected id {}", id);
                return new ContactNotFoundException("Contact not found for the selected id: " + id);
            });
    }

    public Contact createContact(Contact contact) {
        try {
            return contactRepository.save(contact);
        } catch (Exception e) {
            logger.error("failed to save contact", e);
            throw new ContactNotFoundException("failed to save contact");
        }
    }

    public Contact updateContact(Long id, Contact contactDetails) {
        Contact contact = getContactById(id);
        contact.setName(contactDetails.getName());
        contact.setGender(contactDetails.getGender());
        contact.setEmail(contactDetails.getEmail());
        contact.setJobTitle(contactDetails.getJobTitle());
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        Contact contact = getContactById(id);
        contactRepository.delete(contact);
    }

    @Transactional
    public List<Contact> createMultipleContacts(List<Contact> contacts) {
        return contactRepository.saveAll(contacts);
    }
}
