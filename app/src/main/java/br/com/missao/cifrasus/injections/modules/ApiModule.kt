package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.apis.RedditAPI
import br.com.missao.cifrasus.apis.RedditRest
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Application's API module
 */
@Module
class ApiModule {

    /**
     * Provides [RedditAPI] using [retrofit] as implementation
     */
    @Provides @Singleton fun providesRedditApi(retrofit: Retrofit): RedditAPI
            = RedditRest(retrofit)
}
