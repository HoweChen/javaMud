package pojo;

import java.util.function.Function;
import javax.annotation.Nonnull;

/**
 * @author yuhaochen
 */
public class IResult<T> {


    public enum ResultType {
        OK, ERROR
    }

    Object response;
    IError error;

    ResultType resultType;


    public IResult() {
    }

    @Nonnull
    public static<T> IResult ERROR(@Nonnull IError error) {
        IResult bw = new IResult();
        bw.setResultType(ResultType.ERROR);
        bw.setError(error);
        return bw;
    }

    @Nonnull
    public static IResult OK(@Nonnull Object response) {
        IResult bw = new IResult();
        bw.setResultType(ResultType.OK);
        bw.setResponse(response);
        return bw;
    }

    @Nonnull
    public static IResult OK(@Nonnull IResult response, Function<Object, Object> mapper) {
        if (response.getResultType() == ResultType.OK) {
            response.setResponse(mapper.apply(response.response));
        }

        return response;
    }

    public <T> T getResponse(Class<T> clazz) {
        return (T) response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public IError getError() {
        return error;
    }

    public void setError(IError error) {
        this.error = error;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }
}
