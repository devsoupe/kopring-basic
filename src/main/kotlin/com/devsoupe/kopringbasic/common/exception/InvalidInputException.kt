package com.devsoupe.kopringbasic.common.exception

class InvalidInputException(
  val fieldName: String = "",
  message: String = "Invalid Input",
) : RuntimeException(message)