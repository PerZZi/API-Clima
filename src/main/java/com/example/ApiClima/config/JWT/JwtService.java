package com.example.ApiClima.config.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); //Devuelve un token  firmado con la clave proporcionada

    private static final long EXPIRATION_TOKEN = 1000 * 60 * 60;  //1000 milisegundos x 60 segundos x 60 minutos = 1 hora

    public Claims extractAllClaims(String token){ //Va a verificar un token utilizando una clave secreta, luego va a extraer y devolver las claims del token verificado, que seria el payload
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload(); // un objeto signed JWT que contenga las claims firmadas
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token); // aca tenemos todos los clains del token
        return claimsTFunction.apply(claims); // nos retorna mediante la claimsFunction un claim en particular que le pasemos como parametro
    }

    public String extractUserName(String token) { return extractClaim(token, Claims::getSubject);}

    public Date extractExpiration(String token){ return extractClaim(token, Claims::getExpiration); }

    public Boolean isTokenExpired(String token) { return extractExpiration(token).before(new Date()); } // si es true esta expirado si es false esta vigente

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder() // Inicia un objeto del tipo JWT Builder
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())) // fecha de emision del token
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN)) // fecha de expiracion
                .signWith(SECRET_KEY) // firmamos el token con la SECRET KEY
                .compact(); // con este metodo construimos el token JWT Completo y lo devolvemos como un string
    }

    public String generateToken(UserDetails userDetails){ //utiliza como parametro el Client que se declara en UserDetails
        Map<String, Object> claims = new HashMap<>();//la clave será de tipo string que será el "rol" y el valor será el rol que nosotros vamos a obtener del userDatails
        String rol = userDetails.getAuthorities().iterator().next().getAuthority();
        claims.put("rol", rol); // clave rol, valor rol
        return createToken(claims, userDetails.getUsername());
    }
}
