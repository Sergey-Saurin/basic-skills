public enum EnumOperationStatus
{
    OPERATION_SUCCESSFULLY("Operation successfully"),
    ERROR("Error");

    private final String TEXT_MESSAGE;

    private EnumOperationStatus(String text) {
        this.TEXT_MESSAGE = text;
    }
    public String getTextMessage() {
        return TEXT_MESSAGE;
    }
}
