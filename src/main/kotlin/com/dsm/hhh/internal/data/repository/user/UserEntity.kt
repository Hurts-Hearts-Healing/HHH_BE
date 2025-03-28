package com.dsm.hhh.internal.data.repository.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDateTime

/**
 * UserEntity - Spring Data MongoDB에 의해 영속화되는 사용자 정보 도메인 데이터 클래스
 * <p>
 *
 * @property id MongoDB에서 자동 생성되는 고유 식별자. 형식은 ObjectId (Insert 시 null 허용)
 * @property email 사용자 이메일 주소 (unique 인덱스 적용)
 * @property password 해시 처리된 비밀번호
 * @property name 사용자 실명
 * @property birthday 사용자 생년월일
 * @property breakupDate 마지막 이별일
 * @property emotionStatus 사용자 가입일의 감정 상태 (단위: %).
 * @property registeredAt 사용자 가입일
 *
 * <p>
 *
 * @author Kim Seung Won
 * @since 2025-03-22
 * @version 1.0
 */
@Document(collection = "user-collection") // TODO: 현재 하드 코딩이지만, 추후 개선 예정
class UserEntity private constructor(
    @Id
    val id: String?,

    val name: String,
    @Indexed(unique = true)
    val email: String,
    val password: String,

    val birthday: String, // TODO: String으로 저장한 건지 Date 형식으로 저장할 건지 고민.

    val breakupDate: String,
    val emotionStatus: Int, // TODO: 사용자에게 입력 받은 현재의 감정 상태를 어떻게 저장하고 갱신할지 고민.

    @Field(targetType = FieldType.DATE_TIME)
    val registeredAt: LocalDateTime = LocalDateTime.now()
) {

    constructor(
        name: String,
        email: String,
        password: String,
        birthday: String,
        breakupDate: String,
        emotionStatus: Int
    ) : this(null, name, email, password, birthday, breakupDate, emotionStatus)

}