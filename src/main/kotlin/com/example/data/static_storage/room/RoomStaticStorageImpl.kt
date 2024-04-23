package com.example.data.static_storage.room

class RoomStaticStorageImpl:RoomStaticStorage {

    private val photos = listOf(
        "https://www.funnyart.club/uploads/posts/2022-01/1642963227_56-www-funnyart-club-p-fon-komnati-krasivo-63.jpg",
        "https://destinationslinkhospitality.com/wp-content/uploads/2014/03/Taj-Palace-Bedroom.jpg",
        "https://img.goodfon.com/original/1280x960/4/fa/interer-stil-dizayn-dom-348.jpg",
        "https://img.goodfon.com/original/4368x2912/2/59/interer-stil-dizayn-4806.jpg",
        "https://www.shutterfly.com/ideas/wp-content/uploads/2017/11/bdrmred-50.jpg",
        "https://cdn.architecturendesign.net/wp-content/uploads/2014/07/House-in-Bryanston-271.jpg"

    )
    override fun fetchPhotos() = photos
}