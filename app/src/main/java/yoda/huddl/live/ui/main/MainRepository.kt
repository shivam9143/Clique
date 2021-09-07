package yoda.huddl.live.ui.main

import app.yoda.huddl.huddlutils.HuddleBaseRepository
import yoda.huddl.live.network.main.MainApiHelper
import javax.inject.Inject

class MainRepository : HuddleBaseRepository() {

    @Inject
    lateinit var mainApiHelper: MainApiHelper
}