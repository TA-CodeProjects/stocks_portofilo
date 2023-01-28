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
        adminService.addUser(user1);
        adminService.addUser(user2);
        adminService.addUser(user3);



        userService.setUser(adminService.getOneUser(2));
        userService.makeTransaction("GOOG", 100, 86.4, TransactionType.BUY, "2022-12-28");
        userService.makeTransaction("GOOG", 100, 88.4, TransactionType.BUY, "2022-12-31");
        userService.makeTransaction("AAPL", 200, 151.07, TransactionType.BUY, "2022-11-23");
        userService.makeTransaction("AAPL", 100, 161.07, TransactionType.SELL, "2022-11-23");

        userService.setUser(adminService.getOneUser(3));
        userService.makeTransaction("GOOG", 100, 86.4, TransactionType.BUY, "2022-12-28");
        userService.makeTransaction("GOOG", 100, 88.4, TransactionType.BUY, "2022-12-31");
        userService.makeTransaction("AAPL", 200, 151.07, TransactionType.BUY, "2022-11-23");
        userService.makeTransaction("AAPL", 100, 161.07, TransactionType.SELL, "2022-11-23");

        userService.setUser(adminService.getOneUser(4));
        userService.makeTransaction("GOOG", 100, 86.4, TransactionType.BUY, "2022-12-28");
        userService.makeTransaction("GOOG", 100, 88.4, TransactionType.BUY, "2022-12-31");
        userService.makeTransaction("AAPL", 200, 151.07, TransactionType.BUY, "2022-11-23");
        userService.makeTransaction("AAPL", 100, 161.07, TransactionType.SELL, "2022-11-23");

        adminService.getAllUsers().forEach(System.out::println);








    }
}
