package top.technopedia.footballappapi.model

import com.google.gson.annotations.SerializedName

class Leagues (
    @SerializedName("leagues")
    var leagues: List<League>)