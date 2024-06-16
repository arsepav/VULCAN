package good.damn.kamchatka.fragments.ui.blocks

import good.damn.kamchatka.R

class LikeFragment
: BlockFragment() {

    override fun onMsgVulcan(): Int = R.string.like_msg

    override fun onTitle(): Int = R.string.title_like

    override fun onDesc(): Int = R.string.desc_like

    override fun onIcon(): Int = R.drawable.ic_like_pink
}