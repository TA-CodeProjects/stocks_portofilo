package com.ta.stock_protfolio.clr;

import com.ta.stock_protfolio.beans.TransactionType;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.services.AdminService;
import com.ta.stock_protfolio.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(2)
@RequiredArgsConstructor
public class InitUsers implements CommandLineRunner {
    private final AdminService adminService;
    private final UserService userService;
    @Value("${ADMIN_EMAIL}")
    private String ADMIN_EMAIL;
    @Value("${ADMIN_PASSWORD}")
    private String ADMIN_PASSWORD;

    @Override
    public void run(String... args) throws Exception {
        User admin = User.builder().firstName("Admin").lastName("Admin").email(ADMIN_EMAIL).password(ADMIN_PASSWORD).build();

        adminService.addUser(admin);

        User user1 = User.builder().firstName("Moshe").lastName("Chen").email("moshe@gmail.com").password("1234").build();
        User user2 = User.builder().firstName("Dana").lastName("Bar").email("dana@gmail.com").password("1234").build();
        User user3 = User.builder().firstName("Dani").lastName("Shovevani").email("dani@gmail.com").password("1234").build();
        User user4 = User.builder().firstName("Nir").lastName("Bir").email("nir@gmail.com").password("1234").build();
        adminService.addUser(user1);
        adminService.addUser(user2);
        adminService.addUser(user3);
        adminService.addUser(user4);



        userService.setUser(adminService.getOneUser(2));
        userService.makeTransaction("GOOG", 100, 96.73, TransactionType.BUY, "2023-01-25");
        userService.makeTransaction("GOOG", 100, 88.4, TransactionType.BUY, "2023-02-01");
        userService.makeTransaction("AAPL", 200, 142.53, TransactionType.BUY, "2023-01-24");
        userService.makeTransaction("AAPL", 100, 154.65, TransactionType.SELL, "2023-02-07");

        userService.setUser(adminService.getOneUser(3));
        userService.makeTransaction("AMZN", 100, 81.82, TransactionType.BUY, "2022-12-28");
        userService.makeTransaction("GOOG", 100, 88.4, TransactionType.BUY, "2022-12-31");

        userService.setUser(adminService.getOneUser(4));
        userService.makeTransaction("TSLA", 200, 181.41, TransactionType.BUY, "2023-01-02");
        userService.makeTransaction("COST", 100, 498.3, TransactionType.BUY, "2023-01-26");
        userService.makeTransaction("COST", 50, 151.07, TransactionType.SELL, "2023-02-07");

        userService.setUser(adminService.getOneUser(5));
        userService.makeTransaction("NFLX", 200, 291.12, TransactionType.BUY, "2022-12-29");


        adminService.getAllUsers().forEach(System.out::println);

//        userService.getTransactionHistory(1).forEach(System.out::println);







    }
}
