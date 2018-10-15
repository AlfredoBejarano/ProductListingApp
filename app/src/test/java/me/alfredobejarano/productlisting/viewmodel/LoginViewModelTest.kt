package me.alfredobejarano.productlisting.viewmodel

import me.alfredobejarano.productlisting.data.LoginRepository
import me.alfredobejarano.productlisting.data.LoginRequest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test case for the [LoginViewModel] class.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 14/10/2018 - 09:02 PM
 */
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @Mock
    lateinit var repository: LoginRepository

    /**
     * Asserts that requesting a login performs a webservice call.
     */
    @Test
    fun requestLogin() {
        // Create a new mock request, this will help to verify that the request was made.
        val mockRequest = Mockito.mock(LoginRequest::class.java)
        // Create the view model reference to test.
        val viewModel = LoginViewModel(repository)
        // Perform the mock login request.
        viewModel.requestLogin(mockRequest)
        // Verify that the request was made using the mock object.
        Mockito.verify(repository)
            .performLoginRequest(mockRequest)
    }
}