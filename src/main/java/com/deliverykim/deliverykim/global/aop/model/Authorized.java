package com.deliverykim.deliverykim.global.aop.model;


import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Authorized {

    MemberRole value();

}
