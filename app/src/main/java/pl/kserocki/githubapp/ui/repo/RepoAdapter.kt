package pl.kserocki.githubapp.ui.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kserocki.githubapp.R
import pl.kserocki.githubapp.db.entities.GithubCommit

class RepoAdapter(private val githubCommitClickListener: GithubCommitClickListener) :
    RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {

    private var githubCommitList: ArrayList<GithubCommit> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_commit_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return githubCommitList.size
    }

    fun setGithubCommitList(list: List<GithubCommit>) {
        githubCommitList.clear()
        githubCommitList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val githubCommit = githubCommitList[position]

        holder.commitShaValue.text = githubCommit.sha
        holder.commitMessage.text = githubCommit.commit.message
        holder.repositoryOwner.text = githubCommit.author.login

        holder.itemView.setOnClickListener {
            githubCommitClickListener.onGithubCommitClick(it, githubCommit)
        }

    }

    interface GithubCommitClickListener {
        fun onGithubCommitClick(view: View, githubCommit: GithubCommit)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repositoryOwner: TextView = view.findViewById(R.id.repositoryOwner)
        val commitShaValue: TextView = view.findViewById(R.id.commitShaValue)
        val commitMessage: TextView = view.findViewById(R.id.commitMessage)
    }

}