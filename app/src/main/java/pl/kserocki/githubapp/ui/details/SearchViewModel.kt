package pl.kserocki.githubapp.ui.details

import pl.kserocki.githubapp.api.repository.GithubRepoRepository
import javax.inject.Inject

class SearchViewModel @Inject
constructor(
    private val githubRepoRepository: GithubRepoRepository
) : SearchContract.ViewModel() {



}