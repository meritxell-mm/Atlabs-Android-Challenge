package tech.atlabs.githubchallenge.util

import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.domain.model.User

fun getMockUserWithRepos(): User {
    val mockRepos = getMockRepos()
    return getMockUser().copy(repos = mockRepos)
}

fun getMockRepos(): List<Repo> { //TODO fer-ho bé
    val mockRepos = listOf(
        Repo(
            id = 132935648,
            name = "boysenberry-repo-1",
            fullName = "octocat/boysenberry-repo-1",
            private = false,
            htmlUrl = "https://github.com/octocat/boysenberry-repo-1",
            description = "Testing",
            isForked = true,
            language = null,
            stargazersCount = 317,
            forksCount = 19
        )
    )
    return mockRepos
}

fun getMockUsername() = "octocat"

fun getMockUser(): User {
    return User(
        login = "octocat",
        id = 1,
        avatarUrl = "https://github.com/images/error/octocat_happy.gif",
        htmlUrl = "https://github.com/octocat",
        name = "monalisa octocat",
        company = "GitHub",
        blog = "https://github.com/blog",
        location = "San Francisco",
        email = "octocat@github.com",
        bio = "There once was...",
        publicRepos = 2,
        publicGists = 1,
        followers = 20,
        following = 0,
        createdAt = "2008-01-14T04:33:35Z"
    )
}