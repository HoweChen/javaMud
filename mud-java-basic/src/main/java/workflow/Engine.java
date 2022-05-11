package workflow;

/**
 * @author yuhaochen
 */
public interface Engine<T> {

  void run(T unit, Context context);
}
