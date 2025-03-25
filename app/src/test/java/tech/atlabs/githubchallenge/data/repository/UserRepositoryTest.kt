package tech.atlabs.githubchallenge.data.repository

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService
import tech.atlabs.githubchallenge.domain.repository.UserRepository
import tech.atlabs.githubchallenge.util.getMockUser
import tech.atlabs.githubchallenge.util.getMockUsername

class UserRepositoryTest {

    private lateinit var userRepository: UserRepository
    private lateinit var apiService: GitHubUserApiService

    @Before
    fun setUp() {
        apiService = mock()
        userRepository = UserRepositoryImpl(apiService)
    }

    @Test
    fun `test that UserRepository returns user data successfully`() = runTest {

        val username = getMockUsername()
        val mockUser = getMockUser()

        val result = userRepository.getUser(username)

        // Assert that the repository returns the expected user
        assertEquals(mockUser, result)
    }

    @Test
    fun `test that UserRepository handles error correctly`() = runTest {
        val username = "dgb "

        // Assuming that getUser(username) returns a Result with an error message on failure
        val user = userRepository.getUser(username)
        assertEquals(user, null)
    }
}
