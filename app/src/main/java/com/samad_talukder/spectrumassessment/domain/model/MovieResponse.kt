package com.samad_talukder.spectrumassessment.domain.model

data class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
)