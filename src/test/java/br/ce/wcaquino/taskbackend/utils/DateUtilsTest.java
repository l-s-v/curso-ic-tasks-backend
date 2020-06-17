package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(LocalDate.of(2030, 01, 01)));
	}
	
	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(LocalDate.of(2010, 01, 01)));
	}
	
	@Test
	public void deveRetornarTrueParaDataAtual() {
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(LocalDate.now()));
	}
}
