package br.com.bonaldoapps.trymee.service.facade;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;

@Stateless
public class EncriptService {

	private static final String CRIPT_KEY = "trymeeappcriptos";

	public String encriptText(String text) {

		if (text == null || text.isEmpty())
			return null;

		try {

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] mensagem = text.getBytes();

			byte[] chave = CRIPT_KEY.getBytes();

			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
			byte[] encrypted = cipher.doFinal(mensagem);

			return encrypted.toString();

		} catch (Exception e) {
			// TODO
			return null;
		}

	}

	public String decriptText(String text) {

		if (text == null || text.isEmpty())
			return null;

		try {

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] mensagem = text.getBytes();

			byte[] chave = CRIPT_KEY.getBytes();

			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
			byte[] decrypted = cipher.doFinal(mensagem);

			return decrypted.toString();

		} catch (Exception e) {
			// TODO
			return null;
		}

	}

}
