package com.eone.submission3.utils

import com.eone.submission3.data.response.Genre
import com.eone.submission3.data.response.ItemDetailResponse
import com.eone.submission3.data.response.ItemListResponse

object FakeDataDummy {
    fun getDummyMovie(): List<ItemListResponse> =
        arrayListOf(
            ItemListResponse(
                634649,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg", "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                ""
            ),
            ItemListResponse(1, "", "", "", "", ""),
            ItemListResponse(2, "", "", "", "", ""),
            ItemListResponse(3, "", "", "", "", ""),
            ItemListResponse(4, "", "", "", "", ""),
            ItemListResponse(5, "", "", "", "", ""),
            ItemListResponse(6, "", "", "", "", ""),
            ItemListResponse(7, "", "", "", "", ""),
            ItemListResponse(8, "", "", "", "", ""),
            ItemListResponse(9, "", "", "", "", ""),
            ItemListResponse(10, "", "", "", "", ""),
            ItemListResponse(11, "", "", "", "", ""),
            ItemListResponse(12, "", "", "", "", ""),
            ItemListResponse(13, "", "", "", "", ""),
            ItemListResponse(14, "", "", "", "", ""),
            ItemListResponse(15, "", "", "", "", ""),
            ItemListResponse(16, "", "", "", "", ""),
            ItemListResponse(17, "", "", "", "", ""),
            ItemListResponse(18, "", "", "", "", ""),
            ItemListResponse(19, "", "", "", "", "")
        )

    fun getDummyTvShow(): List<ItemListResponse> =
        arrayListOf(
            ItemListResponse(
                88329,
                "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                "/xA  KMj134XHQVNHLC6rWsccLMenG.jpg",
                "",
                "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
                "Hawkeye"
            ),
            ItemListResponse(1, "", "", "", "", ""),
            ItemListResponse(2, "", "", "", "", ""),
            ItemListResponse(3, "", "", "", "", ""),
            ItemListResponse(4, "", "", "", "", ""),
            ItemListResponse(5, "", "", "", "", ""),
            ItemListResponse(6, "", "", "", "", ""),
            ItemListResponse(7, "", "", "", "", ""),
            ItemListResponse(8, "", "", "", "", ""),
            ItemListResponse(9, "", "", "", "", ""),
            ItemListResponse(10, "", "", "", "", ""),
            ItemListResponse(11, "", "", "", "", ""),
            ItemListResponse(12, "", "", "", "", ""),
            ItemListResponse(13, "", "", "", "", ""),
            ItemListResponse(14, "", "", "", "", ""),
            ItemListResponse(15, "", "", "", "", ""),
            ItemListResponse(16, "", "", "", "", ""),
            ItemListResponse(17, "", "", "", "", ""),
            ItemListResponse(18, "", "", "", "", ""),
            ItemListResponse(19, "", "", "", "", "")
        )

    fun getDummyMovieDetail(): ItemDetailResponse =
        ItemDetailResponse(
            "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            listOf(
                Genre(
                    28,
                    "Action"
                ),
                Genre(
                    12,
                    "Adventure"
                ),
                Genre(
                    878,
                    "Science Fiction"
                )
            ),
            634649,
            "Spider-Man: No Way Home",
            148,
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.5,
            listOf(0),
            "",
            ""
        )

    fun getDummyTvShowDetail(): ItemDetailResponse =
        ItemDetailResponse(
            "/9QNv2Al3GfCND8BwuLmu2GwVht7.jpg",
            "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
            listOf(
                Genre(
                    10759,
                    "Action & Adventure"
                ),
                Genre(
                    18,
                    "Drama"
                )
            ),
            88329,
            "",
            0,
            "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
            "",
            8.5,
            listOf(50),
            "2021-11-24",
            "Hawkeye"
        )
}