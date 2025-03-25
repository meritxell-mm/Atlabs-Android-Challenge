package tech.atlabs.githubchallenge.domain.usecase


import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import tech.atlabs.githubchallenge.domain.repository.UserRepository
import tech.atlabs.githubchallenge.util.getMockUser
import tech.atlabs.githubchallenge.util.getMockUsername


class GetUserUseCaseTest {

    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = mock()
        getUserUseCase = GetUserUseCase(userRepository)
    }

    @Test
    fun `test that GetUserUseCase returns user successfully`() = runTest {
        val username = getMockUsername()

        val mockUser = getMockUser()

        val result = getUserUseCase.invoke(username)

        // Assert that the use case returns the expected user
        assertTrue(result.isSuccess)
        assertEquals(mockUser, result)
    }

    @Test
    fun `test that GetUserUseCase returns failure for invalid username`() = runTest {
        val username = "vb wsj"
        val result = getUserUseCase.invoke(username)
        // Assert that the use case returns an error
        assertTrue(result.isFailure)
    }
}
