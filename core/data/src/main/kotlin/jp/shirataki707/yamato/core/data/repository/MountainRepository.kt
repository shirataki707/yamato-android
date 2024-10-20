package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.core.data.R
import jp.shirataki707.yamato.core.data.model.Mountain

object MountainRepository {
    val mountains = listOf(
        Mountain(
            nameRes = R.string.mountain_name_001_rishiri,
            descriptionRes = R.string.mountain_description_001_rishiri,
            imageRes = R.drawable.mountain_001_rishiri
        ),
        Mountain(
            nameRes = R.string.mountain_name_002_rausu,
            descriptionRes = R.string.mountain_description_002_rausu,
            imageRes = R.drawable.mountain_002_rausu
        ),
        Mountain(
            nameRes = R.string.mountain_name_003_shari,
            descriptionRes = R.string.mountain_description_003_shari,
            imageRes = R.drawable.mountain_003_syari
        ),
        Mountain(
            nameRes = R.string.mountain_name_004_akan,
            descriptionRes = R.string.mountain_description_004_akan,
            imageRes = R.drawable.mountain_004_akan
        ),
        Mountain(
            nameRes = R.string.mountain_name_005_taisetsu,
            descriptionRes = R.string.mountain_description_005_taisetsu,
            imageRes = R.drawable.mountain_005_taisetsu
        ),
        Mountain(
            nameRes = R.string.mountain_name_006_tomuraushi,
            descriptionRes = R.string.mountain_description_006_tomuraushi,
            imageRes = R.drawable.mountain_006_tomuraushi
        ),
        Mountain(
            nameRes = R.string.mountain_name_007_tokachi,
            descriptionRes = R.string.mountain_description_007_tokachi,
            imageRes = R.drawable.mountain_007_tokachi
        ),
        Mountain(
            nameRes = R.string.mountain_name_008_poroshiri,
            descriptionRes = R.string.mountain_description_008_poroshiri,
            imageRes = R.drawable.mountain_008_poroshiri
        ),
        Mountain(
            nameRes = R.string.mountain_name_009_yotei,
            descriptionRes = R.string.mountain_description_009_yotei,
            imageRes = R.drawable.mountain_009_yotei
        ),
    )
}