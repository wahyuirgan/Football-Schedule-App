package top.technopedia.footballappapi.model

import com.google.gson.annotations.SerializedName

data class FootballMatch (
        @SerializedName("events") var events: List<Event>
)


