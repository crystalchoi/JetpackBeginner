package com.crystal.codapizza.data

import androidx.annotation.StringRes
import com.crystal.codapizza.R

enum class Topping(
    @StringRes val toppingName: Int
){
    Basil(R.string.topping_basil),
    Mushroom(
        toppingName = R.string.topping_mushroom
    ),
    Olive(
        toppingName = R.string.topping_olive
    ),
    Peppers(
        toppingName = R.string.topping_peppers
    ),
    Pepperoni(
        toppingName = R.string.topping_pepperoni
    ),
    Pineapple(
        toppingName = R.string.topping_pineapple
    )
}


enum class DowSize(
    @StringRes val dowName: Int
){
    Large(R.string.dow_size_large),
    Medium(
        dowName = R.string.dow_size_medium
    ),
    Small(
        dowName = R.string.dow_size_small
    ),
    Family(
        dowName = R.string.dow_size_family
    ),
}