package com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.model

class MyData {
    var headerTitle: String? = null
    var listItem: List<Item>? = null

    constructor() {

    }

    constructor(headerTitle: String, listItem: List<Item>) {
        this.headerTitle = headerTitle
        this.listItem = listItem
    }
}