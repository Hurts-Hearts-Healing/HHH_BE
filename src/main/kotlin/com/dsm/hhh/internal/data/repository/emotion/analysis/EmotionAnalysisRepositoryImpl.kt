package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.external.web.rest.emotion.analysis.mapper.EmotionAnalysisMapper
import com.dsm.hhh.external.web.rest.emotion.analysis.response.EmotionAnalysisResponse
import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.core.domain.model.primitive.diary.DiaryId
import com.dsm.hhh.internal.core.domain.model.primitive.emotion.Emotion
import com.dsm.hhh.internal.core.domain.model.primitive.user.UserId
import com.dsm.hhh.internal.data.repository.emotion.analysis.mapper.EmotionAnalysisEntityMapper
import com.dsm.hhh.internal.data.repository.emotion.diary.mapper.EmotionDiaryEntityMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class EmotionAnalysisRepositoryImpl(
    private val emotionAnalysisMongoRepository: EmotionAnalysisMongoRepository,
) : EmotionAnalysisRepository {
    override fun save(emotionAnalysisInternalDTO: EmotionAnalysisInternalDTO): Mono<Void> {
        val entity = EmotionAnalysisEntityMapper.toEntity(emotionAnalysisInternalDTO)

        return emotionAnalysisMongoRepository.save(entity).then()
    }

    override fun findByUserIdAndDiaryId(userId: String, diaryId: String): Mono<EmotionAnalysisInternalDTO> {
        return emotionAnalysisMongoRepository.findByUserIdAndDiaryId(userId, diaryId)
            .map(EmotionAnalysisEntityMapper::toDto)
    }
}