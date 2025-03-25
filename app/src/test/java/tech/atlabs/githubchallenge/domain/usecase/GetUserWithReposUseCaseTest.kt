package tech.atlabs.githubchallenge.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import tech.atlabs.githubchallenge.domain.repository.RepoRepository
import tech.atlabs.githubchallenge.domain.repository.UserRepository
import tech.atlabs.githubchallenge.util.getMockUserWithRepos
import tech.atlabs.githubchallenge.util.getMockUsername


class GetUserWithReposUseCaseTest {

    private lateinit var getUserWithReposUseCase: GetUserWithReposUseCase
    private lateinit var userRepository: UserRepository
    private lateinit var repoRepository: RepoRepository

    @Before
    fun setUp() {
        repoRepository = mock()
        getUserWithReposUseCase = GetUserWithReposUseCase(userRepository, repoRepository)
    }

    @Test
    fun `test that GetUserWithReposUseCaseTest returns user successfully`() = runTest {
        val username = getMockUsername()

        val mockUserWithRepos = getMockUserWithRepos()

        val result = getUserWithReposUseCase.invoke(username)

        // Assert that the use case returns the expected user
        assertTrue(result.isSuccess)
        assertEquals(mockUserWithRepos, result)
    }

    @Test
    fun `test that GetUserWithReposUseCaseTest returns failure for invalid username`() = runTest {
        val username = "vb wsj"
        val result = getUserWithReposUseCase.invoke(username)
        // Assert that the use case returns an error
        assertTrue(result.isFailure)
    }
}
