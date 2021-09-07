package yoda.huddl.live.ui.splash

import app.yoda.huddl.huddlutils.HuddleBaseRepository
import yoda.huddl.live.network.splash.SplashApiHelper
import javax.inject.Inject

class SplashRepository @Inject constructor() : HuddleBaseRepository() {
    @Inject
    lateinit var splashApiHelper: SplashApiHelper
}