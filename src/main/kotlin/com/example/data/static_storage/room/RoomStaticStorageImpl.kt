package com.example.data.static_storage.room

class RoomStaticStorageImpl:RoomStaticStorage {

    private val photos = listOf(
        "https://handyfloor.ru/wp-content/uploads/2019/04/Nautical-Bedrooms-Master-Bedroom-Wall-Color-Calming-e1554972671685.jpg",
        "https://pro-dachnikov.com/uploads/posts/2023-01/1672794023_pro-dachnikov-com-p-chistaya-komnata-foto-1.jpg",
        "https://i.pinimg.com/originals/33/5b/f1/335bf1b89b47295c722922fd0d8a5e51.jpg",
        "https://media.cntraveler.com/photos/53dac5956dec627b14a01b09/16:9/w_2560%2Cc_limit/taj-palace-hotel-marrakech-morocco-4.jpg",
        "https://lee-price-photography.com/wp-content/uploads/2019/07/bed-rooms-with-blue-color-best-colors-for-bedrooms-for-bedroom-regarding-sizing-1280-x-720.jpg",
        "https://mykaleidoscope.ru/uploads/posts/2020-02/1581964853_41-p-fotografii-spalen-v-sovremennom-stile-91.jpg"

    )
    override fun fetchPhotos() = photos
}