package business.conversion;

public interface IConversionProcess {
    String convertBytesToHex(byte[] bytes);
    byte[] convertHexToBytes(String hex);
}
