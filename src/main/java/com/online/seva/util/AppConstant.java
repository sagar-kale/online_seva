package com.online.seva.util;

public class AppConstant {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String EMAIL_NOT_VALID = "The e-mail you have entered is not valid.";
    public static final String USER_ALREADY_EXIST = "USER_ALREADY_EXIST";

    private static final String PASSWORD_CANNOT_BE_USED = "Your password is not acceptable by the organizational password policy.";
    private static final String PASSWORD_IS_TOO_LONG = "Password is too long";
    private static final String PASSWORD_IS_TOO_SHORT = "Password is too short";
    private static final String PASSWORD_TOO_FEW_LOWERS = "Password needs to contains at least %d lower-case characters";
    private static final String PASSWORD_TOO_FEW_UPPERS = "Password needs to contains at least %d upper-case characters";
    private static final String PASSWORD_TOO_FEW_NUMERICS = "Password needs to contains at least %d numeric characters";
    private static final String PASSWORD_TOO_FEW_SPECIAL_SYMBOLS = "Password needs to contains at least %d special symbols";
    private static final String SETTING_A_NEW_PASSWORD_HAS_FAILED_PLEASE_NOTE_THE_PASSWORD_POLICY_AND_TRY_AGAIN_ERROR_MESSAGE =
            "Setting a new password has failed. Please note the password policy and try again. Error message: ";
    public static final String ACCOUNT_CREATION_HAS_FAILED_PASSWORDS_DO_NOT_MATCH =
            "Account creation has failed. These passwords don't match";

    private static final String ACCOUNT_LOCKED_OR_DOES_NOT_EXIST = "Account is locked or does not exist";

    private static final String LINK_HAS_EXPIRED = "link has expired";
    private static final String LINK_DOES_NOT_EXIST = "link does not exist in DB";    //means that link was already used, or it is invalid

    private static final String CHANGE_PASSWORD_FAILED_NEW_PASSWORD_SAME_AS_OLD_PASSWORD = "CHANGE PASSWORD FAILED: New Password is same as Old Password.";
    private static final String CHANGE_PASSWORD_BAD_OLD_PASSWORD = "CHANGE PASSWORD Failed: Bad Old Password.";
}
