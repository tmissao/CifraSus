package br.com.missao.cifrasus.adapter.delegates

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.activities.SongActivity
import br.com.missao.cifrasus.extensions.inflate
import br.com.missao.cifrasus.interfaces.ViewType
import br.com.missao.cifrasus.interfaces.ViewTypeDelegateAdapter
import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper
import kotlinx.android.synthetic.main.item_song.view.*

/**
 * Delegate Song Adapter for [SongInfoWrapper]
 */
class SongsDelegateAdapter : ViewTypeDelegateAdapter {

  override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    holder as TurnsViewHolder
    holder.bind(item as SongInfoWrapper)
  }

  /**
   * Class to implement [RecyclerView.ViewHolder]
   */
  class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
      parent.inflate(R.layout.item_song)
  ) {
    fun bind(item: SongInfoWrapper) = with(itemView) {
      this.textSongName.text = item.name
      this.textSongArtist.text = item.artist
      this.llSongItemContainer.setOnClickListener {
        context.startActivity(SongActivity.getStartIntent(context, item.id))
      }
    }
  }
}