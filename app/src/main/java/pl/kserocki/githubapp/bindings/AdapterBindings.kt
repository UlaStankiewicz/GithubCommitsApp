package pl.kserocki.githubapp.bindings

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.kserocki.githubapp.R
import pl.kserocki.githubapp.db.entities.GithubCommit
import pl.kserocki.githubapp.db.entities.GithubRepo
import pl.kserocki.githubapp.models.MyResource
import pl.kserocki.githubapp.models.Status
import pl.kserocki.githubapp.ui.repo.RepoAdapter
import pl.kserocki.githubapp.ui.search.SearchAdapter


@BindingAdapter("adapterRepositories")
fun bindAdapterRepositories(view: RecyclerView, myResource: MyResource<List<GithubRepo>>?) {
    if (myResource != null) {
        when (myResource.status) {
            is Status.LOADING -> Unit
            is Status.SUCCESS -> {
                val adapter = view.adapter as? SearchAdapter
                adapter?.setGithubRepoList(myResource.data!!)
            }
            is Status.ERROR -> Toast.makeText(
                view.context,
                myResource.status.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@BindingAdapter("visibility")
fun bindTextView(view: TextView, myResource: MyResource<List<GithubCommit>>?) {
    if (myResource != null) {
        when (myResource.status) {
            is Status.LOADING -> {
                view.visibility = VISIBLE
                view.text = view.context.getString(R.string.loading)
            }
            is Status.SUCCESS -> {
                if (myResource.data.isNullOrEmpty()) {
                    view.visibility = VISIBLE
                    view.text = view.context.getString(R.string.empty_list)
                } else {
                    view.visibility = GONE
                }
            }
            is Status.ERROR -> {
                view.visibility = VISIBLE
                view.text = view.context.getString(R.string.error_occurred)
            }
        }
    }
}

@BindingAdapter("adapterCommits")
fun bindAdapterCommits(view: RecyclerView, myResource: MyResource<List<GithubCommit>>?) {
    if (myResource != null) {
        when (myResource.status) {
            is Status.LOADING -> Unit
            is Status.SUCCESS -> {
                val adapter = view.adapter as? RepoAdapter
                adapter?.setGithubCommitList(myResource.data!!)
            }
            is Status.ERROR -> Toast.makeText(
                view.context,
                myResource.status.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}