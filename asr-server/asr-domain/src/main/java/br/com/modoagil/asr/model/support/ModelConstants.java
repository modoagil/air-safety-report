package br.com.modoagil.asr.model.support;

/**
 * Constants used on entity model
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
public final class ModelConstants {

    private ModelConstants() {}

    /**
     * Constant for JPA column with size equals 2
     */
    public static final int COLUMN_SIZE_2 = 2;

    /**
     * Constant for JPA column with size equals 15
     */
    public static final int COLUMN_SIZE_15 = 15;

    /**
     * Constant for JPA column with size equals 20
     */
    public static final int COLUMN_SIZE_20 = 20;

    /**
     * Constant for JPA column with size equals 30
     */
    public static final int COLUMN_SIZE_30 = 30;

    /**
     * Constant for JPA column with size equals 40
     */
    public static final int COLUMN_SIZE_40 = 40;

    /**
     * Constant for JPA column with size equals 45
     */
    public static final int COLUMN_SIZE_45 = 45;

    /**
     * Constant for JPA column with size equals 50
     */
    public static final int COLUMN_SIZE_50 = 50;

    /**
     * Constant for JPA column with size equals 60
     */
    public static final int COLUMN_SIZE_60 = 60;

    /**
     * Constant for JPA column with size equals 120
     */
    public static final int COLUMN_SIZE_120 = 120;

    /**
     * Constant for JPA column with size equals 600
     */
    public static final int COLUMN_SIZE_600 = 600;

    /**
     * Constant for JPA column with size equals 5000
     */
    public static final int COLUMN_SIZE_5000 = 5000;

    /**
     * Constant for bean validation on field with minimum OR maximum of 1
     */
    public static final int FIELD_SIZE_1 = 1;

    /**
     * Constant for bean validation on field with minimum OR maximum of 15
     */
    public static final int FIELD_SIZE_15 = 15;

    /**
     * Constant for bean validation on field with minimum OR maximum of 20
     */
    public static final int FIELD_SIZE_20 = COLUMN_SIZE_20;

    /**
     * Constant for bean validation on field with minimum OR maximum of 30
     */
    public static final int FIELD_SIZE_30 = COLUMN_SIZE_30;

    /**
     * Constant for bean validation on field with minimum OR maximum of 40
     */
    public static final int FIELD_SIZE_40 = COLUMN_SIZE_40;

    /**
     * Constant for bean validation on field with minimum OR maximum of 45
     */
    public static final int FIELD_SIZE_45 = COLUMN_SIZE_45;

    /**
     * Constant for bean validation on field with minimum OR maximum of 50
     */
    public static final int FIELD_SIZE_50 = COLUMN_SIZE_50;

    /**
     * Constant for bean validation on field with minimum OR maximum of 60
     */
    public static final int FIELD_SIZE_60 = COLUMN_SIZE_60;

    /**
     * Constant for bean validation on field with minimum OR maximum of 600
     */
    public static final int FIELD_SIZE_600 = COLUMN_SIZE_600;

    /**
     * Constant for bean validation on phone fields
     */
    public static final String PHONE_PATTERN = "([1-9]{2})?([0-9]{8,9})";

    /**
     * Constant for bean validation on email fields
     */
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,5}";

}
