package good.damn.kamchatka.fragments.ui.blocks

import good.damn.kamchatka.R

class ZakaznikiFragment
    : BlockFragment() {
    override fun onMsgVulcan(): Int = R.string.zakazniki_msg

    override fun onTitle(): Int = R.string.zakazniki_title

    override fun onDesc(): Int = R.string.zakazniki_desc

    override fun onIcon(): Int = R.drawable.ic_mountain
}