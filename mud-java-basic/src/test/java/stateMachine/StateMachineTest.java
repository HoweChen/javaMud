package stateMachine;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static stateMachine.StateEnum.*;

public class StateMachineTest {

  @Test
  void givenWaitForPaymentState_whenOperate_thenShouldSucceed() {
    // given
    StateEnum state = WAIT_FOR_PAYMENT;
    // when
    StateEnum nextState = state.next();
    StateEnum prevState = state.prev();

    // then
    Assertions.assertThat(nextState).isNotNull().isEqualTo(PAID);
    Assertions.assertThat(prevState).isNotNull().isEqualTo(WAIT_FOR_PAYMENT);
  }
}
