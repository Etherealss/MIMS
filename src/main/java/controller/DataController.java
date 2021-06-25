package controller;

import common.enums.ResourcePath;
import common.enums.ResultCode;
import common.exception.BadRequestException;
import common.factory.ServiceFactory;
import pojo.vo.Msg;
import service.DataService;
import util.DataWriter;

import java.io.IOException;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public class DataController {

    private DataService dataService = ServiceFactory.getDataService();

    public Msg saveData(String dataType) {
        try {
            dataService.saveData(dataType);
            return Msg.success();
        } catch (IOException e) {
            return Msg.exception("IO异常");
        } catch (BadRequestException e) {
            Msg msg = new Msg();
            msg.setCode(ResultCode.ERROR_TYPE);
            msg.setMessage("错误的数据类型");
            return msg;
        }
    }

    /**
     * 导出文件至指定路径
     * @param dataType
     * @param exportFilePath
     * @return
     */
    public Msg exportData(String dataType, String exportFilePath) {
        try {
            dataService.exportData(dataType, exportFilePath);
            return Msg.success();
        } catch (IOException e) {
            return Msg.exception("IO异常");
        } catch (BadRequestException e) {
            Msg msg = new Msg();
            msg.setCode(ResultCode.ERROR_TYPE);
            msg.setMessage("错误的数据类型");
            return msg;
        }
    }
}
