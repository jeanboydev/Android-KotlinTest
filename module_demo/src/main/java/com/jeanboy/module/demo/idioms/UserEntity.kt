package com.jeanboy.module.demo.idioms

/**
 *
 * @author caojianbo
 * @since 2020/1/7 16:36
 */

data class UserEntity(val name: String, var age: Int)

// 默认会为 UserEntity 提供以下功能
// 所有属性的 getters （对于 var 定义的还有 setters）
// equals()
// hashCode()
// toString()
// copy()