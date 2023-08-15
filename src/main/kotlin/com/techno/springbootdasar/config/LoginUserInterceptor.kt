package com.techno.springbootdasar.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.service.impl.LoginServiceImpl
import com.techno.springbootdasar.util.JWTGenerator
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginUserInterceptor(
    private val loginServiceImpl: LoginServiceImpl
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey = request.getHeader("api-key")

        if (apiKey != "123-456-789") {
            val result = ResBaseDto(false, "Forbidden", null, 401, "Unauthorized")
            val jsonResponse = convertObjectToJson(result)

            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED

            response.writer.write(jsonResponse)
            return false
        }

        return true
    }


    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        super.postHandle(request, response, handler, modelAndView)
    }

    fun convertObjectToJson(resBaseDto: ResBaseDto<*>): String {
        return ObjectMapper().writeValueAsString(resBaseDto)
    }
}