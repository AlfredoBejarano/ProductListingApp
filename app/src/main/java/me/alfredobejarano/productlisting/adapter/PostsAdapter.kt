package me.alfredobejarano.productlisting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.alfredobejarano.productlisting.R
import me.alfredobejarano.productlisting.data.Post

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
        val post = elements?.get(position)
        holder.slug.text = post?.slug ?: ""
        holder.description.text = post?.body ?: ""
    }

    /**
     * Defines the layout id for an element in this adapter at a given position.
     * @param position The position of the [ViewHolder] being rendered.
     * @return Layout id for the item_product.xml file.
     */
    override fun getItemViewType(position: Int) = R.layout.item_product

    /**
     * Simple [ViewHolder] class that represents
     * the visuals of a Post in kotlin code.
     */
    class PostViewHolder(itemView: View) : ViewHolder(itemView) {
        internal val slug: TextView = itemView.findViewById(R.id.slug)
        internal val description: TextView = itemView.findViewById(R.id.description)
    }
}