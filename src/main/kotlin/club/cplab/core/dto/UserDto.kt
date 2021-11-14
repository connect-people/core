package club.cplab.core.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SignUpReq(
    @JsonProperty("login_id")
    var loginId: String,
    var password: String,
    var name: String,
    var phone: String,
    @JsonProperty("is_auth")
    var isAuth: Int,
    var created: String
)

data class SignUpRes(
    var token: String? = null
)