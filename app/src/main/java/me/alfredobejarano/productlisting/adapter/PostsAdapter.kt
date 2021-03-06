package me.alfredobejarano.productlisting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import me.alfredobejarano.productlisting.PostsFragment
import me.alfredobejarano.productlisting.R
import me.alfredobejarano.productlisting.data.Post
import me.alfredobejarano.productlisting.utilities.fromHTML

/**
 *
 * [RecyclerView.Adapter] subclass that defines how a [Post] entity
 * is going to be rendered in a [RecyclerView].
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 10:49 PM
 * @version 1.0
 **/
class PostsAdapter(var elements: List<Post>?) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    /**
     * Creates a [ViewHolder] to be displayed in a [RecyclerView].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    /**
     * Returns how many elements are in the list.
     * @return The size of the elements property or zero if null.
     */
    override fun getItemCount() = elements?.size ?: 0

    /**
     * Renders data of an element at a given position into the [ViewHolder].
     * @param holder ViewHolder element being rendered.
     * @param position Position of the ViewHolder in the adapter.
     */
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        // Get the post from the elements.
        val post = elements?.get(position)
        // Display the post data in the view.
        holder.title.text = post?.title ?: ""
        holder.description.text = post?.body?.fromHTML() ?: ""
        holder.picture.setImageURI(post?.imageURL ?: "")
        // Enable a Click listener to the selected post.
        holder.itemView.setOnClickListener {
            // Check if the ViewHolder context implements the required interface.
            if (it.context is PostsFragment.OnPostClickedListener) {
                // If so, report which post was clicked.
                (it.context as PostsFragment.OnPostClickedListener)
                    .onPostClicked(post?.id ?: -1)
            }
        }
    }

    /**
     * Defines the layout id for an element in this adapter at a given position.
     * @param position The position of the [ViewHolder] being rendered.
     * @return Layout id for the item_product.xml file.
     */
    override fun getItemViewType(position: Int) = R.layout.item_product

    /**
     * Sets a new data set of posts to the adapter.
     * @param posts The new posts for the adapter.
     */
    fun setPosts(posts: List<Post>) {
        elements = posts
        notifyDataSetChanged()
    }

    /**
     * Simple [ViewHolder] class that represents
     * the visuals of a Post in kotlin code.
     */
    class PostViewHolder(itemView: View) : ViewHolder(itemView) {
        internal val title: TextView = itemView.findViewById(R.id.title)
        internal val picture: SimpleDraweeView = itemView.findViewById(R.id.picture)
        internal val description: TextView = itemView.findViewById(R.id.description)
    }
}