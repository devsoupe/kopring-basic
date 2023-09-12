package com.devsoupe.kopringbasic.member.repository

import com.devsoupe.kopringbasic.member.entity.Member
import com.devsoupe.kopringbasic.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
  fun findByLoginId(loginId: String): Member?
}

interface MemberRoleRepository: JpaRepository<MemberRole, Long>