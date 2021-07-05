package stateMachine;

/** @author yuhaochen */
public enum StateEnum implements State {
  WAIT_FOR_PAYMENT {
    @Override
    public StateEnum prev() {
      return this;
    }

    @Override
    public StateEnum next() {
      return PAID;
    }
  },

  PAID {
    @Override
    public StateEnum prev() {
      return WAIT_FOR_PAYMENT;
    }

    @Override
    public StateEnum next() {
      return this;
    }
  },

  REFUNDED {
    @Override
    public StateEnum prev() {
      return PAID;
    }

    @Override
    public StateEnum next() {
      return this;
    }
  },

  CANCLED {
    @Override
    public StateEnum prev() {
      return WAIT_FOR_PAYMENT;
    }

    @Override
    public StateEnum next() {
      return this;
    }
  };

  StateEnum() {}
}
