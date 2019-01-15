package com.vk.type;

/**
 * Created by liuzb on 18-7-3.
 * 巡查记录状态
 */
public class InspectionRecordStatus {

    public static final int CHECK_WAIT = 0; //审核中

    public static final int ALTER_ING = 1; //大联动整改中

    public static final int ALTER_FINISH = 2; //整改完成待复查

    public static final int ALTER_FINISH_CHECKING = 3; //复查完成待审核

    public static final int FINISH = 5; //已完成
}
