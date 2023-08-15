package com.techno.springbootdasar.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingConfigurationAdapter(
    private val crudMahasiswaInterceptor: CrudMahasiswaInterceptor,
    private val loginUserInterceptor: LoginUserInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(crudMahasiswaInterceptor).addPathPatterns("/v1/api/crud/user")
        registry.addInterceptor(crudMahasiswaInterceptor).addPathPatterns("/v1/api/crud/user/{uuid}")
        registry.addInterceptor(crudMahasiswaInterceptor).addPathPatterns("/v1/api/validate")
        registry.addInterceptor(loginUserInterceptor).addPathPatterns("/v1/api/login")
    }
}