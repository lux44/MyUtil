package com.example.myutil.utils

import android.net.Uri
import android.os.Bundle
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import timber.log.Timber

object DeepLinkUtils {

        /*** 공용url을 안드에서 사용할수 있는 값으로 변경.
         *  - 팬투운영클럽과 유저운영클럽이 처리되는 부분이 나눠져 있기 때문에 내부에서 진입로 변경
         **/
//        fun convertDeepLinkForInApp(deepLink:String?):String?{
//            if(deepLink.isNullOrEmpty())return deepLink
//            var link = deepLink
//            val type = Uri.parse(link).getQueryParameter(KEY_PARAM_TYPE)
//            val part = Uri.parse(link).getQueryParameter(KEY_PARAM_PART)
//            if(PARAM_VALUE_TYPE_POST == type || PARAM_VALUE_TYPE_REPLY == type){
//                if((PARAM_VALUE_PART_COMMUNITY == part)){
//                    link += INAPP_PARAM_POST_COMMUNITY
//                }else if(PARAM_VALUE_PART_COMMUNITY_NOTICE == part){
//                    link += INAPP_PARAM_POST_COMMUNITY_NOTICE
//                }else if(PARAM_VALUE_PART_CLUB == part){
//                    val clubId = Uri.parse(link).getQueryParameter(KEY_PARAM_CLUB_ID)
//                    link += if(clubId == CLUB_TEXT_FANTOO_TV || clubId == ConstVariable.ClubDef.CLUB_ID_FANTOO_TV.toString()){
//                        INAPP_PARAM_CLUB_NAME_FANTOO_TV
//                    }else if(clubId == CLUB_TEXT_HANRYU_TIMES || clubId == ConstVariable.ClubDef.CLUB_ID_HANRYU_TIMES.toString()) {
//                        INAPP_PARAM_CLUB_NAME_HANRYU_TIMES
//                    }else if(clubId == CLUB_TEXT_FANSING || clubId == ConstVariable.ClubDef.CLUB_ID_FANSING.toString()){
//                        INAPP_PARAM_CLUB_NAME_FANSING
//                    }else{
//                        INAPP_PARAM_CLUB_NAME_OTHERS
//                    }
//                }
//            }else if(PARAM_VALUE_TYPE_CLUB == type || (PARAM_VALUE_TYPE_BOARD == type && PARAM_VALUE_TYPE_CLUB == part)){
//                val clubId = Uri.parse(link).getQueryParameter(KEY_PARAM_CLUB_ID)
//                if(clubId == CLUB_TEXT_FANTOO_TV || clubId == ConstVariable.ClubDef.CLUB_ID_FANTOO_TV.toString()){
//                    link += INAPP_PARAM_CLUB_NAME_FANTOO_TV
//                }else if(clubId == CLUB_TEXT_HANRYU_TIMES || clubId == ConstVariable.ClubDef.CLUB_ID_HANRYU_TIMES.toString()) {
//                    link += INAPP_PARAM_CLUB_NAME_HANRYU_TIMES
//                }else if(clubId == CLUB_TEXT_FANSING || clubId == ConstVariable.ClubDef.CLUB_ID_FANSING.toString()){
//                    link += INAPP_PARAM_CLUB_NAME_FANSING
//                }else{
//                    link += INAPP_PARAM_CLUB_NAME_OTHERS
//                }
//            }
//            return link
//        }
//
//        fun hasWebLink(deepLink: String?):Boolean{
//            if(deepLink.isNullOrEmpty())return false
//            val type = Uri.parse(deepLink).getQueryParameter(KEY_PARAM_TYPE)
//            if(type == PARAM_VALUE_TYPE_WEB)return true
//            return false
//        }
//
//        fun moveOtherApp(deepLink: String?):Boolean{
//            if(deepLink.isNullOrEmpty()) return false
//            val type = Uri.parse(deepLink).getQueryParameter(KEY_PARAM_TYPE)
//            return type == PARAM_VALUE_TYPE_APP
//        }
//        fun hasFanitReward(deepLink: String?):Boolean{
//            if(deepLink.isNullOrEmpty()) return false
//            val type = Uri.parse(deepLink).getQueryParameter(KEY_PARAM_TYPE)
//            return type == PARAM_VALUE_TYPE_FANIT_REWARD
//        }
//
//        fun isRequireLoginLink(link: String?):Boolean{
//            if(link.isNullOrEmpty())return false
//            val type = Uri.parse(link).getQueryParameter(KEY_PARAM_TYPE)
//            val part = Uri.parse(link).getQueryParameter(KEY_PARAM_PART)
//            if ((type == PARAM_VALUE_TYPE_CLUB ||
//                        type == PARAM_VALUE_TYPE_REQ_FANIT ||
//                        type == PARAM_VALUE_TYPE_CLUB_JOIN_MANAGE ||
//                        type == PARAM_VALUE_TYPE_MENU ||
//                        type == PARAM_VALUE_TYPE_OFFERWALL ||
//                        part == PARAM_VALUE_TYPE_CLUB
//                        )
//                && !isFantooOwnClub(link)
//            ) {
//                return true
//            }else if(type == PARAM_VALUE_TYPE_WEB){
//                val requireLogin = Uri.parse(link).getQueryParameter("login")
//                requireLogin?.let {
//                    if(it == "1")return true
//                }
//            }
//            return false
//        }
//        fun isGAinLink(link: String?):Boolean{
//            if(link.isNullOrEmpty())return false
//            val type = Uri.parse(link).getQueryParameter(KEY_PARAM_TYPE)
//            return type == PARAM_VALUE_TYPE_GA
//        }
//
//        fun isMinuteinLink(link: String?):Boolean{
//            if(link.isNullOrEmpty())return false
//            val type = Uri.parse(link).getQueryParameter(KEY_PARAM_TYPE)
//            return type == PARAM_VALUE_TYPE_MINUTE
//        }
//
//        fun isRequestFanitLink(link: String?):Boolean{
//            if(link.isNullOrEmpty())return false
//            val type = Uri.parse(link).getQueryParameter(KEY_PARAM_TYPE)
//            return type == PARAM_VALUE_TYPE_REQ_FANIT
//        }
//
//        fun isFantooOwnClub(link:String?):Boolean{
//            if(link.isNullOrEmpty())return false
//            val clubId = Uri.parse(link).getQueryParameter(KEY_PARAM_CLUB_ID)
//            clubId?.let{
//                if(clubId == CLUB_TEXT_FANTOO_TV || clubId == CLUB_TEXT_HANRYU_TIMES || clubId == CLUB_TEXT_FANSING){
//                    return true
//                }
//            }
//            return false
//        }
//
//        fun isOfferWallLink(link: String?):Boolean{
//            if(link.isNullOrEmpty())return false
//            val type = Uri.parse(link).getQueryParameter(KEY_PARAM_TYPE)
//            return type == PARAM_VALUE_TYPE_OFFERWALL
//        }
//
//        fun getClubBoardDeepLink(clubId: String, boardCategoryCode:String): Uri?{
//            try {
//                val clubName =
//                    if(clubId == CLUB_ID_TEXT_FANTOO_TV ||
//                        clubId == CLUB_ID_TEXT_HANRYU_TIMES ||
//                        clubId == CLUB_ID_TEXT_FANSING) clubId
//                    else{
//                        when (clubId.toInt()) {
//                            ConstVariable.ClubDef.CLUB_ID_FANTOO_TV -> {
//                                CLUB_TEXT_FANTOO_TV
//                            }
//
//                            ConstVariable.ClubDef.CLUB_ID_HANRYU_TIMES -> {
//                                CLUB_TEXT_HANRYU_TIMES
//                            }
//
//                            ConstVariable.ClubDef.CLUB_ID_FANSING -> {
//                                CLUB_TEXT_FANSING
//                            }
//
//                            else -> {
//                                CLUB_TEXT_OTHERS
//                            }
//                        }
//                    }
//                return "fantoo://link?type=board&clubId=${clubId}&clubName=${clubName}&categoryCode=${boardCategoryCode}".toUri()
//            }catch (e:Exception){
//                Timber.e("${e.printStackTrace()}")
//            }
//            return null
//        }
//
//        fun getClubDeepLinkUri(clubId:String): Uri?{
//            try{
//                val clubName = if(clubId == CLUB_ID_TEXT_FANTOO_TV ||
//                    clubId == CLUB_ID_TEXT_HANRYU_TIMES ||
//                    clubId == CLUB_ID_TEXT_FANSING) clubId
//                else {
//                    when (clubId.toInt()) {
//                        ConstVariable.ClubDef.CLUB_ID_FANTOO_TV -> {
//                            CLUB_TEXT_FANTOO_TV
//                        }
//
//                        ConstVariable.ClubDef.CLUB_ID_HANRYU_TIMES -> {
//                            CLUB_TEXT_HANRYU_TIMES
//                        }
//
//                        ConstVariable.ClubDef.CLUB_ID_FANSING -> {
//                            CLUB_TEXT_FANSING
//                        }
//
//                        else -> {
//                            CLUB_TEXT_OTHERS
//                        }
//                    }
//                }
//                return "fantoo://link?type=club&clubId=${clubId}&clubName=${clubName}".toUri()
//            }catch (e:Exception){
//                Timber.e("${e.printStackTrace()}")
//            }
//            return null
//        }
//
//        fun getClubPostReplyDeepLinkUri(categoryCode:String, clubId:String, postId:Int,replyId:Int): Uri?{
//            try{
//                return "fantoo://link?type=post&part=club&categoryCode=${categoryCode}&clubId=${clubId}&postId=${postId}&replyId=${replyId}".toUri()
//            }catch (e:Exception){
//                Timber.e("${e.printStackTrace()}")
//            }
//            return null
//        }
//
//        fun getClubPostDeepLinkUri(categoryCode:String, clubId:String, postId:Int): Uri?{
//            try{
//                return "fantoo://link?type=post&part=club&categoryCode=${categoryCode}&clubId=${clubId}&postId=${postId}".toUri()
//            }catch (e:Exception){
//                Timber.e("${e.printStackTrace()}")
//            }
//            return null
//        }
//
//        fun getChatRoomDeepLinkUri(chatId:Int,voiceTalk:String?, entryType:String): Uri?{
//            try {
//                return "fantoo://link?type=chat&chatId=${chatId}&voiceTalk=${voiceTalk}&entryType=${entryType}".toUri()
//            }catch (e:Exception){
//                Timber.e("${e.printStackTrace()}")
//            }
//            return null
//        }
//
//        fun getCommunityCommentDeepLinkUri(postId:Int,boardCode:String,postType:String,replyId:Int): Uri?{
//            try {
//
//                return "fantoo://link?type=post&part=community&code=${boardCode}&postId=${postId}&postType=${postType}&replyId=${replyId}".toUri()
//            }catch (e:Exception){
//                Timber.e("${e.printStackTrace()}")
//            }
//            return null
//        }
//
//        fun getCommunityPostDeepLinkUri(postType: String, boardCode:String, postId:Int): Uri?{
//            try {
//                return "fantoo://link?type=post&part=community&code=${boardCode}&postId=${postId}&postType=${postType}".toUri()
//            }catch (e:Exception){
//                Timber.e("${e.printStackTrace()}")
//            }
//            return null
//        }
//
//        fun getMinuteDeepLinkUri(minuteId:Int): Uri?{
//            try{
//                return "fantoo://link?type=minute&idx=${minuteId}".toUri()
//            }catch (e:Exception){
//
//            }
//            return null
//        }
//
//        fun getDestinationIdOfDynamicLink(argument: Bundle, uriString:String?):Int{
//            var destinationId= -1
//            if(uriString.isNullOrEmpty())return destinationId
//            val uri = Uri.parse(uriString)
//            var notifyLinkData = NotifyLinkData(
//                type = uri.getQueryParameter("type"),
//                part = uri.getQueryParameter("part"),
//                code = uri.getQueryParameter("code"),
//                categoryCode = uri.getQueryParameter("categoryCode"),
//                clubId = uri.getQueryParameter("clubId"),
//                postId = uri.getQueryParameter("postId"),
//                replyId = uri.getQueryParameter("replyId"),
//                tab = uri.getQueryParameter("tab"),
//                url = uri.getQueryParameter("url"),
//                idx = uri.getQueryParameter("idx"),
//                view = uri.getQueryParameter("view")
//            )
//
//            if(uri.toString().contains("chat")) {
//                var url = uri.toString()
//                val chatId: String
//                url = url.removeRange(0,19)
//                chatId = url.replace("/enter", "")
//                notifyLinkData = NotifyLinkData(
//                    type = "CHAT",
//                    part = uri.getQueryParameter("part"),
//                    code = uri.getQueryParameter("code"),
//                    categoryCode = uri.getQueryParameter("categoryCode"),
//                    clubId = uri.getQueryParameter("clubId"),
//                    postId = uri.getQueryParameter("postId"),
//                    replyId = uri.getQueryParameter("replyId"),
//                    tab = uri.getQueryParameter("tab"),
//                    url = uri.getQueryParameter("url"),
//                    idx = chatId,
//                    view = uri.getQueryParameter("view")
//                )
//            }
//
//            when (notifyLinkData.type) {
//                ConstVariable.Notification.PUSH_SERVICE_TYPE_CHAT -> {
//                    destinationId = R.id.chattingFragment
//                    notifyLinkData.idx?.let {
//                        if (it.contains("/start") || it.contains("/end")) {
//                            argument.putInt("chatId", (it.replace("/start", "").replace("/end", "")).toInt())
//                            if (it.contains("/start")) {
//                                argument.putString("voiceTalk", "voice")
//                            }
//                        } else {
//                            argument.putInt("chatId", it.toInt())
//                        }
//                    }
//                }
//                ConstVariable.Notification.TYPE_HOME -> {
//                    notifyLinkData.tab?.let { tabIndex ->
//                        when (tabIndex) {
//                            "0" -> {
//                                destinationId = R.id.home
//                            }
//                            "1" -> {
//                                destinationId = R.id.community
//                            }
//                            "2" -> {
//                                destinationId = R.id.club
//                            }
//                            "3" -> {
//                                destinationId = R.id.chatting
//                            }
//                            "4" -> {
//                                destinationId = R.id.minute
//                            }
//                            "5" -> {
//                                destinationId = R.id.menu
//                            }
//                        }
//                    }
//                }
//                ConstVariable.Notification.TYPE_CLUB -> {
//                    destinationId = R.id.clubDetailPageFragment
//                    argument.putString("clubId", notifyLinkData.clubId)
//                }
//                ConstVariable.Notification.TYPE_POST -> {
//                    when (notifyLinkData.part) {
//                        ConstVariable.Notification.PART_CLUB -> {
//                            destinationId = R.id.club_post
//                            argument.putString("clubId", notifyLinkData.clubId)
//                            argument.putInt("postId", (notifyLinkData.postId?.toInt() ?: 0))
//                            argument.putString("categoryCode", notifyLinkData.categoryCode)
//                            argument.putString("previewType", ConstVariable.TYPE_CLUB)
//                        }
//                        ConstVariable.Notification.PART_COMMUNITY -> {
//                            destinationId = R.id.detailPost
//                            argument.putString("postType", ConstVariable.TYPE_COMMUNITY)
//                            argument.putString("boardCode", notifyLinkData.code)
//                            argument.putInt("postId", (notifyLinkData.postId?.toInt() ?: 0))
//                        }
//                        ConstVariable.Notification.PART_COMMUNITY_NOTICE ->{
//                            destinationId = R.id.detailPost
//                            argument.putString("postType", ConstVariable.TYPE_COMMUNITY_NOTICE)
//                            argument.putString("boardCode", notifyLinkData.code)
//                            argument.putInt("postId", (notifyLinkData.postId?.toInt() ?: 0))
//                        }
//                        else -> {
//                        }
//                    }
//                }
//                ConstVariable.Notification.TYPE_REPLY ->{
//                    when (notifyLinkData.part) {
//                        ConstVariable.Notification.PART_CLUB -> {
//                            destinationId = R.id.clubCommentFragment2
//                            argument.putInt("postId", (notifyLinkData.postId?.toInt() ?: 0))
//                            argument.putInt("replyId", (notifyLinkData.replyId?.toInt() ?: 0))
//                            argument.putString("clubId", notifyLinkData.clubId)
//                            argument.putString("categoryCode", notifyLinkData.categoryCode)
//                        }
//                        ConstVariable.Notification.PART_COMMUNITY -> {
//                            destinationId = R.id.commentFragment
//                            argument.putInt("postId", (notifyLinkData.postId?.toInt() ?: 0))
//                            argument.putInt("replyId", (notifyLinkData.replyId?.toInt() ?: 0))
//                        }
//                        else -> {
//
//                        }
//                    }
//                }
//                ConstVariable.Notification.TYPE_BOARD ->{
//                    when (notifyLinkData.part) {
//                        ConstVariable.Notification.PART_CLUB -> {
//                            destinationId = R.id.clubDetailPageFragment
//                            argument.putString("clubId", notifyLinkData.clubId)
//                            argument.putString("clubCategoryCode", notifyLinkData.categoryCode)
//                        }
//                        ConstVariable.Notification.PART_COMMUNITY -> {
//                            destinationId = R.id.communityboard
//                            argument.putBundle("boardInfo", bundleOf("boardInfo" to BoardInfo(boardName = "", boardId = notifyLinkData.categoryCode, boardType = ConstVariable.BOARD_COMMON_TYPE)))
//                        }
//                        else -> {
//                        }
//                    }
//                }
//                ConstVariable.Notification.TYPE_CLUB_JOIN_MANAGE ->{
//                    destinationId = R.id.clubJoinWaitListFragment
//                    argument.putString("clubId", notifyLinkData.clubId)
//                }
//                ConstVariable.Notification.TYPE_WEB ->{
//                    notifyLinkData.url?.let {url ->
//                        argument.putString("link", url)
//                        when(notifyLinkData.view){
//                            "in" ->{
//                                destinationId = R.id.homeBannerWebFragment
//                            }
//                            "out" ->{//out ex. fantoo://link?type=web&url=https://www.hanryutimes.com&view=out
//                                destinationId = ConstVariable.Notification.DESTNATION_ID_WEB_OUT
//                            }
//                        }
//                    }
//                }
//                ConstVariable.Notification.TYPE_APP ->{
//                    notifyLinkData.url?.let {url ->
//                        argument.putString("link", url)
//                        destinationId = ConstVariable.Notification.DESTNATION_ID_WEB_OUT
//                    }
//                }
//                ConstVariable.Notification.TYPE_MINUTE ->{
//                    destinationId = R.id.minute
//                    argument.putInt("idx", notifyLinkData.idx?.toInt()?:0)
//                }
//                else -> {
//
//                }
//            }
//            return destinationId
//        }
//
//        fun getChatUri(uriString:String?): Uri?{
//            if(uriString.isNullOrEmpty())return null
//            var ret: Uri? = null
//            val uri = Uri.parse(uriString)
//            if(uri.toString().contains("chat")) {
//                var url = uri.toString()
//                val chatId: String
//                url = url.removeRange(0,19)
//                chatId = url.replace("/enter", "")
//                val notifyLinkData = NotifyLinkData(
//                    type = "CHAT",
//                    part = uri.getQueryParameter("part"),
//                    code = uri.getQueryParameter("code"),
//                    categoryCode = uri.getQueryParameter("categoryCode"),
//                    clubId = uri.getQueryParameter("clubId"),
//                    postId = uri.getQueryParameter("postId"),
//                    replyId = uri.getQueryParameter("replyId"),
//                    tab = uri.getQueryParameter("tab"),
//                    url = uri.getQueryParameter("url"),
//                    idx = chatId,
//                    view = uri.getQueryParameter("view")
//                )
//
//                notifyLinkData.idx?.let {
//                    var chatId = -1
//                    var voiceTalk:String? = null
//                    if (it.contains("/start") || it.contains("/end")) {
//                        chatId = (it.replace("/start", "").replace("/end", "")).toInt()
//                        if (it.contains("/start")) {
//                            voiceTalk = "voice"
//                        }
//                    } else {
//                        chatId = it.toInt()
//                    }
//                    ret = getChatRoomDeepLinkUri(chatId, voiceTalk, ChattingFragment.ENTRY_TYPE_NORMAL)
//                }
//            }
//            return ret
//        }
//
//        private const val KEY_PARAM_TYPE = "type"
//        private const val KEY_PARAM_PART = "part"
//
//        private const val KEY_PARAM_CLUB_ID = "clubId"
//        const val KEY_PARAM_URL = "url"
//
//        private const val PARAM_VALUE_TYPE_POST = "post"
//        private const val PARAM_VALUE_TYPE_REPLY = "reply"
//        private const val PARAM_VALUE_TYPE_CLUB = "club"
//        private const val PARAM_VALUE_TYPE_BOARD = "board"
//        private const val PARAM_VALUE_TYPE_CLUB_JOIN_MANAGE = "club_join_manage"
//        private const val PARAM_VALUE_TYPE_GA = "ga"
//        private const val PARAM_VALUE_TYPE_MINUTE = "minute"
//        private const val PARAM_VALUE_TYPE_WEB = "web"
//        private const val PARAM_VALUE_TYPE_APP = "app"
//        private const val PARAM_VALUE_TYPE_FANIT_REWARD = "showFanitRewardAd"
//        private const val PARAM_VALUE_TYPE_MENU = "menu"
//        private const val PARAM_VALUE_TYPE_REQ_FANIT = "requestFanit"
//        private const val PARAM_VALUE_TYPE_OFFERWALL ="showOfferwall"
//
//        private const val PARAM_VALUE_PART_CLUB = "club"
//        private const val PARAM_VALUE_PART_COMMUNITY = "community"
//        private const val PARAM_VALUE_PART_COMMUNITY_NOTICE = "communityNotice"
//
//        const val PARAM_WEB_VIEW_OUT = "view=out"
//
//        const val CLUB_TEXT_FANTOO_TV = "fantoo_tv"
//        const val CLUB_TEXT_HANRYU_TIMES = "hanryu_times"
//        const val CLUB_TEXT_FANSING = "fansing"
//        const val CLUB_TEXT_OTHERS = "others"
//        const val KEY_PARAM_VALUE = "value"
//        const val KEY_PARAM_KEY = "key"
//        const val KEY_PARAM_TKN = "tkn"
//
//        const val INAPP_PARAM_POST_COMMUNITY = "&postType=community"
//        const val INAPP_PARAM_POST_COMMUNITY_NOTICE = "&postType=COMMUNITYNOTICE"
//        const val INAPP_PARAM_CLUB_NAME_FANTOO_TV = "&clubName=fantoo_tv"
//        const val INAPP_PARAM_CLUB_NAME_HANRYU_TIMES = "&clubName=hanryu_times"
//        const val INAPP_PARAM_CLUB_NAME_FANSING = "&clubName=fansing"
//        const val INAPP_PARAM_CLUB_NAME_OTHERS = "&clubName=others"
//
//        const val DEEPLINK_URL_FANTOO_PREFIX = "fantoo://links"

}