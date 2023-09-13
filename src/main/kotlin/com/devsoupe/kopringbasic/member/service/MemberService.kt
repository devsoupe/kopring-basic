package com.devsoupe.kopringbasic.member.service

import com.devsoupe.kopringbasic.common.authority.JwtTokenProvider
import com.devsoupe.kopringbasic.common.authority.TokenInfo
import com.devsoupe.kopringbasic.common.exception.InvalidInputException
import com.devsoupe.kopringbasic.common.status.ROLE
import com.devsoupe.kopringbasic.member.dto.LoginDto
import com.devsoupe.kopringbasic.member.dto.MemberDtoRequest
import com.devsoupe.kopringbasic.member.entity.Member
import com.devsoupe.kopringbasic.member.entity.MemberRole
import com.devsoupe.kopringbasic.member.repository.MemberRepository
import com.devsoupe.kopringbasic.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
  private val memberRepository: MemberRepository,
  private val memberRoleRepository: MemberRoleRepository,
  private val authenticationManagerBuilder: AuthenticationManagerBuilder,
  private val jwtTokenProvider: JwtTokenProvider,
) {
  /**
   * 회원가입
   */
  fun signUp(memberDtoRequest: MemberDtoRequest): String {
    // ID 중복 검사
    var member: Member? = memberRepository.findByLoginId(loginId = memberDtoRequest.loginId)
    if (member != null) {
      throw InvalidInputException(fieldName = "loginId", message = "이미 등록된 ID 입니다.")
    }

    member = memberDtoRequest.toEntity()
    memberRepository.save(member)

    val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
    memberRoleRepository.save(memberRole)

    return "회원가입이 완료되었습니다."
  }

  /**
   * 로그인 -> 토큰 발행
   */
  fun login(loginDto: LoginDto): TokenInfo {
    val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.passowrd)
    val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

    return jwtTokenProvider.createToken(authentication)
  }
}