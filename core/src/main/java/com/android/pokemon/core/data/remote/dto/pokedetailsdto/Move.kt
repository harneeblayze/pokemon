package com.android.pokemon.core.data.remote.dto.pokedetailsdto

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)