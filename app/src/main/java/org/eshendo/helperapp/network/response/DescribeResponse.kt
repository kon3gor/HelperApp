package org.eshendo.helperapp.network.response

import org.eshendo.helperapp.model.Caption

data class DescribeResponse(
    val tags: List<String>,
    val captions: List<Caption>
)