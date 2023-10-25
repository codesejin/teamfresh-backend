package com.api.teamfresh.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.api.teamfresh.domain.constants.VOCContent;
import com.api.teamfresh.domain.entity.Claim;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ClaimTest {

    @Nested
    @DisplayName("[클레임 인입] 테스트")
    class ClaimAcceptanceTest {
        @Test
        @DisplayName("[배송 지연]으로 접수된다.")
        void claimWithDelayedDelivery() {

            Claim claim = new Claim(VOCContent.DELAYED_DELIVERY);
            assertThat(claim).isNotNull();
            assertThat(claim).isInstanceOf(Claim.class);
        }

        @Test
        @DisplayName("[잘못된 위치로 배송]으로 접수된다.")
        void claimWithWrongDeliveryLocation() {

            Claim claim = new Claim(VOCContent.WRONG_DELIVERY_LOCATION);
            assertThat(claim).isNotNull();
            assertThat(claim).isInstanceOf(Claim.class);
        }

        @Test
        @DisplayName("[물건 파손]으로 접수된다.")
        void claimWithDamagedItem() {

            Claim claim = new Claim(VOCContent.DAMAGED_ITEM);
            assertThat(claim).isNotNull();
            assertThat(claim).isInstanceOf(Claim.class);
        }

        @Test
        @DisplayName("[물건 누락]으로 접수된다.")
        void claimWithMissingItem() {

            Claim claim = new Claim(VOCContent.MISSING_ITEM);
            assertThat(claim).isNotNull();
            assertThat(claim).isInstanceOf(Claim.class);
        }
    }
}