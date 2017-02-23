package it.cajani.matteo.salestaxes;

import it.cajani.matteo.salestaxes.domain.Good;
import it.cajani.matteo.salestaxes.domain.Receipt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static it.cajani.matteo.salestaxes.domain.GoodCategory.*;
import static java.math.BigDecimal.valueOf;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).getBean(Application.class).run();
    }

    private void run() {
        List<Receipt> receipts = of(
                Receipt.builder().goods(of(
                        Good.builder().name("book").category(BOOKS).imported(false).price(valueOf(12.49)).build(),
                        Good.builder().name("music CD").category(AUDIO).imported(false).price(valueOf(14.99)).build(),
                        Good.builder().name("chocolate bar").category(FOOD).imported(false).price(valueOf(0.85)).build()
                )).build(),
                Receipt.builder().goods(of(
                        Good.builder().name("box of chocolates").category(FOOD).imported(true).price(valueOf(10.00)).build(),
                        Good.builder().name("bottle of perfume").category(BEAUTY).imported(true).price(valueOf(47.50)).build()
                )).build(),
                Receipt.builder().goods(of(
                        Good.builder().name("bottle of perfume").category(BEAUTY).imported(true).price(valueOf(27.99)).build(),
                        Good.builder().name("bottle of perfume").category(BEAUTY).imported(false).price(valueOf(18.99)).build(),
                        Good.builder().name("packet of headache pills").category(HEALTH).imported(false).price(valueOf(9.75)).build(),
                        Good.builder().name("box of chocolates").category(FOOD).imported(true).price(valueOf(11.25)).build()
                )).build()
        );
        System.out.println(receipts.stream().map(Receipt::toString).reduce("", (a, b) -> String.format("%s\n%s\n", a, b)));
    }
}
