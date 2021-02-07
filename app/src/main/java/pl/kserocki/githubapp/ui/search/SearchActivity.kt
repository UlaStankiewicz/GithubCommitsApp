package pl.kserocki.githubapp.ui.search

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import pl.kserocki.githubapp.R
import pl.kserocki.githubapp.databinding.ActivitySearchBinding
import pl.kserocki.githubapp.db.entities.GithubRepo
import pl.kserocki.githubapp.factory.GithubViewModelFactory
import pl.kserocki.githubapp.models.MyResource
import pl.kserocki.githubapp.models.Status
import pl.kserocki.githubapp.ui.base.BaseActivity
import pl.kserocki.githubapp.ui.repo.RepoActivity
import pl.kserocki.githubapp.utils.*
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchContract.View,
    SearchAdapter.GithubRepoClickListener {

    @Inject
    lateinit var viewModelFactory: GithubViewModelFactory
    private lateinit var binding: ActivitySearchBinding
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }
    private val searchAdapter by lazy { SearchAdapter(this) }

    override fun initializeUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        KeyboardUtils.setupUI(searchLayout, this)

        repositoriesRecycler.adapter = searchAdapter
        repositoriesRecycler.layoutManager = LinearLayoutManager(this)
        viewModel.repoLiveData.observe(this, Observer {
            it?.let { value ->
                onNewRepositoryFound(value)
            }
        })
    }

    fun onSearchRepositoryButtonClick(view: View) {
        val ownerAndRepository = ownerAndRepositoryTxt.text.toString()
        if (ownerAndRepository.isEmpty() || !ownerAndRepository.contains("/")) {
            view.shakeAnimation()
            Toast.makeText(this, getString(R.string.wrong_format), Toast.LENGTH_SHORT).show()
            return
        }

        val stringSplit = ownerAndRepository.split("/")
        if (stringSplit.size != 2 || stringSplit[0].isEmpty() || stringSplit[1].isEmpty()) {
            view.shakeAnimation()
            Toast.makeText(this, getString(R.string.wrong_format), Toast.LENGTH_SHORT).show()
            return
        }

        view.clickAnimation(object : ClickCallback {
            override fun onClickFinish() {
                viewModel.ownerAndRepoLiveData.postValue(ownerAndRepository)
            }
        })
    }

    override fun onGithubRepositoryClick(view: View, githubRepo: GithubRepo) {
        view.clickAnimationReverse(object : ClickCallback {
            override fun onClickFinish() {
                viewModel.ownerAndRepoLiveData.postValue(githubRepo.owner.login + "/" + githubRepo.name)
            }
        })
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun goToRepoActivity(owner: String, repositoryName: String, repositoryId: Int) {
        val intent = Intent(this@SearchActivity, RepoActivity::class.java)
        intent.putExtra("repositoryOwner", owner)
        intent.putExtra("repositoryName", repositoryName)
        intent.putExtra("repositoryId", repositoryId)
        startActivity(intent)
        overridePendingTransition(
            R.anim.fragment_fade_enter,
            R.anim.fragment_fade_exit
        )
    }

    override fun onNewRepositoryFound(githubRepo: MyResource<GithubRepo>) {
        when (githubRepo.status) {
            Status.LOADING -> Unit
            Status.SUCCESS ->
                if (githubRepo.data != null) {
                    goToRepoActivity(
                        githubRepo.data.owner.login,
                        githubRepo.data.name,
                        githubRepo.data.id
                    )

                }

            is Status.ERROR -> Toast.makeText(
                this,
                getString(R.string.repository_not_found),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

}