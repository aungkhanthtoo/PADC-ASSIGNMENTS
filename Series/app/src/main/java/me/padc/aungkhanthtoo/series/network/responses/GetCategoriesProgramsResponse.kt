package me.padc.aungkhanthtoo.series.network.responses

import me.padc.aungkhanthtoo.series.data.vo.CategoryVO

data class GetCategoriesProgramsResponse(
        val categoriesPrograms: List<CategoryVO>
) : BaseResponse()