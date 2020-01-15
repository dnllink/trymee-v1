/**
 * 
 */
package br.com.bonaldoapps.trymee.application;

import java.text.DecimalFormat;

/**
 * @author Daniel
 *
 */
public class FeatureTest {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("#,##0.00");

		Double price = 128.99;

		System.out.println("R$ " + df.format(price));

	}

}
