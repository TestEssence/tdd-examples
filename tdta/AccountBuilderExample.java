public class AccountBuilderExample {
    public static void main(String[] args) {
        String owner = "Richard C. Collins";
        Account account = new Account.AccountBuilder("Saving Account", owner, 1111l)
                .balance(96458.32)
                .interest(4.5)
                .type("SAVING")
                .build();
        Assert.assertEquals(account.getOwner(), owner);
        System.out.println(account);
    }
}
