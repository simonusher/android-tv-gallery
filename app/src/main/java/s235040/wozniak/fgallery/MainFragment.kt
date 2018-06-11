
import android.os.Bundle
import android.support.v17.leanback.app.BrowseFragment
import android.support.v17.leanback.app.HeadersFragment
import android.support.v17.leanback.widget.ArrayObjectAdapter
import android.support.v17.leanback.widget.ListRow
import android.support.v17.leanback.widget.ListRowPresenter
import android.support.v17.leanback.widget.Presenter
import android.support.v17.leanback.widget.PresenterSelector
import android.support.v17.leanback.widget.Row
import android.support.v17.leanback.widget.RowHeaderPresenter
import android.support.v4.content.ContextCompat
import s235040.wozniak.fgallery.*
import java.util.ArrayList


class MainFragment : BrowseFragment() {

    private var picassoBackgroundManager: PicassoBackgroundManager? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        picassoBackgroundManager = PicassoBackgroundManager(activity)

        setupUIElements()

        loadRows()

        setupEventListeners()

    }

    private fun loadRows() {
        val list = ArrayList<Image>()
        list.add(Image(R.drawable.t1, R.drawable.g1))
        list.add(Image(R.drawable.t2, R.drawable.g2))
        list.add(Image(R.drawable.t3, R.drawable.g3))
        list.add(Image(R.drawable.t4, R.drawable.g4))
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()
        val gridRowAdapter = ArrayObjectAdapter(cardPresenter)
        var gridItemPresenterHeader: MyItemHeader
        for (i in list.indices) {
            gridItemPresenterHeader = MyItemHeader(i.toLong(), list[i])
            rowsAdapter.add(ListRow(gridItemPresenterHeader, gridRowAdapter))
        }

        adapter = rowsAdapter
    }


    private fun setupUIElements() {
        headersState = BrowseFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(activity, R.color.fastlane_background)

        setHeaderPresenterSelector(object : PresenterSelector() {
            override fun getPresenter(o: Any): Presenter {
                return MyRowHeaderPresenter()
            }
        })
    }


    private fun setupEventListeners() {
        headersFragment.setOnHeaderViewSelectedListener(HeaderViewSelectedListener())
    }


    private inner class HeaderViewSelectedListener : HeadersFragment.OnHeaderViewSelectedListener {
        override fun onHeaderSelected(viewHolder: RowHeaderPresenter.ViewHolder, row: Row) {
            val resId = (row.headerItem as MyItemHeader).image.imageId
            picassoBackgroundManager!!.updateBackgroundWithDelay(resId)
        }
    }

    companion object {
        private val TAG = "picTAG"
    }

}