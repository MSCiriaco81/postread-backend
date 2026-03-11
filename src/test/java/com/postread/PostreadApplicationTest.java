package com.postread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
@DisplayName("PostreadApplication")
class PostreadApplicationTest {

    @Test
    @DisplayName("contexto Spring deve carregar sem erros")
    void contextLoads() {
        // Se o contexto subir, o teste passa
    }
}
