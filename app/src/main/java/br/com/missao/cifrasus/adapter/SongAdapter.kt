package br.com.missao.cifrasus.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.missao.cifrasus.adapter.delegates.SongsDelegateAdapter
import br.com.missao.cifrasus.constants.AdapterConstants
import br.com.missao.cifrasus.interfaces.ViewType
import br.com.missao.cifrasus.interfaces.ViewTypeDelegateAdapter

/**
 * Recycler View adapter to display Song info
 */
class SongAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var items: ArrayList<ViewType> = ArrayList()
  private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

  init {
    delegateAdapters.put(AdapterConstants.SONGS, SongsDelegateAdapter())
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    return delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return delegateAdapters.get(viewType).onCreateViewHolder(parent)
  }

  override fun getItemViewType(position: Int): Int {
    return items.get(position).getViewType()
  }

  override fun getItemCount(): Int {
    return items.size
  }

  /**
   * Adds the items the adapter
   */
  fun add(news: List<ViewType>) {
    items.clear()
    items.addAll(news)
    notifyDataSetChanged()
  }

  /**
   * Obtains number of elements in adapter
   */
  fun getItemsSize() = items.size
}