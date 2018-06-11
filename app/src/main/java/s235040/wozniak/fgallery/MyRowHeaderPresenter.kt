
import android.content.Context
import android.support.v17.leanback.widget.ImageCardView
import android.support.v17.leanback.widget.ListRow
import android.support.v17.leanback.widget.Presenter
import android.support.v17.leanback.widget.RowHeaderPresenter
import android.view.LayoutInflater
import android.view.ViewGroup
import s235040.wozniak.fgallery.MyItemHeader
import s235040.wozniak.fgallery.R


/**
 * Customized HeaderItem Presenter to show {@link IconHeaderItem}
 */

class MyRowHeaderPresenter : RowHeaderPresenter() {
    private var mUnselectedAlpha: Float = 0f

    override fun onCreateViewHolder(parent: ViewGroup): RowHeaderPresenter.ViewHolder {
        mUnselectedAlpha = parent.resources.getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1)
        val inflater =  parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.my_header_item, null)
        val viewHolder = ViewHolder(view)
        setSelectLevel(viewHolder, 0f)
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any?) {
        val iconHeaderItem = (item as ListRow).headerItem as MyItemHeader
        val rootView = viewHolder.view

        val imageView = rootView as ImageCardView
        imageView.setMainImageDimensions(300, 169)
        val image = rootView.resources.getDrawable(iconHeaderItem.image.thumbnailId, null)
        imageView.mainImage = image
    }

    override fun onSelectLevelChanged(holder: ViewHolder) {
        holder.view.alpha = mUnselectedAlpha + holder.selectLevel * (1.0f - mUnselectedAlpha)
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        viewHolder.view.alpha = mUnselectedAlpha
        super.onUnbindViewHolder(viewHolder)
    }

    companion object {
        val TAG = MyRowHeaderPresenter::class.java.simpleName
    }

}