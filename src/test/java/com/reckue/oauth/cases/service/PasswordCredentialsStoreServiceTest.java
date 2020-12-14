package com.reckue.oauth.cases.service;

import com.reckue.oauth.factory.MockPasswordCredentialsFactory;
import com.reckue.oauth.factory.base.ExampleMatcherFactory;
import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.mock.MockPasswordCredentialsRepository;
import com.reckue.oauth.mock.MockPasswordCredentialsValidatorService;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.service.store.impl.PasswordCredentialsStoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reckue.oauth.utils.AlreadyExistsUtil.checkThrowReckuePasswordCredentialsAlreadyExists;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {
        MockPasswordCredentialsRepository.class,
        MockPasswordCredentialsValidatorService.class,
        PasswordCredentialsStoreService.class,
        MongoExampleFactory.class,
        ExampleMatcherFactory.class,
        MockUuidFactory.class,
        MockPasswordCredentialsFactory.class
})
public class PasswordCredentialsStoreServiceTest {

    @Autowired
    private PasswordCredentialsStoreService passwordCredentialsStoreService;

    @Autowired
    private MockPasswordCredentialsFactory mockPasswordCredentialsFactory;

    @Test
    public void create() {
        checkThrowReckuePasswordCredentialsAlreadyExists(() ->
                passwordCredentialsStoreService.create(mockPasswordCredentialsFactory.produce())
        );
    }

    @Test
    public void findById() {
        PasswordCredentials expected = mockPasswordCredentialsFactory.produce();
        String id = expected.getId();
        PasswordCredentials actual = passwordCredentialsStoreService.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        PasswordCredentials expected = mockPasswordCredentialsFactory.produce();
        String id = expected.getId();
        PasswordCredentials actual = passwordCredentialsStoreService.deleteById(id);
        assertEquals(expected, actual);
    }
}
