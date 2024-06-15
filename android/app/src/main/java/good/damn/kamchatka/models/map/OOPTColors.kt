package good.damn.kamchatka.models.map

import good.damn.kamchatka.models.Color

enum class OOPTColors(
    val color: Int
) {
    A(Color.parseFromHex(
        0xffB6E0ED.toInt(),
        0.6f
    )),
    B(Color.parseFromHex(
        0xff2D4D6D.toInt(),
        0.3f
    )),
    C(Color.parseFromHex(
        0xff808AFF.toInt(),
        0.6f
    )),
    D(Color.parseFromHex(
        0xff656B7B.toInt(),
        0.3f
    )),
    E(Color.parseFromHex(
        0xff16A1EF.toInt(),
        0.3f
    )),
    F(Color.parseFromHex(
        0xff9776A5.toInt(),
        0.3f
    ))
}