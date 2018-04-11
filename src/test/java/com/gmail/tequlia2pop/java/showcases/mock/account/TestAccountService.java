package com.gmail.tequlia2pop.java.showcases.mock.account;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 用于测试 AccountService 类的测试用例。
 * 我们使用了自定义的 mock AccountManager，该类也是我们自己实现的。
 */
public class TestAccountService {
	@Test
	public void testTransferOk() {
		MockAccountManager mockAccountManager = new MockAccountManager();
		
		Account senderAccount = new Account("1", 200);
		Account beneficiaryAccount = new Account("2", 100);
		mockAccountManager.addAccount("1", senderAccount);
		mockAccountManager.addAccount("2", beneficiaryAccount);

		AccountService accountService = new AccountService();
		accountService.setAccountManager(mockAccountManager);
		accountService.transfer("1", "2", 50);

		assertEquals(150, senderAccount.getBalance());
		assertEquals(150, beneficiaryAccount.getBalance());
	}
}
