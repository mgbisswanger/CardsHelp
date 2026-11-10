package com.bisswanger.cardshelp.data

import androidx.room3.ColumnTypeConverter
import kotlinx.serialization.json.Json

public class Converters {
    @ColumnTypeConverter
    fun cardListToString(cardList: List<Card>) : String {
        return Json.encodeToString(cardList)
    }

    @ColumnTypeConverter
    fun stringToCardList(jsonString: String): List<Card> {
        return Json.decodeFromString<List<Card>>(jsonString)
    }
}