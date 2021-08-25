package yoda.huddl.live.ui.main

import yoda.huddl.live.HuddleBaseRepository
import yoda.huddl.live.network.main.MainApiHelper
import javax.inject.Inject

class MainRepository : HuddleBaseRepository() {

    @Inject
    lateinit var mainApiHelper: MainApiHelper
}