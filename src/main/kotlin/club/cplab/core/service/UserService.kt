package club.cplab.core.service

import club.cplab.core.dto.SignUpReq
import club.cplab.core.dto.SignUpRes
// import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class UserService(
    // private val modelMapper: ModelMapper
) {
    suspend fun signup(user: SignUpReq): SignUpRes {
        return SignUpRes(
            token = "dummy"
        )
        // return modelMapper.map(token, SignUpRes::class.java)
    }
}