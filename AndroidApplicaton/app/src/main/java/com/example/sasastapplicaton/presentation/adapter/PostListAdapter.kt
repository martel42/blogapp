import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.sasastapplicaton.R
import com.example.sasastapplicaton.data.db.entity.Post
import com.example.sasastapplicaton.databinding.ItemPostBinding
import com.example.sasastapplicaton.presentation.adapter.PostDiff
import com.example.sasastapplicaton.presentation.adapter.PostListDiff
import com.example.sasastapplicaton.presentation.adapter.PostViewHolder

class PostListAdapter: ListAdapter<Post,PostViewHolder>(PostDiff()) {
    var onPostClickListener: ((Post) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layout = R.layout.item_post
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        val binding = holder.binding

        with(binding) {
            with(post) {
                postTitle.text = post.title
                postContent.text= post.content
            }
        }

        binding.root.setOnClickListener {
            onPostClickListener?.invoke(post)
        }
    }

}