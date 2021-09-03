package stateMachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static stateMachine.StateEnum.PAID;
import static stateMachine.StateEnum.WAIT_FOR_PAYMENT;

public class StateMachineTest {

  @Test
  void givenWaitForPaymentState_whenOperate_thenShouldSucceed() {
    Logger log = LoggerFactory.getLogger(StateMachineTest.class);
    // given
    StateEnum state = WAIT_FOR_PAYMENT;
    // when
    StateEnum nextState = state.next();
    StateEnum prevState = state.prev();

//    // then
//    log.info();
//    Assertions.assertThat(nextState).isNotNull().isEqualTo(PAID);
//    Assertions.assertThat(prevState).isNotNull().isEqualTo(WAIT_FOR_PAYMENT);
  }
}
