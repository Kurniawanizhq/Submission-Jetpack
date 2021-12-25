package com.eone.submission1

object FakeDataDummy {
    fun getDummyMovie(): List<ItemListResponse> =
        arrayListOf(
            ItemListResponse(
                634649,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                listOf(),
//                    Genre(28, ""),
//                    Genre(12, ""),
//                    Genre(878, "") ,
                "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                ""
            ),
            ItemListResponse(1, "", listOf(), "", "", "", ""),
            ItemListResponse(2, "", listOf(), "", "", "", ""),
            ItemListResponse(3, "", listOf(), "", "", "", ""),
            ItemListResponse(4, "", listOf(), "", "", "", ""),
            ItemListResponse(5, "", listOf(), "", "", "", ""),
            ItemListResponse(6, "", listOf(), "", "", "", ""),
            ItemListResponse(7, "", listOf(), "", "", "", ""),
            ItemListResponse(8, "", listOf(), "", "", "", ""),
            ItemListResponse(9, "", listOf(), "", "", "", ""),
            ItemListResponse(10, "", listOf(), "", "", "", ""),
            ItemListResponse(11, "", listOf(), "", "", "", ""),
            ItemListResponse(12, "", listOf(), "", "", "", ""),
            ItemListResponse(13, "", listOf(), "", "", "", ""),
            ItemListResponse(14, "", listOf(), "", "", "", ""),
            ItemListResponse(15, "", listOf(), "", "", "", ""),
            ItemListResponse(16, "", listOf(), "", "", "", ""),
            ItemListResponse(17, "", listOf(), "", "", "", ""),
            ItemListResponse(18, "", listOf(), "", "", "", ""),
            ItemListResponse(19, "", listOf(), "", "", "", "")
        )

    fun getDummyTvShow(): List<ItemListResponse> =
        arrayListOf(
            ItemListResponse(
                88329,
                "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                listOf(),
//                    Genre(10759, ""),
//                    Genre(18, "")
//                ),
                "/xA  KMj134XHQVNHLC6rWsccLMenG.jpg",
                "",
                "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
                "Hawkeye"
            ),
            ItemListResponse(1, "", listOf(), "", "", "", ""),
            ItemListResponse(2, "", listOf(), "", "", "", ""),
            ItemListResponse(3, "", listOf(), "", "", "", ""),
            ItemListResponse(4, "", listOf(), "", "", "", ""),
            ItemListResponse(5, "", listOf(), "", "", "", ""),
            ItemListResponse(6, "", listOf(), "", "", "", ""),
            ItemListResponse(7, "", listOf(), "", "", "", ""),
            ItemListResponse(8, "", listOf(), "", "", "", ""),
            ItemListResponse(9, "", listOf(), "", "", "", ""),
            ItemListResponse(10, "", listOf(), "", "", "", ""),
            ItemListResponse(11, "", listOf(), "", "", "", ""),
            ItemListResponse(12, "", listOf(), "", "", "", ""),
            ItemListResponse(13, "", listOf(), "", "", "", ""),
            ItemListResponse(14, "", listOf(), "", "", "", ""),
            ItemListResponse(15, "", listOf(), "", "", "", ""),
            ItemListResponse(16, "", listOf(), "", "", "", ""),
            ItemListResponse(17, "", listOf(), "", "", "", ""),
            ItemListResponse(18, "", listOf(), "", "", "", ""),
            ItemListResponse(19, "", listOf(), "", "", "", "")
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
            28,
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.5,
            listOf(0),
            "",
            ""
        )

    fun getDummyTvShowDetail(): ItemDetailResponse =
        ItemDetailResponse(
            "/iY6upFm468X37gqWPE7IEz3TRJx.jpg",
            "/7vjaCdMw15FEbXyLQTVa04URsPm.jpg",
            listOf(
                Genre(
                    10765,
                    "Sci-Fi & Fantasy"
                ),
                Genre(
                    18,
                    "Drama"
                ),
                Genre(
                    10759,
                    "Action & Adventure"
                )
            ),
            71912,
            "",
            0,
            "Geralt faces off with a demon targeting his nearest and dearest while the most powerful players on the Continent ramp up their pursuit of Ciri.",
            "",
            8.2,
            listOf(60),
            "2019-12-20",
            "The Witcher"
        )
}