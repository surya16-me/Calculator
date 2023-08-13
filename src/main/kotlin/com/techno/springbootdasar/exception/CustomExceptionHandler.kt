package com.techno.springbootdasar.exception

import java.lang.RuntimeException

class CustomExceptionHandler(message: String) : RuntimeException(message) {

}