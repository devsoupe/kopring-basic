package com.devsoupe.kopringbasic.common.dto

import com.devsoupe.kopringbasic.common.status.ResultCode

data class BaseResponse<T>(
  val resultCode: String = ResultCode.SUCCESS.name,
  val data: T? = null,
  val message: String = ResultCode.SUCCESS.msg,
)
