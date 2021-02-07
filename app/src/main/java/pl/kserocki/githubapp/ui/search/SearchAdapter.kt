package pl.kserocki.githubapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kserocki.githubapp.R
import pl.kserocki.githubapp.db.entities.GithubRepo

class SearchAdapter(private val githubRepoClickListener: GithubRepoClickListener) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private var githubRepoList: ArrayList<GithubRepo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_repository_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return githubRepoList.size
    }

    fun setGithubRepoList(list: List<GithubRepo>) {
        githubRepoList.clear()
        githubRepoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val githubRepo = githubRepoList[position]

        holder.repositoryName.text = githubRepo.name
        holder.repositoryOwner.text = githubRepo.owner.login

        holder.itemView.setOnClickListener {
            githubRepoClickListener.onGithubRepositoryClick(it, githubRepo)
        }

    }

    interface GithubRepoClickListener {
        fun onGithubRepositoryClick(view: View, githubRepo: GithubRepo)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repositoryOwner: TextView = view.findViewById(R.id.repositoryOwner)
        val repositoryName: TextView = view.findViewById(R.id.repositoryName)
    }

}