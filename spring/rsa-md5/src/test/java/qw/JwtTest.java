package qw;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.junit.Test;

import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;

public class JwtTest {
	
//"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTcwODg4MzMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGl6aSJ9.97y-pJCgAbyJhvukyQHwbQnjD0HGMGJYUH6gWmqAsCM"
	@Test
	public void test1() {
		int a [] = {4,5,6};
		int a1 [] = new int[10];
		a1[0]=10;
		a1[1]=900;
//    	header.payload.secret
		HashMap<String, Object> map = new HashMap<>();
		map.put("alg", "HMAC256");
		map.put("typ", "JWT");

		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND, 600);

		Builder builder = JWT.create();

		String token = builder.withHeader(map) // head
				.withClaim("userId", 21) // payload
				.withClaim("username", "xizi")
				.withExpiresAt(instance.getTime()) // 过期时间 60秒之后
				.sign(Algorithm.HMAC256("123456"));// 签名  盐
		System.out.println("-------------------------");
		System.out.println(token);
		System.out.println("-------------------------");
	}

	@Test
			public void test() {
				// 创建验证对象
				Verification verification = JWT.require(Algorithm.HMAC256("QWER"));// 签名
				JWTVerifier jwtVerifier = verification.build();

				DecodedJWT verify;
				try {
					verify = jwtVerifier.verify(
							"eyJ0eX1AiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjY2NjYzMjAsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGl6aSJ9.gyIL5P_8UQBOKJXVb9sul6idJIdDkM5NnvJu-j0m4Ws");
					Claim userId = verify.getClaim("userId");
//        byte[] header = Base64.getDecoder().decode(verify.getHeader());
//        System.out.println(new String(header));
					Claim username = verify.getClaim("username");
					System.out.println(userId.asInt());
					System.out.println(username.asString());
					System.out.println("过期时间：" + verify.getExpiresAt());
				} catch (JWTVerificationException e) {
					e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		// 创建验证对象
		Verification verification = JWT.require(Algorithm.HMAC256("123456"));// 签名
		JWTVerifier jwtVerifier = verification.build();

		DecodedJWT verify;
		try {
			verify = jwtVerifier.verify(
					"eyJ0eX1AiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjY2NjYzMjAsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGl6aSJ9.gyIL5P_8UQBOKJXVb9sul6idJIdDkM5NnvJu-j0m4Ws");
//			return true;
		} catch (JWTVerificationException e) {
//			return false;
		}
	}

	@Test
	public void test3() {
		byte[] header = Base64.getDecoder().decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9");
		System.out.println(new String(header));
	}
}
