public enum EnumOperationStatus
{
    OPERATION_SUCCESSFULLY("Operation successfully"),
    ERROR("Error"),
    INSUFFICIENT_FUNDS("Insufficient funds");

    public String TEXT_MESSAGE;
    private EnumOperationStatus(String text) {
        this.TEXT_MESSAGE = text;
    }
}
