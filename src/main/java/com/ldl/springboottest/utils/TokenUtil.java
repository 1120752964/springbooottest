package com.ldl.springboottest.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ldl.springboottest.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class TokenUtil {
    /*
     * 生成token
     * */
    public String generateToken(User user){
        Date start  = new Date() ;
        long currentTime = System.currentTimeMillis() + 60*60*1000 ; //一小时的有效时间
        Date end = new Date(currentTime) ;
        String token ="";
                token = JWT.create()
                .withAudience(user.getUnumber())
                .withAudience(user.getUname())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256("qweasd123"));
        return token ;
    }

    /*
     * 获取指定token中某个属性值
     * */
    public static String get(String token , String key){
        List<String> list = JWT.decode(token).getAudience() ;
        String userId = list.get(0) ;
        return userId ;
    }
    /*
     * 获取request
     * */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest() ;
    }

    /*
     * 获取token
     * */
    public String getToken(HttpServletRequest request)  {
        Cookie[] cookies = request.getCookies() ;
        for(Cookie c : cookies){
            if(c.getName() == "token"){
                return c.getValue() ;
            }
        }
        return null ;
    }

}