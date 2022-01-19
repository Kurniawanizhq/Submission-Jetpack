package com.eone.submission3.utils

import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity

object FakeDataDummy {
    fun getDummyMovie(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                524434,
                "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
                "/tutaKitJJIaqZPyMz7rxrhb4Yxm.jpg",
                "Eternals",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "",
                "",
                "",
                0.0,
                false
            ),
            MovieEntity(1, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(2, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(3, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(4, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(5, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(6, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(7, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(8, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(9, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(10, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(11, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(12, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(13, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(14, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(15, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(16, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(17, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(18, "", "", "", "", "", "", "", 0.0, false),
            MovieEntity(19, "", "", "", "", "", "", "", 0.0, false)
        )
    }

    fun getDummyTvShow(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                85552,
                "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                "/oKt4J3TFjWirVwBqoHyIvv5IImd.jpg",
                "",
                "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                "Euphoria",
                "",
                "",
                0.0,
                false
            ),
            TvShowEntity(1, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(2, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(3, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(4, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(5, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(6, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(7, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(8, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(9, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(10, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(11, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(12, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(13, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(14, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(15, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(16, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(17, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(18, "", "", "", "", "", "", "", 0.0, false),
            TvShowEntity(19, "", "", "", "", "", "", "", 0.0, false))
    }

    fun getDummyMovieDetail(): MovieEntity =MovieEntity(
            524434,
            "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
            "/c6H7Z4u73ir3cIoCteuhJh7UCAR.jpg",
            "Eternals",
            "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
            "2021-11-03",
            "156",
            "Action, Adventure, Fantasy, Science, Fiction",
            7.2,
            false
        )


    fun getDummyTvShowDetail(): TvShowEntity = TvShowEntity(
        85552,
        "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
        "/oKt4J3TFjWirVwBqoHyIvv5IImd.jpg",
        "Euphoria",
        "",
        "As the semester kicks off, Jules questions Rue and Elliot's new friendship. While Cal hunts for answers, Nate makes a tough decision. The lines between fantasy and reality begin to blur as Kat ponders her relationship, and Maddy contemplates the decision to end hers.",
        "2019-06-16",
        "Drama",
        8.4,
        false
    )
}