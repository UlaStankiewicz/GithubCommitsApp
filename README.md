# GithubApp

## What is it?

It is a mobile application for the android platform, written in Kotlin.

It's main task is to provide information about the GitHub user repository and its commits.

## How to run?

The application does not require any complicated launch options, just build a project and start.

## What the app can do?

- It allows a user to input a GitHub repository name in the following format: “<owner>/<repository>“, e.g. "krzysztofserocki/GithubCommitsApp
- When the repository name is provided, the app fetches and displays the following information about it using GitHub API:
  - repository ID,
  - repository name,
  - repository owner,
  - the list of the commits in the repository:
    - each commit is described by: SHA value, message and author’s name,
    - the list is sorted by date so that the latest commits are at the top (just like on GitHub).
- The app caches the previously used repositories data.
  - The user can access the history of last used repositories.
    - Using the history, the user can open the repository information again in the same way as if he typed the repository name manually again (see: 1.)
- When the app is offline, the user should still be able to see the previously fetched repository information.


## Techonolgies and others
- Kotlin
- GithubAPI
- Architecture Components:
  - Room
  - ViewModel
  - DataBinding
  - LiveData
- Dagger2
- Retrofit2
- MaterialDesign
- MVVM Pattern
