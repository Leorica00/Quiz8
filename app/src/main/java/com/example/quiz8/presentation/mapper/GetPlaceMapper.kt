package com.example.quiz8.presentation.mapper

import com.example.quiz8.domain.model.GetPlace
import com.example.quiz8.presentation.model.Place

fun GetPlace.toPresentation() = Place(
    id = id,
    cover = cover,
    price = price,
    title = title,
    location = location,
    reactionCount = reactionCount,
    rate = rate
)