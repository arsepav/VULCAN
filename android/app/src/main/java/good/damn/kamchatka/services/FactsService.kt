package good.damn.kamchatka.services

import android.content.res.Resources
import good.damn.kamchatka.R
import kotlin.random.Random

class FactsService(
    resources: Resources
) {
    private val mFacts = resources.getStringArray(
        R.array.facts
    )

    fun getFact(
        index: Int
    ): String {
        return mFacts[index]
    }

    fun getRandomFact(): String {
        return mFacts[
            Random.nextInt(
                mFacts.size
            )
        ]
    }

}