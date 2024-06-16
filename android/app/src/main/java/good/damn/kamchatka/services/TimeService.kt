package good.damn.kamchatka.services

class TimeService {
    companion object {
        fun currentTimeSec(): Long {
            return System.currentTimeMillis() / 1000
        }

        fun checkExpiration(
            time: Long,
            period: Long
        ): Boolean {
            return currentTimeSec() - time > period
        }
    }

}