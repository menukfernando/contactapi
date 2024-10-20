package com.menukfernando.contactapi.seeder;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.menukfernando.contactapi.domain.Contact;
import com.menukfernando.contactapi.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DataSeeder implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);
    private final ContactRepository contactRepository;

    public DataSeeder(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Application is ready. Seeding data...");

        if (contactRepository.count() == 0) {
            seedContacts();
        } else {
            logger.info("Contacts are already seeded.");
        }
    }

    private void seedContacts() {
        Contact contact1 = new Contact();
        contact1.setName("Menuk Fernando");
        contact1.setEmail("menukfernando7@gmail.com");
        contact1.setGender("Male");
        contact1.setJobTitle("Software Engineer");

        Contact contact2 = new Contact();
        contact2.setName("John Doe");
        contact2.setEmail("johndoe@example.com");
        contact2.setGender("Male");
        contact2.setJobTitle("Manager");

        Contact contact3 = new Contact();
        contact3.setName("Jane Smith");
        contact3.setEmail("janesmith@example.com");
        contact3.setGender("Female");
        contact3.setJobTitle("Data Analyst");

        Contact contact4 = new Contact();
        contact4.setName("Emily Clark");
        contact4.setEmail("emilyclark@example.com");
        contact4.setGender("Female");
        contact4.setJobTitle("Product Designer");

        Contact contact5 = new Contact();
        contact5.setName("Michael Johnson");
        contact5.setEmail("michaeljohnson@example.com");
        contact5.setGender("Male");
        contact5.setJobTitle("DevOps Engineer");

        // Save contacts into the database
        contactRepository.save(contact1);
        contactRepository.save(contact2);
        contactRepository.save(contact3);
        contactRepository.save(contact4);
        contactRepository.save(contact5);

        logger.info("Contact data seeded.");
    }
}
