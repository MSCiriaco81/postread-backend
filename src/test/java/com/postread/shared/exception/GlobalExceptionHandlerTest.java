package com.postread.shared.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GlobalExceptionHandler")
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("handleNotFound: deve retornar 404 com mensagem correta")
    void handleNotFound_returns404() {
        var ex = new ResourceNotFoundException("User", "user-99");
        ProblemDetail pd = handler.handleNotFound(ex);

        assertThat(pd.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(pd.getDetail()).contains("user-99");
        assertThat(pd.getProperties()).containsKey("timestamp");
    }

    @Test
    @DisplayName("handleBusiness: deve retornar 400 com mensagem de negócio")
    void handleBusiness_returns400() {
        var ex = new BusinessException("Operação inválida");
        ProblemDetail pd = handler.handleBusiness(ex);

        assertThat(pd.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(pd.getDetail()).isEqualTo("Operação inválida");
    }

    @Test
    @DisplayName("handleConflict: deve retornar 409")
    void handleConflict_returns409() {
        var ex = new ConflictException("Email já em uso");
        ProblemDetail pd = handler.handleConflict(ex);

        assertThat(pd.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(pd.getDetail()).isEqualTo("Email já em uso");
    }

    @Test
    @DisplayName("handleBadCredentials: deve retornar 401 com mensagem genérica")
    void handleBadCredentials_returns401() {
        var ex = new BadCredentialsException("Wrong password");
        ProblemDetail pd = handler.handleBadCredentials(ex);

        assertThat(pd.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        assertThat(pd.getDetail()).isEqualTo("Invalid credentials");
    }

    @Test
    @DisplayName("handleAccessDenied: deve retornar 403")
    void handleAccessDenied_returns403() {
        var ex = new AccessDeniedException("Forbidden");
        ProblemDetail pd = handler.handleAccessDenied(ex);

        assertThat(pd.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("handleValidation: deve retornar 400 com mapa de erros de campo")
    void handleValidation_returns400WithFieldErrors() throws Exception {
        // simula erro de validação no campo "email"
        var bindingResult = new BeanPropertyBindingResult(new Object(), "request");
        bindingResult.addError(new FieldError("request", "email", "must not be blank"));
        var ex = new MethodArgumentNotValidException(null, bindingResult);

        ProblemDetail pd = handler.handleValidation(ex);

        assertThat(pd.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        @SuppressWarnings("unchecked")
        var fieldErrors = (java.util.Map<String, String>) pd.getProperties().get("fieldErrors");
        assertThat(fieldErrors).containsKey("email");
    }

    @Test
    @DisplayName("handleGeneral: deve retornar 500 para exceções inesperadas")
    void handleGeneral_returns500() {
        var ex = new RuntimeException("Algo inesperado");
        ProblemDetail pd = handler.handleGeneral(ex);

        assertThat(pd.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
