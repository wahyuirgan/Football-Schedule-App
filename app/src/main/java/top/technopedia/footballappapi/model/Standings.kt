package top.technopedia.footballappapi.model

import com.google.gson.annotations.SerializedName

class Standings (@SerializedName("table")
                 var standings: List<Standing>)