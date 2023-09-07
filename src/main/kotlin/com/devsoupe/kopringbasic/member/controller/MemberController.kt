package com.devsoupe.kopringbasic.member.controller

import com.devsoupe.kopringbasic.member.dto.MemberDtoRequest
import com.devsoupe.kopringbasic.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
class MemberController(
  private val memberService: MemberService,
) {
  /**
   * 회원가입
   */
  @PostMapping("/signup")
  fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): String {
    return memberService.signUp(memberDtoRequest)
  }
}