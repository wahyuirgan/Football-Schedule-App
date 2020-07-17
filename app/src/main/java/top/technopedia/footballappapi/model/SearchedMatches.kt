package top.technopedia.footballappapi.model

import com.google.gson.annotations.SerializedName


data class SearchedMatches(
        @SerializedName("event") var events: List<Event>
)