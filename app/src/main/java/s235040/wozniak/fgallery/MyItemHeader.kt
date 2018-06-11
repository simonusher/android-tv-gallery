package s235040.wozniak.fgallery

import android.support.v17.leanback.widget.HeaderItem
/**
 * Subclass of [HeaderItem] to hold icon resource id
 * to show icon on header with [IconHeaderItemPresenter]
 */
class MyItemHeader(id: Long, val image: Image) : HeaderItem(id, null)
