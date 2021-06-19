package score;

public enum EnumOperationStatus
{
    OPERATION_SUCCESSFULLY("Operation successfully"),
    ERROR("Error"),
    INSUFFICIENT_FUNDS("Insufficient funds"),
    FORBIDDEN_TO_REMOVE("Forbidden to remove"),
    NO_TEXT(""),
    WRONG_AMOUNT("Wrong amount");

    private final String TEXT_MESSAGE;

    private EnumOperationStatus(String text) {
        this.TEXT_MESSAGE = text;
    }
    public String getTextMessage() {
        return TEXT_MESSAGE;
    }
}
