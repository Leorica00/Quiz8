package com.example.quiz8.data.remote.mapper

import com.example.quiz8.data.remote.model.PlaceDto
import com.example.quiz8.domain.model.GetPlace

fun PlaceDto.toDomain() = GetPlace(
    id = id,
    cover = cover,
    price = price,
    title = title,
    location = location,
    reactionCount = reactionCount,
    rate = rate
)