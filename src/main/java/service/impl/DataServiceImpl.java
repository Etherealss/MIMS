package service.impl;

import common.enums.ExceptionType;
import common.enums.ResourcePath;
import common.enums.ResultCode;
import common.exception.BadRequestException;
import pojo.vo.Msg;
import service.DataService;
import util.DataWriter;

import java.io.IOException;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public class DataServiceImpl implements DataService {

    private DataWriter writer = new DataWriter();

    @Override
    public void saveData(String dataType) throws IOException, BadRequestException {
        switch (dataType) {
            case "user":
                writer.exportUserData(ResourcePath.USER_DATA_PATH);
                break;
            case "music":
                writer.exportMusicData(ResourcePath.MUSIC_DATA_PATH);
                break;
            case "consumption":
                writer.exportConsumptionData(ResourcePath.CONSUMING_RECORD_PATH);
                break;
            case "recharge":
                writer.exportRechargeData(ResourcePath.RECHARGE_RECORD_PATH);
                break;
            default:
                throw new BadRequestException(ExceptionType.ERROR_TYPE, "错误的保存类型");
        }
    }

    @Override
    public void exportData(String dataType, String exportFilePath) throws IOException, BadRequestException {
        switch (dataType) {
            case "user":
                writer.exportUserData(exportFilePath);
                break;
            case "music":
                writer.exportMusicData(exportFilePath);
                break;
            case "consumption":
                writer.exportConsumptionData(exportFilePath);
                break;
            case "recharge":
                writer.exportRechargeData(exportFilePath);
                break;
            default:
                throw new BadRequestException(ExceptionType.ERROR_TYPE, "错误的保存类型");

        }
    }
}
