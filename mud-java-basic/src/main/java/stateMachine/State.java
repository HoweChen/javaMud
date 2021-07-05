package stateMachine;

public interface State {

    StateEnum prev();
    StateEnum next();
}
