package com.guruji.auth.enums;

/**
 * <h1>EncryptDecryptEnum</h1>
 * <p>This enum used for get values which are used to Encryption and Decryption</p>
 *
 * @author Softvan Nester
 * @version 1.0
 * @since 25-May-2020
 */
public enum EncryptDecryptEnum {

  CIPHER_INSTANCE("AES/ECB/PKCS5Padding"),
  UTF_8("UTF-8"),
  SHA_1("SHA-1"),
  AES("AES"),
  KEY("b780a9a45c966041b9cb3070761ffce5");

  private final String value;

  EncryptDecryptEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
