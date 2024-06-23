package good.damn.kamchatka.fragments.ui.blocks

import good.damn.kamchatka.R

class MonumentsFragment
    : BlockFragment() {
    override fun onMsgVulcan(): Int = R.string.monuments_msg

    override fun onTitle(): Int = R.string.monuments_title

    override fun onDesc(): Int = R.string.monuments_desc

    override fun onIcon(): Int = R.drawable.ic_mountain
}