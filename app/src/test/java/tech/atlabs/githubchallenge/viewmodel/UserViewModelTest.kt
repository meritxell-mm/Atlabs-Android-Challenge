package tech.atlabs.githubchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.domain.usecase.GetReposUseCase
import tech.atlabs.githubchallenge.domain.usecase.GetUserUseCase
import tech.atlabs.githubchallenge.domain.usecase.GetUserWithReposUseCase
import tech.atlabs.githubchallenge.ui.utils.ErrorHandler
import tech.atlabs.githubchallenge.ui.utils.UiState
import tech.atlabs.githubchallenge.util.getMockRepos
import tech.atlabs.githubchallenge.util.getMockUser
import tech.atlabs.githubchallenge.util.getMockUserWithRepos
import tech.atlabs.githubchallenge.util.getMockUsername

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // StateFlow synchronous updates

    @Mock
    lateinit var getUserUseCase: GetUserUseCase

    @Mock
    lateinit var getUserWithReposUseCase: GetUserWithReposUseCase

    @Mock
    lateinit var getReposUseCase: GetReposUseCase

    @Mock
    lateinit var errorHandler: ErrorHandler

    private lateinit var viewModel: UserViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        // Set the main dispatcher for tests to StandardTestDispatcher
        Dispatchers.setMain(testDispatcher)

        // Initialize the mock use cases and ViewModel
        getUserUseCase = mock()
        getUserWithReposUseCase = mock()
        getReposUseCase = mock()
        errorHandler = mock()

        // Create the ViewModel with all the required mocks
        viewModel = UserViewModel(
            getUserUseCase = getUserUseCase,
            getUserWithReposUseCase = getUserWithReposUseCase,
            getReposUseCase = getReposUseCase,
            errorHandler = errorHandler
        )
    }

    @Test
    fun `test that get user is loaded correctly`() = testScope.runTest {
        val mockUser = getMockUser()

        val username = getMockUsername()
        whenever(getUserUseCase.invoke(username)).thenReturn(Result.success(mockUser))

        // Act: Fetch the user details
        viewModel.getUser(username)

        // Act & Assert
        viewModel.userState.test {
            awaitItem()
            // Assert Loading
            assert(awaitItem() is UiState.Loading)

            val successState = awaitItem() // Assert Success & userMock
            assert(successState is UiState.Success)
            assertEquals(mockUser, (successState as UiState.Success).data)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test that user with repos are loaded correctly`() = testScope.runTest {
        val username = getMockUsername()
        val mockUser = getMockUserWithRepos()

        whenever(getUserWithReposUseCase.invoke(username)).thenReturn(Result.success(mockUser))

        // Act: Fetch the user with repos
        viewModel.getUserWithRepos(username)

        // Act & Assert
        viewModel.userState.test {
            awaitItem()
            // Assert Loading
            assert(awaitItem() is UiState.Loading)

            val successState = awaitItem() // Assert Success & userWithReposMock
            assert(successState is UiState.Success)
            assertEquals(mockUser, (successState as UiState.Success).data)

            cancelAndIgnoreRemainingEvents()
        }
    }
}