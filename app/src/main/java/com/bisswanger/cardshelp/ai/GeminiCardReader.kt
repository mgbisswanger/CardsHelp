package com.bisswanger.cardshelp.ai

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content

// sends a photo to gemini and gets back the cards in it
object GeminiCardReader {
    // the gemini model
    private val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-3.5-flash-lite")

    // photoBytes: the raw contents of a .jpg
    suspend fun readCards(photoBytes: ByteArray): List<String> {
        val response = model.generateContent(
            content {
                // the photo
                inlineData(photoBytes, "image/jpeg")
                // exact instructions so the reply is always in the label format
                text(
                    "List every playing card in this image, left to right, " +
                    "as rank + suit letter separated by commas, like: AS, 10H, KD. " +
                    "Ranks: 2-10, J, Q, K, A. Suits: C, D, H, S. Reply with only the list."
                )
            }
        )

        return response.text.orEmpty().split(",")
            .map{ label -> label.trim().uppercase() }
            .filter { label -> label.isNotEmpty() }
    }
}
