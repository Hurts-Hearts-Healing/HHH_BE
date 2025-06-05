package com.dsm.hhh.internal.data.repository.emotion.analysis

import com.dsm.hhh.internal.core.domain.model.dto.emotion.analysis.EmotionAnalysisInternalDTO
import com.dsm.hhh.internal.data.repository.emotion.analysis.mapper.EmotionAnalysisEntityMapper
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

    override fun findById(diaryId: String): Mono<EmotionAnalysisInternalDTO> {
        return emotionAnalysisMongoRepository.findById(diaryId)
            .map(EmotionAnalysisEntityMapper::toDto)
    }
}