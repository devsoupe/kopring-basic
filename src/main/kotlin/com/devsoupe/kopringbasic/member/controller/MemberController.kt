package com.devsoupe.kopringbasic.member.controller

import com.devsoupe.kopringbasic.common.authority.TokenInfo
import com.devsoupe.kopringbasic.common.dto.BaseResponse
import com.devsoupe.kopringbasic.common.dto.CustomUser
import com.devsoupe.kopringbasic.member.dto.LoginDto
import com.devsoupe.kopringbasic.member.dto.MemberDtoRequest
import com.devsoupe.kopringbasic.member.dto.MemberDtoResponse
import com.devsoupe.kopringbasic.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/member")
@RestController
class MemberController(
  private val memberService: MemberService,
) {
  /**
   * 회원가입
   */
  @PostMapping("/signup")
  fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
    val resultMsg = memberService.signUp(memberDtoRequest = memberDtoRequest)
    return BaseResponse(message = resultMsg)
  }

  /**
   * 로그인
   */
  @PostMapping("/login")
  fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
    val tokenInfo = memberService.login(loginDto)
    return BaseResponse(data = tokenInfo)
  }

  /**
   * 내 정보 보기
   */
  @GetMapping("/info")
  fun searchMyInfo(): BaseResponse<MemberDtoResponse> {
    val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
    val response = memberService.searchMyInfo(userId)
    return BaseResponse(data = response)
  }
}