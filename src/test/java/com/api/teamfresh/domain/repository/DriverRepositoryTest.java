package com.api.teamfresh.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.api.teamfresh.domain.entity.Driver;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DriverRepositoryTest {

    private Driver realDriver;

    @Autowired
    private DriverRepository driverRepository;
    @BeforeEach
    void beforeEach() {
        realDriver = Driver.from("박기사", "01012341234");
        driverRepository.save(realDriver);
    }
    @Test
    @DisplayName("Spring Data Jpa로 [기사 이름, 기사 핸드폰 번호]로 잘 조회 되는지 확인")
    void findById() {
        // given
        String driverName = "박기사";
        String driverPhone = "01012341234";
        // when
        Optional<Driver> optionalDriver = driverRepository.findByNameAndPhoneNumber(driverName, driverPhone);

        // then
        Assertions.assertThat(optionalDriver).isPresent(); // 해당 Id로 Optional이 비어있지 않은지 확인
        Driver test = optionalDriver.get();
        Assertions.assertThat(realDriver.getName()).isEqualTo(test.getName());
        Assertions.assertThat(realDriver.getId()).isEqualTo(test.getId());
        Assertions.assertThat(realDriver.getPhoneNumber()).isEqualTo(test.getPhoneNumber());
    }
}