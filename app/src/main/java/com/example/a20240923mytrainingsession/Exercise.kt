package com.example.a20240923mytrainingsession

import java.io.Serializable

class Exercise(
    val name: String,
    val description: String,
    val durationInSec: Int,
    val gifPict: Int,
    val type: String
) : Serializable