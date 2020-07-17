package top.technopedia.footballappapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (

    @SerializedName("idLeague") var idLeague: String?,
    @SerializedName("strLeague") var strLeague: String?,
    @SerializedName("strDescriptionEN") var strDescriptionEN: String?,
    @SerializedName("strBadge") var strBadge: String?

): Parcelable