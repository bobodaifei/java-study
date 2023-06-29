package qw;

import java.util.Base64;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JavaJWTUtil {
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withClaim("as", "a")
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(token);
            System.out.println(jwt.getHeader());
            System.out.println(jwt.getPayload());
            System.out.println(jwt.getSignature());
            byte[] header = Base64.getDecoder().decode(jwt.getHeader());
            byte[] Payload = java.util.Base64.getDecoder().decode(jwt.getPayload());
            System.out.println(new String(header));
            System.out.println(new String(Payload));
            System.out.println(jwt.getToken());
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        String zhangsan = JavaJWTUtil.sign("zhangsan", "123");
        JavaJWTUtil.verify(zhangsan, "zhangsan", "123");
    }
}