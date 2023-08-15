package com.techno.springbootdasar.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.util.JWTGenerator
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CrudMahasiswaInterceptor: HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey = request.getHeader("api-key")
        val token = request.getHeader("authToken")
        if (apiKey != "123-456-789" || token == null) {
            val result = ResBaseDto(false, "Forbidden", null, 401, "Unauthorized")
            val jsonResponse = convertObjectToJson(result)

            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED

            response.writer.write(jsonResponse)
            return false
        }
        val isValidToken = validateAndDecodeJWT(token)
        if (!isValidToken) {
            val result = ResBaseDto(false, "Invalid JWT token", null, 401, "Unauthorized")
            val jsonResponse = convertObjectToJson(result)

            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED

            response.writer.write(jsonResponse)
            return false
        }

        return true
    }

    fun validateAndDecodeJWT(token: String): Boolean {
        try {

            // Use your JWT library to validate and decode the token
            val decodedToken = JWTGenerator().decodeJWT(token)

            // Check expiration time
            val expirationTime = decodedToken.expiration.time
            val currentTime = System.currentTimeMillis()
            return expirationTime > currentTime

            // You can also perform additional checks here if needed

        } catch (e: Exception) {
            return false
        }
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