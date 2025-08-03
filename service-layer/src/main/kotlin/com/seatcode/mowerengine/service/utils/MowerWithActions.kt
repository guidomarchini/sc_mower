package com.seatcode.mowerengine.service.utils

import com.seatcode.mowerengine.model.Mower
import com.seatcode.mowerengine.model.MowerAction

data class MowerWithActions(val mower: Mower, val actions: List<MowerAction>)