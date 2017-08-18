package br.com.missao.cifrasus.mappers

import br.com.missao.cifrasus.model.dtos.RedditNewsDataResponse
import br.com.missao.cifrasus.model.wrappers.RedditNewsWrapper

/**
 * Parse methods for class [RedditNewsWrapper]
 */
class RedditNewsMapper {

    /**
     * Parses [dto] to [RedditNewsWrapper]
     */
    fun toWrapper(dto: RedditNewsDataResponse): RedditNewsWrapper
            = RedditNewsWrapper(dto.author, dto.title, dto.num_comments, dto.thumbnail, dto.url)

}