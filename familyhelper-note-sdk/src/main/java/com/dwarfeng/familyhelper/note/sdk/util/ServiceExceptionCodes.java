package com.dwarfeng.familyhelper.note.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 14000;

    public static final ServiceException.Code USER_NOT_EXISTS =
            new ServiceException.Code(offset(0), "user not exists");
    public static final ServiceException.Code USER_NOT_PERMITTED_FOR_PB_SET =
            new ServiceException.Code(offset(10), "user not permitted for pb set");
    public static final ServiceException.Code PB_SET_NOT_EXISTS =
            new ServiceException.Code(offset(20), "pb set not exists");
    public static final ServiceException.Code INVALID_PERMISSION_LEVEL =
            new ServiceException.Code(offset(30), "invalid permission level");
    public static final ServiceException.Code PB_NODE_NOT_EXISTS =
            new ServiceException.Code(offset(40), "pb node not exists");
    public static final ServiceException.Code ILLEGAL_PB_NODE_STATE =
            new ServiceException.Code(offset(50), "illegal pb node state");
    public static final ServiceException.Code PB_SET_NOT_IDENTICAL =
            new ServiceException.Code(offset(60), "pb set not identical");
    public static final ServiceException.Code PB_ITEM_NOT_EXISTS =
            new ServiceException.Code(offset(70), "pb item not exists");
    public static final ServiceException.Code ILLEGAL_PB_ITEM_STATE =
            new ServiceException.Code(offset(80), "illegal pb item state");
    public static final ServiceException.Code PB_RECORD_NOT_EXISTS =
            new ServiceException.Code(offset(90), "pb record not exists");
    public static final ServiceException.Code ILLEGAL_PB_RECORD_STATE =
            new ServiceException.Code(offset(100), "illegal pb record state");
    public static final ServiceException.Code PB_FILE_NOT_EXISTS =
            new ServiceException.Code(offset(110), "pb file not exists");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        USER_NOT_EXISTS.setCode(offset(0));
        USER_NOT_PERMITTED_FOR_PB_SET.setCode(offset(10));
        PB_SET_NOT_EXISTS.setCode(offset(20));
        INVALID_PERMISSION_LEVEL.setCode(offset(30));
        PB_NODE_NOT_EXISTS.setCode(offset(40));
        ILLEGAL_PB_NODE_STATE.setCode(offset(50));
        PB_SET_NOT_IDENTICAL.setCode(offset(60));
        PB_ITEM_NOT_EXISTS.setCode(offset(70));
        ILLEGAL_PB_ITEM_STATE.setCode(offset(80));
        PB_RECORD_NOT_EXISTS.setCode(offset(90));
        ILLEGAL_PB_RECORD_STATE.setCode(offset(100));
        PB_FILE_NOT_EXISTS.setCode(offset(110));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
