package com.example.myutil.data.local.model

import androidx.annotation.StringRes
import com.example.myutil.R


enum class GenderType(@StringRes val stringRes: Int) {
    UNKNOWN(R.string.s_select), MALE(R.string.n_man), FEMALE(R.string.a_woman)
}