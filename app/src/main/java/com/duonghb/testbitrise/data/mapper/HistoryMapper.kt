package com.duonghb.testbitrise.data.mapper

import com.duonghb.testbitrise.data.database.entity.History
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData

fun History.toModel() = HistoryModelData(
    title = title,
    section = section,
    url = url,
    imageUrl = imageUrl
)
