package yoda.huddl.live.ui.splash

import yoda.huddl.live.HuddleBaseRepository
import yoda.huddl.live.network.splash.SplashApiHelper
import javax.inject.Inject

class SplashRepository @Inject constructor() : HuddleBaseRepository() {
    @Inject
    lateinit var splashApiHelper: SplashApiHelper
}