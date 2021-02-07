package pl.kserocki.githubapp.ui.repo

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_repo.*
import pl.kserocki.githubapp.R
import pl.kserocki.githubapp.databinding.ActivityRepoBinding
import pl.kserocki.githubapp.db.entities.GithubCommit
import pl.kserocki.githubapp.factory.GithubViewModelFactory
import pl.kserocki.githubapp.ui.base.BaseActivity
import pl.kserocki.githubapp.utils.ClickCallback
import pl.kserocki.githubapp.utils.clickAnimation
import pl.kserocki.githubapp.utils.clickAnimationReverse
import javax.inject.Inject

class RepoActivity : BaseActivity(), RepoContract.View, RepoAdapter.GithubCommitClickListener {

    @Inject
    lateinit var viewModelFactory: GithubViewModelFactory
    private lateinit var binding: ActivityRepoBinding
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RepoViewModel::class.java)
    }
    private val commitAdapter by lazy { RepoAdapter(this) }

    override fun initializeUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        commitsRecycler.adapter = commitAdapter
        commitsRecycler.layoutManager = LinearLayoutManager(this)

        respositoryName.text = getRepositoryName()
        ownerName.text = getRepositoryOwner()
        repositoryId.text = getRepositoryId().toString()

        viewModel.setOwnerAndRepositoryName(getRepositoryOwner() + "/" + getRepositoryName())
    }

    fun onRepoBackButtonClick(view: View) {
        view.clickAnimation(object : ClickCallback {
            override fun onClickFinish() {
                this@RepoActivity.onBackPressed()
            }
        })
    }

    override fun onGithubCommitClick(view: View, githubCommit: GithubCommit) {
        view.clickAnimationReverse(object : ClickCallback {
            override fun onClickFinish() {

            }
        })
    }

    override fun getRepositoryOwner(): String {
        return intent.getStringExtra("repositoryOwner") ?: ""
    }

    override fun getRepositoryName(): String {
        return intent.getStringExtra("repositoryName") ?: ""
    }

    override fun getRepositoryId(): Int {
        return intent.getIntExtra("repositoryId", 0)
    }

}