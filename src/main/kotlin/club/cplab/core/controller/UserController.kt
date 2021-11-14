package club.cplab.core.controller

import club.cplab.core.dto.SignUpReq
import club.cplab.core.dto.SignUpRes
import club.cplab.core.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/v1")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    suspend fun signup(@RequestBody user: SignUpReq): SignUpRes {
        return userService.signup(user).let {
            println(it)
            SignUpRes(it.toString())
        }
    }
}