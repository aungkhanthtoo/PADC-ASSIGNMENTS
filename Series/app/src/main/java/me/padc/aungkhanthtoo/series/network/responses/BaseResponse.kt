package me.padc.aungkhanthtoo.series.network.responses

abstract class BaseResponse{
    protected var code: Int? = null
    protected var message: String? = null
    protected var apiVersion: String? = null
    var page: String? = null
}