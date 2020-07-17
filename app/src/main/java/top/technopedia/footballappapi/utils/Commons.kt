package top.technopedia.footballappapi.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import top.technopedia.footballappapi.R

internal fun loadImage(context: Context,
                       url: String,
                       imageView: ImageView
) {

    fun setMemoryCategory(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
    }

    setMemoryCategory(context)

    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_soccer)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imageView)
}