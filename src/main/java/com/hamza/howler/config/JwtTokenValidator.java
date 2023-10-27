package com.hamza.howler.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHead=request.getHeader(JwtConstant.JWT_HEADER);
        if(authHead!=null){
            String jwt=authHead.substring(7);

        try{
            SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
            Claims claims;
            if(Jwts.parserBuilder().setSigningKey(key).build().isSigned(jwt)){
                claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            }else{
                claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(jwt).getBody();
            }
            String email=String.valueOf(claims.get("email"));
            String authorities=String.valueOf(claims.get("authorities"));
            List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
            Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,auths);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            throw new BadCredentialsException(e.getMessage());
        }
        }
        filterChain.doFilter(request,response);
    }
}
