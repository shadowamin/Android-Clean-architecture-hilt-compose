package com.hannibalprojects.sampleproject.presentation.viewmodels

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Observer
import com.hannibalprojects.sampleproject.domain.User
import com.hannibalprojects.sampleproject.domain.usecases.GetUserUseCase
import com.hannibalprojects.sampleproject.domain.usecases.GetUsersUseCase
import com.hannibalprojects.sampleproject.domain.usecases.RefreshUsersUseCase
import com.hannibalprojects.sampleproject.presentation.UsersViewModel
import com.hannibalprojects.sampleproject.presentation.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
class UsersViewModelTest {

    @Mock
    lateinit var usersUseCase: GetUsersUseCase

    @Mock
    lateinit var userDetailsUseCase: GetUserUseCase

    @Mock
    lateinit var refreshUsersUseCase: RefreshUsersUseCase

    @InjectMocks
    lateinit var usersViewModel: UsersViewModel

    @Mock
    lateinit var refreshObserver: Observer<Boolean>

    @Mock
    lateinit var listUsersObserver: Observer<DataWrapper<List<User>>>

    @Mock
    lateinit var userObserver: Observer<DataWrapper<User>>

    private val dispatcher = TestCoroutineDispatcher()

    private val user = User(
        id = 3,
        email = "user@email.com",
        firstName = "firstName",
        lastName = "lastName",
        avatar = "avatar"
    )

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @Test
    fun `refreshUsers - when response - then pass value to observer`() = dispatcher.runBlockingTest {
        // Given
        given(refreshUsersUseCase.execute()).willReturn(true)
        usersViewModel.refreshUsersLiveData.observeForever(refreshObserver)

        // When
        usersViewModel.refreshUsers(true)

        // Then
        then(refreshUsersUseCase).should().execute()
        verify(refreshObserver).onChanged(true)
        usersViewModel.refreshUsersLiveData.removeObserver(refreshObserver)
    }

    @Test
    fun `loadUsers - when response - then post value to observer`() = dispatcher.runBlockingTest {
        // Given
            val listUsers = listOf(user)
        val usersFlow: Flow<List<User>> = flow {
            emit(listUsers)
        }
        given(usersUseCase.execute()).willReturn(usersFlow)
        usersViewModel.loadUsersLiveData.observeForever(listUsersObserver)

        // When
        usersViewModel.loadUsers()

        // Then
        then(usersUseCase).should().execute()
        verify(listUsersObserver).onChanged(Success(listUsers))
        usersViewModel.loadUsersLiveData.removeObserver(listUsersObserver)
    }

    @Test
    fun `loadUsers - when error - then post Failure`() = dispatcher.runBlockingTest {
        // Given
        val exception = RuntimeException()
        given(usersUseCase.execute()).willThrow(exception)
        usersViewModel.loadUsersLiveData.observeForever(listUsersObserver)

        // When
        usersViewModel.loadUsers()

        // Then
        then(usersUseCase).should().execute()
        verify(listUsersObserver).onChanged(Failure(RequestError(exception)))
        usersViewModel.loadUsersLiveData.removeObserver(listUsersObserver)
    }

    @Test
    fun `getUserDetails - when response - then pass value to observer`() = dispatcher.runBlockingTest {
        // Given
        given(userDetailsUseCase.execute(3)).willReturn(user)
        usersViewModel.loadUserLiveData.observeForever(userObserver)

        // When
        usersViewModel.getUserDetails(3)

        // Then
        then(userDetailsUseCase).should().execute(3)
        verify(userObserver).onChanged(Success(user))
        usersViewModel.loadUserLiveData.removeObserver(userObserver)
    }

    @Test
    fun `getUserDetails - when Exception - then post ErrorRequest`() = dispatcher.runBlockingTest {
        // Given
        val exception = RuntimeException()
        given(userDetailsUseCase.execute(3)).willThrow(exception)
        usersViewModel.loadUserLiveData.observeForever(userObserver)

        // When
        usersViewModel.getUserDetails(3)

        // Then
        then(userDetailsUseCase).should().execute(3)
        verify(userObserver).onChanged(Failure(RequestError(exception)))
        usersViewModel.loadUserLiveData.removeObserver(userObserver)
    }

}