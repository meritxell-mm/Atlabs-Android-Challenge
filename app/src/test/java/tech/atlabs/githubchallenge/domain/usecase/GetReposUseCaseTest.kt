package tech.atlabs.githubchallenge.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import tech.atlabs.githubchallenge.domain.repository.RepoRepository
import tech.atlabs.githubchallenge.util.getMockRepos
import tech.atlabs.githubchallenge.util.getMockUsername

class GetReposUseCaseTest {

    private lateinit var getReposUseCase: GetReposUseCase
    private lateinit var repoRepository: RepoRepository

    @Before
    fun setUp() {
        repoRepository = mock()
        getReposUseCase = GetReposUseCase(repoRepository)
    }

    @Test
    fun `test that GetReposUseCase returns repos successfully`() = runTest {
        val username = getMockUsername()

        val mockRepos = getMockRepos()

        whenever(getReposUseCase.invoke(username)).thenReturn(Result.success(mockRepos))

        val result = getReposUseCase.invoke(username)

        // Assert that the use case returns the expected repo
        assertTrue(result.isSuccess)
        assertEquals(mockRepos, result.getOrNull())
    }

    @Test
    fun `test that GetReposUseCase returns failure for invalid username`() = runTest {
        val username = "vb wsj"
        val result = getReposUseCase.invoke(username)
        // Assert that the use case returns an error
        assertTrue(result.isFailure)
    }
}