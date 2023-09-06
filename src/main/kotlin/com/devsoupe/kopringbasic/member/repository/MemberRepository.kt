package com.devsoupe.kopringbasic.member.repository

import com.devsoupe.kopringbasic.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
  fun findByLoginId(loginId: String): Member?
}