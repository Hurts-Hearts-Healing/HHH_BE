package com.dsm.hhh.external.error

enum class ErrorCode(
    val errorCode: String,
    val message: String
) {

    AUTH_001("AUTH_001", "비밀번호가 일치하지 않습니다."),
    AUTH_002("AUTH_002", "가입되지 않은 이메일입니다."),
    AUTH_003("AUTH_003", "사용자 정보를 처리하는 중 문제가 발생했습니다. 관리자에게 문의해주세요."),
    AUTH_004("AUTH_004", "이미 가입된 이메일입니다. 다른 이메일을 사용해주세요."),
    AUTH_005("AUTH_005", "현재 유저의 인증 정보를 불러오지 못했습니다."),
    AUTH_006("AUTH_006", "유효하지 않은 사용자 정보입니다."),
    AUTH_007("AUTH_007", "이메일 인증이 완료되지 않은 사용자입니다."),

    DIARY_001("DIARY_001", "이미 오늘의 일기가 존재합니다."),

    EMOTION_001("EMOTION_001", "이미 오늘의 감정을 선택했습니다."),

    ANALYSIS_001("ANALYSIS_001", "분석에 실패하였습니다."),

    INTERNAL_001("INTERNAL_001", "데이터베이스 연결에 일시적인 문제가 발생했습니다. 잠시 후 다시 시도해주세요."),
    INTERNAL_002("INTERNAL_002", "요청 처리 중 예상치 못한 오류가 발생했습니다. 문제가 지속될 경우 개발팀에 문의해주십시오."),

}