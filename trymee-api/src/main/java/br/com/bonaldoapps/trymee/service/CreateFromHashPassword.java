/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Daniel
 *
 */
public class CreateFromHashPassword {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String senha = "123456";

		UserPassHashGenerator hash = new UserPassHashGenerator();

		System.out.println("Hash1: " + hash.createHashFromPass(senha));

		System.out.println("Hash2: " + hash.createHashFromPass(senha));

		System.out.println("Hash3: " + hash.createHashFromPass(senha));

	}

}
