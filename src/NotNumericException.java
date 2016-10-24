/**
 * Created by Mamel on 11.10.2016.
 */
public class NotNumericException extends Exception {

    private String ErrorCode = "321213";


    public NotNumericException(){
        super("Variable is not numerical");
    }

    public String ErrorCode()
    {
        return ErrorCode;
    }
}
